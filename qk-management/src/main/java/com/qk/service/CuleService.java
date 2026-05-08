package com.qk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.ClueQueryDto;
import com.qk.entity.Clue;

public interface CuleService extends IService<Clue> {
    PageResult<Clue> listSelect(ClueQueryDto clueQueryDto);

    Clue selectById(Integer id);

    void trackClue(Clue clue);

    void toBusiness(Integer clueid);
}
