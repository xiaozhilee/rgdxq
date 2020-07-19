package com.example.demo.dao;

import com.example.demo.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Transactional
@Repository
public interface MailcodeRepository extends JpaRepository<Mail, Integer> {
    Mail findByEmail(String email);
}
