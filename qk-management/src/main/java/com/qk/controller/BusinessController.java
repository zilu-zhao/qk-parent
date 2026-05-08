/*
package com.qk.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.BusinessDto;
import com.qk.entity.Business;
import com.qk.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @Autowired
    private BusinessService businessService;
@GetMapping("/businesses")
    public Result businessPageSelect(BusinessDto businessDto){
      PageResult<Business> pageResult =businessService.businessPageSelect(businessDto);
        return Result.success(pageResult);
    }
}

*/
