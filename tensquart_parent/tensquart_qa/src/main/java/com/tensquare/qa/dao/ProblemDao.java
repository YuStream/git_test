package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem> {

    /**
     * 最新回复的问题表显示在上端,按回复时间降序排列
     * 查询最新问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p where p.id in(select pl.problemid from PL pl where pl.labelid=?1) order by p.replytime desc")
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);
}
