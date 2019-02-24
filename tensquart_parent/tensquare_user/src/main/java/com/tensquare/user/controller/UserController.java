package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/sendsms/{phoneNum}",method = RequestMethod.POST)
    public Result sendSms(@PathVariable String phoneNum){
        userService.sengSms(phoneNum);
        return new Result(true, StatusCode.OK,"发送成功");
    }

    @RequestMapping(value = "/register/{code}",method = RequestMethod.POST)
    public Result add(@RequestBody User user, @PathVariable String code){
        userService.add(user,code );
        return new Result(true,StatusCode.OK,"添加成功");

    }
}
