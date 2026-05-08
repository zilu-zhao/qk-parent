package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.ClueQueryDto;
import com.qk.entity.Clue;
import com.qk.service.CuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/clues")
public class CuleController {
    @Autowired
  private CuleService culeService;
    /*线索添加*/
    @PostMapping
    public Result addClues(@RequestBody Clue clue){
        clue.setStatus(1);
        clue.setCreateTime(LocalDateTime.now());
        clue.setUpdateTime(LocalDateTime.now());
        culeService.save(clue);
        return Result.success();
    }
    /*列表分页查询*/
    @GetMapping
    public  Result listSelect(ClueQueryDto clueQueryDto){
        PageResult<Clue> pageResult = culeService.listSelect(clueQueryDto);
        return Result.success(pageResult);
    }
    /*分配线索 ，修改*/
    @PutMapping("/assign/{clueId}/{userId}")
    public Result assignClue(@PathVariable Integer clueId,@PathVariable Integer userId){
        Clue clue = new Clue();
        clue.setId(clueId);//查询线索是否存在
        clue.setStatus(2);//状态由无人跟进变为待跟进
        clue.setUserId(userId);//设置归属人ID
        clue.setUpdateTime(LocalDateTime.now());//更改修改时间
        culeService.updateById(clue);
        return Result.success();

    }
    /*根据ID进行查询--回显  需要的数据有Clue.* 线索跟进表全部+跟进人（user.name）*/
    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        Clue clue=culeService.selectById(id);
        return Result.success(clue);
    }

    /*线索跟进*/
    @PutMapping
    public Result trackClue(@RequestBody Clue clue){
        culeService.trackClue(clue);
     return  Result.success();
    }
    /*转商机  修改+提交*/
   @PutMapping("/toBusiness/{clueid}")
    public  Result toBUsiness(@PathVariable("clueid") Integer clueid){
       culeService.toBusiness(clueid);
       return Result.success();
   }
}
