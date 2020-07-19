package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
    @Autowired
    JavaMailSender mailSender;
    public  void sendSimpleMail(String toMail,String message){


        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("2491061139@qq.com");
            simpleMailMessage.setTo(toMail);
            simpleMailMessage.setSubject("出错信息");
            simpleMailMessage.setText(message);
            mailSender.send(simpleMailMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
