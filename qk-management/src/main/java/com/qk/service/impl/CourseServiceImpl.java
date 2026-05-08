package com.qk.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.CourseDto;
import com.qk.entity.Course;
import com.qk.mapper.CourseMapper;
import com.qk.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageResult<Course> pageQuery(CourseDto courseDto) {
        Page<Course> page = new Page<>(courseDto.getPage(), courseDto.getPageSize());
        IPage<Course> courseIPage = courseMapper.selectCoursePage(page, courseDto);
        return new PageResult<>(courseIPage.getTotal(), courseIPage.getRecords());
    }
}
