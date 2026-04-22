package com.qk.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qk.common.PageResult;
import com.qk.dto.RoleDto;
import com.qk.entity.Role;
import com.qk.mapper.RoleMapper;
import com.qk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    /*分页查询*/
    @Override
    public PageResult<Role> selectAll(RoleDto roleDto) {
        PageHelper.startPage(roleDto.getPage(), roleDto.getPageSize());
        List<Role> list = roleMapper.selectAll(roleDto);
        PageInfo<Role> rolePageInfo = new PageInfo<>(list);
        return new PageResult<>(rolePageInfo.getTotal(), rolePageInfo.getList());
    }
/*查询所有*/
    @Override
    public List<Role> selectAlll() {
        return roleMapper.selectAlll();
    }
/*删除-根据id*/
    @Override
    public void deletebyid(Integer id) {
        roleMapper.deletebyid(id);
    }
}
