package com.qk.mapper;

import com.qk.dto.UserDto;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
/*分页查询*/
    List<User> SelectUser(UserDto userDto);
}
