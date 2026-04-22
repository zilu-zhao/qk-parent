package com.qk.service;

import com.qk.common.PageResult;
import com.qk.dto.RoleDto;
import com.qk.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
    /*分页查询*/
   PageResult<Role> selectAll(RoleDto roleDto);
/*查询所有*/
    List<Role> selectAlll();

    void deletebyid(Integer id);
}
