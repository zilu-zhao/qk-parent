package com.qk.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.LoginResultVo;
import com.qk.entity.User;

import java.util.List;

public interface UserService {
    /*分页查询*/
    PageResult selectUser(UserDto userDto);
/*新增用户*/
    void insertUser(User user);


    void deleteUser(List<Integer> ids);

    List<User> findbyid(Integer id);

    void updateUser(User user);
/*登录*/
    LoginResultVo loginin(String username, String password);
}
