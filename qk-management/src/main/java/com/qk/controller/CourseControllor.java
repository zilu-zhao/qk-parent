package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.CourseDto;
import com.qk.entity.Course;
import com.qk.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/courses")
public class CourseControllor {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public Result pageQuery(CourseDto courseDto) {
        log.info("课程分页查询参数：{}", courseDto);
        PageResult<Course> pageResult = courseService.pageQuery(courseDto);
        return Result.success(pageResult);
    }
}
