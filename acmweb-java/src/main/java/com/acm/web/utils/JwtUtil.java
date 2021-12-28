package com.acm.web.utils;

import com.acm.web.exception.ParseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@Slf4j
public class JwtUtil {

    @Value("${officialWebsite.jwt.secret}")
    private String secret;

    @Value("${officialWebsite.jwt.expiration}")
    private  Long expiration;

    /**
     * 创建token
     * @param username 用户名
     * @return
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS384, secret)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }


    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserName(String token){
        return Objects.requireNonNull(getTokenBody(token)).getSubject();
    }


    /**
     *  是否已过期
     * @param token
     * @return
     */
    public boolean isExpiration(String token){
        return Objects.requireNonNull(getTokenBody(token)).getExpiration().before(new Date());
    }


    /**
     * 获取token中的信息
     * 同时解析判断是否为正确的JWT
     * @param token
     * @return
     */
    public Claims getTokenBody(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("解析异常:{}",e.getMessage());
            throw new ParseException();
        }
    }

}