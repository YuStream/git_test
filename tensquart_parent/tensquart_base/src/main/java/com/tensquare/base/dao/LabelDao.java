package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//JpaRepository 用于简单查询
//JpaSpecificationExecutor 动态拼接sql 用于复杂查询
public interface LabelDao extends JpaRepository<Label,String> ,JpaSpecificationExecutor<Label> {
}
