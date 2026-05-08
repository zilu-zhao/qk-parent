package com.qk.dto;

import lombok.Data;

@Data
public class CourseDto {
    private String name;
    private Integer subject;
    private Integer target;
    private Integer page=1 ; // 页码
    private Integer pageSize=10; // 每页条数
}
