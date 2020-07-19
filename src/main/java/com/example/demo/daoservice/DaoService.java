package com.example.demo.daoservice;

import com.example.demo.entity.URLInfo;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DaoService {
    void saveAllURLS(List<URLInfo> urls);
    void saveURL(URLInfo urls);
    List<URLInfo> fingAllURLS();
    User findByUserName(String username);
}
