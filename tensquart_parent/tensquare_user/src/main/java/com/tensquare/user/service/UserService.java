package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //生成随机验证码,存到redis 并转发给rabbitMQ
    public void sengSms(String phoneNum){
        String code = String.valueOf(new Random().nextInt(899999) + 100000);

        redisTemplate.opsForValue().set("smscode_"+phoneNum,code,5, TimeUnit.MINUTES);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("phoneNum",phoneNum );
        map.put("code",code );
        rabbitTemplate.convertAndSend("sms",map );
    }

    //用户输入验证码后 点击注册
    public void add(User user, String code){
        //根据手机号码 去redis中查询验证码
        String sysCode = (String) redisTemplate.opsForValue().get("smscode_" + user.getMobile());
        if (sysCode == null){
            throw new RuntimeException("请点击获取验证码");
        }
        if (!code.equals(sysCode)){
            throw new RuntimeException("验证码不正确");
        }
        user.setId(idWorker.nextId()+"");
        user.setFollowcount(0);//关注数
        user.setFanscount(0);//粉丝数
        user.setOnline(0L);//在线时长
        user.setRegdate(new Date());//注册日期
        user.setUpdatedate(new Date());//更新日期
        user.setLastdate(new Date());//最后登陆日期
        user.setPassword(encoder.encode(user.getPassword()));

        userDao.save(user);
    }
}
