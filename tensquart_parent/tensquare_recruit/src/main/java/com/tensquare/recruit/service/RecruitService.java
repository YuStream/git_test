package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;

    public List<Recruit> findFirst4ByStateOrderByCreatetimeDesc(String state){
         return recruitDao.findFirst4ByStateOrderByCreatetimeDesc(state);
    }
}
