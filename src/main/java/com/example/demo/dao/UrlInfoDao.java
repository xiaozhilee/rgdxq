package com.example.demo.dao;

import com.example.demo.entity.URLInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UrlInfoDao extends JpaRepository<URLInfo,String> {
    @Modifying
    @Query(value = "update urlinfo set error_count=error_count+1 where url = ?1",nativeQuery = true)
    void updateErrorCountByUrl(String url);

    @Modifying
    @Query(value = "update urlinf set error_count =0 where url=?1" ,nativeQuery = true)
    void setZero(String url);

    URLInfo findByUrl(String url);

    void deleteByUrl(String url);

}
