package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    /*分页查询*/
    @GetMapping
    public Result SelectUser(UserDto userDto){
        log.info("分页查询+{}",userDto);
          PageResult<User> pg =userService.selectUser (userDto);
                 return Result.success(pg);
    }
    /*添加用户*/
    @PostMapping
    public Result InsertUser(@RequestBody User user){
        log.info("添加用户+{}",user);
        userService.insertUser(user);
        return  Result.success();
    }
    /*删除用户-批量删除*/
    @DeleteMapping("/{ids}")
    public  Result delectUser(@PathVariable("ids") List<Integer> ids){
        log.info("批量删除+{}",ids);
        userService.deleteUser(ids);
return Result.success();
    }
    /*根据ID查询 --也是修改的回显*/
    @GetMapping("/{id}")
    public Result findbyid(@PathVariable("id")Integer id){
        log.info("根据id查询+{}",id);
        List<User> list=userService.findbyid(id);
        return Result.success(list);
    }
    /*修改用户*/
    @PutMapping
    public Result updateUser(@RequestBody User user){
        log.info("修改用户+{}",user);
        userService.updateUser(user);
        return Result.success();
    }
}
