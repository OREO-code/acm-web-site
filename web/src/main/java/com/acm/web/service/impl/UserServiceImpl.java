package com.acm.web.service.impl;


import com.acm.web.entity.User;
import com.acm.web.enums.ResponseEnum;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static com.acm.web.enums.ResponseEnum.USERNAME_OR_PASSWORD_ERROR;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Value("${officialWebsite.jwt.expiration}")
    private Long expiration;

    private static final String SALT = "neuqer";

    Gson gson = new Gson();

    @Override
    public ResponseVo<JwtVo> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        log.info(username);
        log.info(password);
        log.info(DigestUtils.md5DigestAsHex((password + SALT).getBytes(StandardCharsets.UTF_8)));
        if (user == null || !user.getPassword().equalsIgnoreCase(DigestUtils.md5DigestAsHex((password + SALT).getBytes(StandardCharsets.UTF_8)))) {
            //密码错误(用户名或密码错误)
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }
        String jwt = jwtUtil.generateToken(username);
        //不要通过jwt解析出username再去数据库里查
        //直接存入redis中加快效率
        if (Boolean.FALSE.equals(redisTemplate.hasKey(jwt))) {
            redisTemplate.opsForValue().set(jwt, gson.toJson(user), expiration - 1, TimeUnit.SECONDS);
        }
        return ResponseVo.success(new JwtVo(jwt));
    }

    @Override
    public ResponseVo<UserVo> currentUser(String token) {
        jwtUtil.getTokenBody(token);
        String value = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(value)) {
            return ResponseVo.error(ResponseEnum.EXPIRED_ERROR);
        }
        User user = gson.fromJson(value, User.class);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
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
        String value = redisTemplate.opsForValue().get(token);
        if (StringUtils.isEmpty(value)) {
            return ResponseVo.error(ResponseEnum.EXPIRED_ERROR);
        }
        redisTemplate.delete(token);
        return ResponseVo.success("注销成功");
    }

    @Override
    public ResponseVo addUser(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()+SALT).getBytes(StandardCharsets.UTF_8)));
        boolean flag = userMapper.addUser(user);
        if (!flag) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success("添加成功");
    }
}
