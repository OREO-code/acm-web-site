package com.acm.web.service;


import com.acm.web.entity.User;
import com.acm.web.form.UpdateUserFrom;
import com.acm.web.vo.JwtVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.UserVo;

import java.util.List;
import java.util.concurrent.Future;

public interface UserService {

    ResponseVo<JwtVo> login(String username, String password);

    ResponseVo<UserVo> currentUser(String token);

    ResponseVo logout(String token);

    ResponseVo addUser(User user);

    ResponseVo updatePassword(UpdateUserFrom updateUserFrom);

    Future<ResponseVo> sendEmail(String address);

    ResponseVo<List<UserVo>> getAllUser();

    ResponseVo delUser(Integer id);

    ResponseVo updateUser(User user);
}
