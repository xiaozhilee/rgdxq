package com.example.demo.config;


import com.example.demo.daoservice.UserService;
import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetail <T extends User> implements UserDetailsService {


    private static UserService userService;
    @Autowired
    public void setUserService (UserService userService) {
        UserDetail.userService = userService;
    }
    private static Logger logger= LoggerFactory.getLogger("UserDetails");

    @Override
    public UserDetails loadUserByUsername (String Username) throws UsernameNotFoundException {

        logger.info("用户登录："+Username);
        User user=userService.findByUserName(Username);
        if(user == null) throw new UsernameNotFoundException("用户不存在！");
        List<GrantedAuthority> authorityList=new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(String.valueOf(user.getRole())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorityList);
    }


}
