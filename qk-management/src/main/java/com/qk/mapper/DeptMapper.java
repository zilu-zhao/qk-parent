package com.qk.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Insert("Insert into dept (name,status,create_time,update_time)values (#{name},#{status},#{createTime},#{updateTime})")
    void InsertDept(Dept dept);

    List<Dept> selectByNameAndStatus(String name, Integer status);
@Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);

    void update(Dept dept);
@Delete("delete  from dept where id=#{id}")
    void deleteDept(Integer id);
@Select("select * from dept")
    List<Dept> selectAll();
}
