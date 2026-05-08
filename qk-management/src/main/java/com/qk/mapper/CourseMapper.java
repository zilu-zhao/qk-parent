package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.dto.CourseDto;
import com.qk.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    IPage<Course> selectCoursePage(Page<Course> page, @Param("dto") CourseDto courseDto);
//    IPage<Course> selectCoursePage(Page<Course> page, @Param("dt
}
