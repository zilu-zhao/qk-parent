package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qk.entity.Business;
import com.qk.entity.OverviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*商机管理Mapper*/
@Mapper
public interface BusinessMapper extends BaseMapper<Business> {
    /*查询商机线索概览*/
    @Select("select\n" +
            "            count(*) as business_total,\n" +
            "            sum(if(status = 1, 1, 0)) as business_wait_allot,\n" +
            "            sum(if(status = 2, 1, 0)) as business_wait_follow,\n" +
            "            sum(if(status = 3, 1, 0)) as business_following,\n" +
            "            sum(if(status = 4, 1, 0)) as business_false,\n" +
            "            sum(if(status = 5, 1, 0)) as business_convert_customer\n" +
            "        from business"
    )
    OverviewVO getReportbusiness();
}
