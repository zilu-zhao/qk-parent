package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.RoleDto;
import com.qk.entity.Role;
import com.qk.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping
public class RoleController {
@Autowired
private  RoleService roleService;
/*分页查询*/
@GetMapping("/roles")
    public Result selectAll(RoleDto roleDto){
    log.info("角色分页查询+{}",roleDto);
     PageResult<Role> list =roleService.selectAll(roleDto);
    return Result.success(list);
    }
/*查询所有*/
    @GetMapping("/roles/list")
    public Result selectalll(){
        List<Role> list = roleService.selectAlll();
    return Result.success(list);
    }
    /*根据ID进行删除*/
    @DeleteMapping("/roles/{id}")
    public Result deletebyid(@PathVariable("id") Integer id){
        log.error("{}",id);
         roleService.deletebyid(id);
        return Result.success();
    }
}
