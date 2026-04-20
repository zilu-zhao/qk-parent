package com.qk.mapper;

import com.qk.dto.UserDto;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
/*分页查询*/
    List<User> SelectUser(UserDto userDto);
/*增加用户*/
@Insert("INSERT INTO user(username, password, name, phone, email, gender, status, dept_id, role_id, image, remark, create_time, update_time) " +
        "VALUES(#{username}, #{password}, #{name}, #{phone}, #{email}, #{gender}, #{status}, #{deptId}, #{roleId}, #{image}, #{remark}, #{createTime}, #{updateTime})")
    void insertUser(User user);

    void deleteUser(List<Integer> ids);
@Select("select id, username, password, name, phone, email, gender, status, dept_id, role_id, image, remark, create_time, update_time from user where id=#{id}")
    List<User> findbyid(Integer id);

    void updayeUser(User user);
}
