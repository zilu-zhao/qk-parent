package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    /*分页查询*/
    @GetMapping
    public Result SelectUser(UserDto userDto){
          PageResult<User> pg =userService.selectUser (userDto);
                 return Result.success(pg);
    }
}
