package com.tensquare.qa.controller;

import com.tensquare.qa.client.QaFeignClient;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/problem")
@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private QaFeignClient qaFeignClient;

    /**
     * 分页查询最新问题列表
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/newlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
    public Result findNewListByLabelId(@PathVariable String labelId, @PathVariable int page, @PathVariable int size){
        Page<Problem> list = problemService.findNewListByLabelId(labelId, page, size);
        PageResult<Problem> result = new PageResult<Problem>(list.getTotalElements(),list.getContent());
        return new Result(true, StatusCode.OK,"查询成功",result);
    }

    @RequestMapping("/label/{id}")
    private Result findById(@PathVariable(value = "id") String id){
        return qaFeignClient.findById(id);
    }
}
