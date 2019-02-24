package com.tensquare.user.service;

import com.tensquare.user.dao.AdminDao;
import com.tensquare.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Admin fingByLoginnameAndPassword(String loginname,String password){
        Admin admin = adminDao.findByLoginname(loginname);
        if (admin!=null && encoder.matches(password,admin.getPassword() )){
            return admin;
        }
        return null;
    }
}
