package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.service.DeptService;
import lombok.Builder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    /*@postMapping 是用来限定只有post请求该方法*/
    @PostMapping("/depts")
    /*在POST、PUT请求方式中，如果前端传递json格式的请求参数，服务端可以使用Java对象封装，
    方法形参前需要使用@RequestBody注解。如果采用的是*/
    public Result addDept(@RequestBody Dept dept){
          deptService.addDept(dept);
          return  Result.success();
    }

    /*分页查询*/
    @GetMapping("depts")
    public Result selectDept(String name, Integer status,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer pageSize){
              PageResult<Dept> pageresult =deptService.selectDept(name,status,page,pageSize);
return Result.success(pageresult);
    }
    /*部门的修改 --回显 也就是查询*/
    @GetMapping("depts/{id}")
    public Result selectById(@PathVariable("id") Integer id){
    Dept dept=deptService.selectById(id);
    return Result.success(dept);
    }
    /*部门的修改--修改*/
    @PutMapping("depts")
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
     return  Result.success();
    }
    /*部门的删除*/
    @DeleteMapping("depts/{id}")
    public Result delete(@PathVariable("id") Integer id){
        deptService.deleteDept(id);
        return Result.success();
    }

}
