package com.qk.service.impl;

import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.mapper.DeptMapper;
import com.qk.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeptServiceImpl  implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public void addDept(Dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
         deptMapper.InsertDept(dept);
    }
}
