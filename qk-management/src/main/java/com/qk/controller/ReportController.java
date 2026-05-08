package com.qk.controller;

import com.qk.common.Result;
import com.qk.entity.OverviewVO;
import com.qk.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    /*获取首页概览*/
    @GetMapping("/overview")
    public Result selectReport(){
      OverviewVO overviewVO =reportService.selectReport();
        return  Result.success(overviewVO);
    }
}
