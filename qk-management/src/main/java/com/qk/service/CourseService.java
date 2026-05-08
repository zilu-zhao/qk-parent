package com.qk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.CourseDto;
import com.qk.entity.Course;

public interface CourseService extends IService<Course> {

    PageResult<Course> pageQuery(CourseDto courseDto);
}
