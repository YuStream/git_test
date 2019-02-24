package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;

    public List<Enterprise> findByIshot(){
        List<Enterprise> list = enterpriseDao.findByIshot("1");
        return list;
    }
}
