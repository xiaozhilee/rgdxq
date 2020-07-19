package com.example.demo.dao;

import com.example.demo.entity.device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DeviceDao extends JpaRepository<device,String> {
    @Query(value = "select adevice from device where url = ?1 order by devicedate desc limit 0,100",nativeQuery = true)
    List<String> fingDeviceByUrl(String url);
}
