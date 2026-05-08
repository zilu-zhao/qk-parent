package com.qk.service.impl;

import com.qk.entity.OverviewVO;
import com.qk.mapper.BusinessMapper;
import com.qk.mapper.CuleMapper;
import com.qk.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private CuleMapper culeMapper;
//使用redis查询的思路：因为redis是存储在内存中，查询极快，为了提升性能，也增加redis，如果前端页面每次发来请求
    //都从数据库进行查询会有延迟 较慢，第一次从数据库查询，查询到的数据返回给redis，后边的请求直接从redis内进行查询返回给前端
    //redis存储的数据要设置有有效期，因为如果数据库的数据有变动，redis还是之前的老数据。
    //
    //创建redis对象
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    /*获取首页概览*/
    @Override
    public OverviewVO selectReport() {
        //先从redis内进行查找
        Object report = redisTemplate.opsForValue().get("report");
        if (report!=null){
            //如果在redis数据不为空 就把redis内的数据返回 就不用执行下边查询数据库了
            return (OverviewVO) report;
        }
        /*查找线索概览*/   //两次查询使用的同一个类 第一次查询使用了对象的前六个属性，第二次使用后六个
        OverviewVO overviewVO =culeMapper.getReportClue();

        /*查找商机概览*/
       OverviewVO overviewVO1 = businessMapper.getReportbusiness();
       //合并
        overviewVO.setBusinessTotal(overviewVO1.getBusinessTotal());
        overviewVO.setBusinessWaitAllot(overviewVO1.getBusinessWaitAllot());
        overviewVO.setBusinessWaitFollow(overviewVO1.getBusinessWaitFollow());
        overviewVO.setBusinessFollowing(overviewVO1.getBusinessFollowing());
        overviewVO.setBusinessFalse(overviewVO1.getBusinessFalse());
        overviewVO.setBusinessConvertCustomer(overviewVO1.getBusinessConvertCustomer());
       //如果redis内没有数据 还会执行下边语句从数据库进行查询
        //把数据库查询到的数据添加到redis内 这样下次查询会更快 直接从redis内查询了
        redisTemplate.opsForValue().set("report","overviewVO",20, TimeUnit.MINUTES);
        return overviewVO;
    }
}
