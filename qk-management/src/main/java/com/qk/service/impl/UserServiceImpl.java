package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.dto.UserDto;
import com.qk.entity.LoginResultVo;
import com.qk.entity.User;
import com.qk.exception.BuniessException;
import com.qk.mapper.UserMapper;
import com.qk.service.UserService;
import com.qk.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
/*登录*/
    @Override
    public LoginResultVo loginin(String username, String password) {
        //第一步：因为数据库的密码都是md5的，把前端传来的密码变为md5密文
        String newpassword = DigestUtils.md5DigestAsHex(password.getBytes());
        //第二步：调用mapper，查询该账户密码在数据库是否存在
        User user =userMapper.loginin(username);
         //第三步：
        if (user == null || !user.getPassword().equals(newpassword)) {
            throw new BuniessException("用户不存在或密码错误"); // 用户不存在或密码错误
        }
        // 校验用户状态
        if (user.getStatus()==0) { // 0 表示停用状态
            throw new BuniessException("账号已停用，不允许登录"); // 状态异常，不允许登录
        }
        /*生成jwt令牌*/
        /*第一步：创建一个map数组，把个人信息 username和password放入到数组内*/
        Map<String, Object> claim = new HashMap<>();
        claim.put("id",user.getId());
        claim.put("password",user.getPassword());
        /*调用自己创建的JwtUtils工具类的generateToken方法，把装有信息的数组上传生成一个jwt令牌*/
        String jwt = JwtUtils.generateToken(claim);
        /*查出来user对象的值太多了，前端用不了，把user的值给loginResultVo，返回给controller*/
        LoginResultVo loginResultVo = new LoginResultVo();
        loginResultVo.setUsername(user.getUsername());
        loginResultVo.setName(user.getName());
        loginResultVo.setImage(user.getImage());
        loginResultVo.setRoleLabel(user.getRoleLabel());
        loginResultVo.setToken(jwt);
        return loginResultVo;
    }
}
