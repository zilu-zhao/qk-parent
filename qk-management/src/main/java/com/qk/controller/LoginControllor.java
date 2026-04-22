package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.LoginResultVo;
import com.qk.entity.User;
import com.qk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginControllor {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result loginin(@RequestBody User user){
        LoginResultVo loginResultVo=userService.loginin(user.getUsername(),user.getPassword());
        return  Result.success(loginResultVo);
    }
}
