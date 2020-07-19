package com.example.demo.controller;

import com.example.demo.dao.MailcodeRepository;
import com.example.demo.dao.UserDao;
import com.example.demo.daoservice.UserService;
import com.example.demo.entity.Mail;
import com.example.demo.entity.User;
import com.example.demo.util.MailService;
import com.example.demo.util.code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/regist")
public class Regist {
    @Autowired
    MailcodeRepository codeRepository;
    @Autowired
    UserDao userDao;
    @Autowired
    MailService mailservice;
    @Autowired
    UserService userService;

    @GetMapping(value = "/code")
    public Map<String,String> code(@RequestParam String email) throws Exception {
        code Code=new code();
        Mail mail=codeRepository.findByEmail(email);
        if(mail==null) {
            Mail sMail = new Mail();
            sMail.setCheckstatus(0);
            String code=Code.GenerateCode();
            sMail.setCode(code);
            sMail.setEmail(email);
            Timestamp nowtime = new Timestamp(System.currentTimeMillis());
            sMail.setEndtime(new Timestamp(nowtime.getTime() + 60 * 1000));
            codeRepository.save(sMail);
            mailservice.sendmail(email, "您的验证码为" + code + "请在1分钟内验证。");
            Map<String, String> resmap = new HashMap<>();
            resmap.put("code", "200验证码已发送");
            return resmap;
        }
        String code = Code.GenerateCode();
        mail.setCheckstatus(0);
        mail.setCode(code);
        mail.setEmail(email);
        Timestamp nowtime = new Timestamp(System.currentTimeMillis());
        mail.setEndtime(new Timestamp(nowtime.getTime() + 60 * 1000));
        codeRepository.save(mail);
        mailservice.sendmail(email, "您的验证码为" + code + "请在1分钟内验证。");
        Map<String, String> resmap = new HashMap<>();
        resmap.put("code", "200验证码已发送");
        return resmap;
    }

    @GetMapping(value = "/check")
    public Map<String,String> check(@RequestParam String email,
                     @RequestParam String code){
        Map<String,String> resmap=new HashMap<>();
        Timestamp now=new Timestamp(System.currentTimeMillis());
        Mail Mail = codeRepository.findByEmail(email);
        if(Mail.getCode()==null){
            resmap.put("mes","找不到验证码");
            return  resmap;
        }
        else if(Mail.getCode().equals(code)&&now.before(Mail.getEndtime())){
            Mail.setCheckstatus(1);
            codeRepository.save(Mail);
            resmap.put("mes","验证成功");
            return  resmap;
        }
        else {
            resmap.put("mes","验证码错误");
            return resmap;
        }
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping(value = "/finish")
    public Map<String,String> finish(@RequestBody User user){
        Map<String,String> resmap=new HashMap<>();
        System.out.println(user);
        User auser = userService.findByUserName(user.getUsername());
        if(auser!=null) {
            resmap.put("mes","该账户已存在");
            return resmap;
        }
        Mail mail=codeRepository.findByEmail(user.getUsername());
        if(mail != null && mail.getCheckstatus() == 1){
            User suser = new User(user.getUsername(),passwordEncoder.encode(user.getPassword()), "ROLL_ALL");
            userDao.save(suser);
            mail.setCheckstatus(0);
            codeRepository.save(mail);
            resmap.put("mes","注册成功");
        }
        else{
            resmap.put("mes","请先获得验证码");
            return resmap;
        }
        return resmap;
    }

    @PatchMapping(value = "/repassword/{email}")
    public Map<String,String> repassword(
            @PathVariable String email,
            @RequestBody User user
    )  {
        Map<String,String> resmap=new HashMap<>();
        Mail mail=codeRepository.findByEmail(email);
        User auser=userService.findByUserName(email);
        if(mail != null&& mail.getCheckstatus() == 1) {
            auser.setPassword(passwordEncoder.encode(user.getPassword()));
            mail.setCheckstatus(0);
            codeRepository.save(mail);
            userDao.save(auser);
            resmap.put("mes","修改成功");
            return resmap;
        }
        else{
            resmap.put("mes","请先获取修改资格");
            return resmap;
        }
    }
}
