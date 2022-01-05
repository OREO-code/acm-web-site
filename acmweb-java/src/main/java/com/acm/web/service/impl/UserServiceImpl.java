package com.acm.web.service.impl;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.acm.web.entity.User;
import com.acm.web.enums.ResponseEnum;
import com.acm.web.form.LoginForm;
import com.acm.web.form.UpdateUserFrom;
import com.acm.web.mapper.UserMapper;
import com.acm.web.service.UserService;
import com.acm.web.utils.JwtUtil;
import com.acm.web.vo.JwtVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.UserVo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    @Qualifier("redisTemplate1")
    StringRedisTemplate redisTemplate1;

    @Autowired
    @Qualifier("redisTemplate2")
    StringRedisTemplate redisTemplate2;

    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    String mailUsername;

    @Value("${officialWebsite.jwt.expiration}")
    private Long jwtExpiration;

    @Value("${spring.mail.expiration}")
    private Long mailExpiration;

    private static final String SALT = "neuqer";

    Gson gson = new Gson();

    @Override
    public ResponseVo<JwtVo> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
//        log.info(username);
//        log.info(password);
//        log.info(DigestUtils.md5DigestAsHex((password + SALT).getBytes(StandardCharsets.UTF_8)));
        if (user == null || !user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex((password + SALT).getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(用户名或密码错误)
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        //不要通过jwt解析出username 再去数据库里查
        //直接存入redis中加快效率
        //另外 请保证redis当前数据库中只含有JWT-User的键值对
        //否则类型转换异常
//        Set<String> keys = redisTemplate.keys("*");
        Set<String> keys = redisTemplate1.execute((RedisCallback<Set<String>>) connection -> {
            Set<String> binaryKeys = new HashSet<>();
            try (Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match("*").count(1000).build())) {
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next(), StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return binaryKeys;
        });
        log.info("keys:{}", keys);
        for (String key : Objects.requireNonNull(keys)) {
            User user1 = gson.fromJson(redisTemplate1.opsForValue().get(key), User.class);
            if (Objects.equals(redisTemplate1.type(key), DataType.STRING) && user1.getUsername().equals(username)) {
                redisTemplate1.expire(key, jwtExpiration - 1, TimeUnit.SECONDS);
                return ResponseVo.success(new JwtVo(key));
            }
        }
        String jwt = jwtUtil.generateToken(username);
        if (Boolean.FALSE.equals(redisTemplate1.hasKey(jwt))) {
            redisTemplate1.opsForValue().set(jwt, gson.toJson(user), jwtExpiration - 1, TimeUnit.SECONDS);
        }
        return ResponseVo.success(new JwtVo(jwt));
    }

    @Override
    public ResponseVo<UserVo> currentUser(String token) {
        jwtUtil.getTokenBody(token);
        String value = redisTemplate1.opsForValue().get(token);
        if (StrUtil.isEmpty(value)) {
            return ResponseVo.error(ResponseEnum.EXPIRED_ERROR);
        }
        User user = gson.fromJson(value, User.class);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        return ResponseVo.success(userVo);
    }

    /**
     * 无法删除JWT在浏览器中的存储
     * 一方面方便存取
     * 另一方面可以标志jwt的删除
     *
     * @param token
     * @return
     */
    @Override
    public ResponseVo logout(String token) {
        String value = redisTemplate1.opsForValue().get(token);
        if (StrUtil.isEmpty(value)) {
            return ResponseVo.error(ResponseEnum.EXPIRED_ERROR);
        }
        redisTemplate1.delete(token);
        return ResponseVo.success("注销成功");
    }

    @Override
    public ResponseVo addUser(LoginForm loginForm) {
        User user = new User();
        BeanUtils.copyProperties(loginForm, user);
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword() + SALT).getBytes(StandardCharsets.UTF_8)));
        boolean flag = userMapper.addUser(user);
        if (!flag) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success("添加成功");
    }

    @Override
    public ResponseVo updateUser(UpdateUserFrom updateUserFrom) {
        if (!updateUserFrom.getNewPassword1().equals(updateUserFrom.getNewPassword2())) {
            return ResponseVo.error(ResponseEnum.PASSWORD_INCONSISTENT);
        }
        User user = userMapper.selectByUsername(updateUserFrom.getUsername());
        if (user == null) {
            return ResponseVo.error(ResponseEnum.USER_NONEXISTENT);
        }
        if (!(updateUserFrom.getOldPassword().equals(user.getPassword()))) {
            return ResponseVo.error(ResponseEnum.OLD_PASSWORD_ERROR);
        }
        if (user.getPassword().equals(DigestUtils.md5DigestAsHex((updateUserFrom.getNewPassword1() + SALT).getBytes(StandardCharsets.UTF_8)))) {
            return ResponseVo.error(ResponseEnum.PASSWORD_CONSISTENT);
        }
        if (!Objects.equals(redisTemplate2.opsForValue().get("verityCode"), updateUserFrom.getVerifyCode())) {
            return ResponseVo.error(ResponseEnum.VERITYCODE_ERROR);
        }
        user.setPassword(DigestUtils.md5DigestAsHex((updateUserFrom.getNewPassword1() + SALT).getBytes(StandardCharsets.UTF_8)));
        log.info("user:{}", user);
        userMapper.updateUser(user);
        return ResponseVo.success("修改成功");
    }

    //TODO 自定义异步任务执行线程池
    @Async
    @Override
    public Future<ResponseVo> sendEmail(String address) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            Thread.sleep(5000);
            if (Boolean.TRUE.equals(redisTemplate2.hasKey(address))) {
                return new AsyncResult<>(ResponseVo.error(ResponseEnum.VERITYCODE_NOT_EXPIRED));
            }
            String verityCode = RandomUtil.randomString(6);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, true);
            mimeMessageHelper.setSubject("NEUQ-ACM更改密码");
            mimeMessageHelper.setText("Hello!您的验证码为:" + verityCode, true);
            mimeMessageHelper.setFrom("NEUQ-ACM<" + mailUsername + ">");
            mimeMessageHelper.setTo(address);
            mailSender.send(mailMessage);
            //TODO 时间差待优化
            redisTemplate2.opsForValue().set(address, verityCode, mailExpiration, TimeUnit.SECONDS);
        } catch (Exception e) {
            return new AsyncResult<>(ResponseVo.error(ResponseEnum.MAIL_DELIVERY_FAILURE));
        }
        return new AsyncResult<>(ResponseVo.success("验证码发送成功，三分钟有效，请注意查收！"));
    }
}
