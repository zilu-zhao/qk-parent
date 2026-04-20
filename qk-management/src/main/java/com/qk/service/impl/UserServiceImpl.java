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
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
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
/*新增用户*/
    @Override
    public void insertUser(User user) {
       //先补全密码： 设置密码为"用户名123456"
        String pw = user.getUsername()+"123456";
        //使用md5对明文密码进行加密，使用spring框架的一个工具类
        String newpw=DigestUtils.md5DigestAsHex(pw.getBytes());
        user.setPassword(newpw);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
      userMapper.insertUser(user);
    }
/*删除用户*/
    @Override
    public void deleteUser(List<Integer> ids) {
        userMapper.deleteUser(ids);
    }
/*根据ID查数*/
    @Override
    public List<User> findbyid(Integer id) {
        List<User> list=userMapper.findbyid(id);
        return list;
    }
/*修改用户*/
    @Override
    public void updateUser(User user) {
        //补全修改时间
        user.setUpdateTime(LocalDateTime.now());
        //补全密码并设置密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.updayeUser(user);
    }
}
