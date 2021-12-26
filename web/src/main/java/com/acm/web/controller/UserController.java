package com.acm.web.controller;


import com.acm.web.form.LoginForm;
import com.acm.web.service.UserService;
import com.acm.web.vo.JwtVo;
import com.acm.web.vo.ResponseVo;
import com.acm.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseVo<JwtVo> login(@Valid @RequestBody LoginForm loginForm) {
        return userService.login(loginForm.getUsername(), loginForm.getPassword());
    }

    @GetMapping("/currentUser")
    public ResponseVo<UserVo> currentUser(@RequestHeader("Authorization") String token) {
        return userService.currentUser(token);
    }

    @GetMapping("/logout")
    public ResponseVo logout(@RequestHeader("Authorization") String token) {
        return userService.logout(token);
    }

    @PostMapping("/addUser")
    public ResponseVo addUser(@Valid @RequestBody LoginForm loginForm) {
        return userService.addUser(loginForm);
    }
}
