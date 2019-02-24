package com.tensquare.recruit.controller;

import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/recruit")
@RestController
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
    public Result findFirst4ByStateOrderByCreatetimeDesc(){
        List<Recruit> list = recruitService.findFirst4ByStateOrderByCreatetimeDesc("2");
        return new Result(true, StatusCode.OK,"查询成功",list);
    }

}
