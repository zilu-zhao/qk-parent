package com.qk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.common.PageResult;
import com.qk.dto.ClueQueryDto;
import com.qk.entity.Clue;
import com.qk.entity.OverviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CuleMapper extends BaseMapper<Clue> {
    
    IPage<Clue> listselect(Page<Clue> cluePage, ClueQueryDto clueQueryDto);

    Clue selectByidd(Integer id);
@Select("select count(*) as clue_total ,\n" +
        "       sum(if(status=1,1,0)) as clue_wait_allot,\n" +
        "       sum(if(status=2,1,0))as clue_wait_follow,\n" +
        "       sum(if(status=3,1,0)) as  clue_following,\n" +
        "       sum(if(status=4,1,0)) as  clue_false,\n" +
        "       sum(if(status=5,1,0)) as clue_convert_Business\n" +
        "from clue")
    OverviewVO getReportClue();
}
