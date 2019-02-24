package com.tensquare.firend.dao;

import com.tensquare.firend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FirendDao extends JpaRepository<Friend,String> {
    @Modifying
    @Query("update Friend set islike=?3 where userid=?1 and friendid=?2")
    public void updateLike(String userid,String friendid ,String islike);

}
