package com.acm.web.service;


import com.acm.web.form.LoginForm;
import com.acm.web.vo.JwtVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.UserVo;

public interface UserService {

    ResponseVo<JwtVo> login(String username, String password);

    ResponseVo<UserVo> currentUser(String token);

    ResponseVo logout(String token);

    ResponseVo addUser(LoginForm loginForm);
}
