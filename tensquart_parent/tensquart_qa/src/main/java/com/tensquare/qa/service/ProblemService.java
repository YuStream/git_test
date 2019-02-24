package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProblemService {
    @Autowired
    private ProblemDao problemDao;

    /**
     * 分页查询最新问题列表
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    public Page<Problem> findNewListByLabelId(String labelId,int page,int size){
        PageRequest request = PageRequest.of(page-1,size);
        return problemDao.findNewListByLabelId(labelId,request );
    }
}
