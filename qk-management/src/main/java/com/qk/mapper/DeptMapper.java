package com.qk.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.springframework.web.bind.annotation.PostMapping;

@Mapper
public interface DeptMapper {

    @Insert("Insert into dept (name,status,create_time,update_time)values (#{name},#{status},#{createTime},#{updateTime})")
    void InsertDept(Dept dept);
}
