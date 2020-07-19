package com.example.demo.controller;

import com.example.demo.daoservice.UrlInfoService;
import com.example.demo.entity.URLInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetAllURLS {

    private static final Logger logger = LoggerFactory.getLogger("GetAllURLS");
    private static final UrlInfoService urlinfoservice = new UrlInfoService();
    @GetMapping("/urls")
    public List<URLInfo> snedUrls() {
        logger.info("send urls...");
        List<URLInfo> list = urlinfoservice.fingAllURLS();
        return list;
    }

}


