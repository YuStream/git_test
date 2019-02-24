package com.tensquare.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("tensquare-base") //指定从哪个服务中调用功能
public interface QaFeignClient {
    @RequestMapping("/label/{id}")
    public Result findById(@PathVariable("id") String id);
}
