package com.qk.dto;

import lombok.Data;
/*封装查询参数*/
@Data
public class RoleDto {
    private String name;
    private  String label;
    private Integer page = 1; // 页码
    private Integer pageSize = 10; // 每页条数
}
