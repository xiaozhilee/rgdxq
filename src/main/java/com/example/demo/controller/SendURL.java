package com.example.demo.controller;


import com.example.demo.daoservice.UrlInfoService;
import com.example.demo.entity.RoleEnum;
import com.example.demo.entity.URLInfo;
import com.example.demo.messagesend.MyWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class SendURL {

    private static final Logger logger = LoggerFactory.getLogger("SendURL");

    private static final UrlInfoService urlinfoservice = new UrlInfoService();
    private static MyWebSocket myWebSocket = new MyWebSocket();

    @PostMapping("/url")
    @RolesAllowed("ROLE_ADMIN")
    public void getAndSendURLToAll(@NotNull @RequestBody URLInfo newurl) {
        logger.info("开始保存新url....");
        System.out.println(newurl);
        SendURL.urlinfoservice.saveURL(newurl);
        logger.info("urls保存完成！");
        logger.info("开始推送新url....");
        SendURL.myWebSocket.sendToAllPhone(newurl.getUrl());
        logger.info("urls推送完成！");
    }

    @PostMapping("/urls")
    @RolesAllowed("ROLE_ADMIN")
    public void getAndSendURLSToAll(@NotNull @RequestBody List<URLInfo> newurls) {
        logger.info("开始保存新urls....");
        System.out.println(newurls);
        SendURL.urlinfoservice.saveAllURLS(newurls);
        logger.info("urls保存完成！");
        logger.info("开始推送新urls....");
        Iterator<URLInfo> infoIterator = newurls.iterator();
        List<String> urls = new ArrayList<>();
        while (infoIterator.hasNext()) {
            urls.add(infoIterator.next().getUrl());
        }
        SendURL.myWebSocket.sendToAllPhone(String.valueOf(urls));
        logger.info("urls推送完成！");
    }
}
