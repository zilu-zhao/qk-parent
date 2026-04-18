package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.User;
import com.qk.mapper.UserMapper;
import com.qk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageResult<User> selectUser(UserDto userDto) {
        PageHelper.startPage(userDto.getPage(),userDto.getPageSize());
        List<User> list=userMapper.SelectUser(userDto);
        PageInfo<User> userPageInfo = new PageInfo<>(list);
        return new PageResult<>(userPageInfo.getTotal(),userPageInfo.getList());
    }
}
