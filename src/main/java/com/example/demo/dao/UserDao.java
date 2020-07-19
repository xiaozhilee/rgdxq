package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Repository
@Transactional

public interface UserDao extends JpaRepository<User,String> {
    User findByUsername(String username);
}
