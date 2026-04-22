package com.qk.mapper;

import com.qk.dto.RoleDto;
import com.qk.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
  @Select("select name,label,remark,update_time from role")
    List<Role> selectAll(RoleDto roleDto);
@Select("select * from role")
    List<Role> selectAlll();
@Delete("delete from role where id=#{id}")
  void deletebyid(Integer id);
}
