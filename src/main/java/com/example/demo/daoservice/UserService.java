package com.example.demo.daoservice;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.URLInfo;
import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements DaoService{

    private static UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger("UserService");

    @Autowired
    public void setUserDao(UserDao userDao) {
        UserService.userDao = userDao;
    }

    @Override
    public void saveAllURLS(List<URLInfo> urls) {

    }

    @Override
    public void saveURL(URLInfo urls) {

    }

    @Override
    public List<URLInfo> fingAllURLS() {
        return null;
    }

    @Override
    public User findByUserName(String username) {
        User user = userDao.findByUsername(username);
        return user;
    }
}
