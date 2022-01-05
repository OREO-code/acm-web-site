package com.acm.web.service;


import com.acm.web.form.LoginForm;
import com.acm.web.form.UpdateUserFrom;
import com.acm.web.vo.JwtVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.UserVo;

import java.util.concurrent.Future;

public interface UserService {

    ResponseVo<JwtVo> login(String username, String password);

    ResponseVo<UserVo> currentUser(String token);

    ResponseVo logout(String token);

    ResponseVo addUser(LoginForm loginForm);

    ResponseVo updateUser(UpdateUserFrom updateUserFrom);

    Future<ResponseVo> sendEmail(String address);
}
