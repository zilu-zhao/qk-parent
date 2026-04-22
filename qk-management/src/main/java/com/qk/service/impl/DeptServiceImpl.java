package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.mapper.DeptMapper;
import com.qk.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl  implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /*添加*/
    @Override
    public void addDept(Dept dept) {

        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
         deptMapper.InsertDept(dept);
    }

    /*部门查询分页展示*/
    @Override
    public PageResult<Dept> selectDept(String name, Integer status, Integer page, Integer pageSize) {
        /*使用PageHeper进行分页查询*/
        PageHelper.startPage(page,pageSize);
        List<Dept> lists =deptMapper.selectByNameAndStatus(name,status);
        PageInfo<Dept> deptPageInfo = new PageInfo<>(lists);
        return new PageResult<>(deptPageInfo.getTotal(),deptPageInfo.getList());
    }
    /*部门的修改--回显  根据id查*/

    @Override
    public Dept selectById(Integer id) {
        Dept dept=deptMapper.selectById(id);
        return  dept;
    }
/*部门的修改*/
    @Override
    public void update(Dept dept) {
dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
/*删除部门信息*/
    @Override
    public void deleteDept(Integer id) {
        deptMapper.deleteDept(id);
    }
/*查询所有部门*/
    @Override
    public List<Dept> selectAll() {

        return deptMapper.selectAll();
    }


}
