package com.qk.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.entity.Clue;
/*
import com.qk.mapper.BusinessMapper;
*/
/*import com.qk.service.BusinessService;*//*

import com.qk.service.BusinessService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper, Business> implements BusinessService {
//商机列表分页查询

    @Override
    public PageResult<Business> businessPageSelect(BusinessDto businessDto) {
*/
/*         Page<Clue> cluePage = new Page<>(clueQueryDto.getPage(), clueQueryDto.getPageSize());
        //调用baseMapper的自定义方法listselect方法进行查询，返回的是查询到的Clue对象列表
        IPage<Clue> page = baseMapper.listselect(cluePage,clueQueryDto);
        //将查询到的列表的总数和列表返回给controller
        return new PageResult<>(page.getTotal(),page.getRecords());*//*


        Page<Business> businessPage = new Page<>(businessDto.getPage(), businessDto.getPageSize());
        return null;
    }
}
*/
