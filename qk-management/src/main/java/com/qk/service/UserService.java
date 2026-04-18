package com.qk.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;

import java.util.List;

public interface UserService {
    PageResult selectUser(UserDto userDto);

    /*分页查询*/

}
