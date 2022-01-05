package com.acm.web.controller;


import com.acm.web.enums.ResponseEnum;
import com.acm.web.form.LoginForm;
import com.acm.web.form.UpdateUserFrom;
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

    @PostMapping("/updateUser")
    public ResponseVo updateUser(@Valid @RequestBody UpdateUserFrom updateUserFrom) {
        return userService.updateUser(updateUserFrom);
    }

    @RequestMapping("/sendEmail")
    public ResponseVo sendEmail(@RequestParam("address") String address) {
        try {
            return userService.sendEmail(address).get();
        } catch (Exception e) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
    }
}
