package com.qk.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;

import java.util.List;

public interface DeptService {

    void addDept(Dept dept);

    Dept selectById(Integer id) ;
    PageResult<Dept> selectDept(String name, Integer status, Integer page, Integer pageSize);

    void update(Dept dept);

    void deleteDept(Integer id);

    List<Dept> selectAll();
}
