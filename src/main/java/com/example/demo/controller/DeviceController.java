package com.example.demo.controller;

import com.example.demo.dao.DeviceDao;
import com.example.demo.dao.ExpenseDao;
import com.example.demo.dao.UrlInfoDao;
import com.example.demo.entity.URLInfo;
import com.example.demo.entity.device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;


@RestController
public class DeviceController {
    @Autowired
    ExpenseDao expenseDao;
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    UrlInfoDao urlInfoDao;


    @Scheduled(fixedDelay = 3000)
    public void fixedDelay() {
        Timestamp start = new Timestamp(System.currentTimeMillis());
        Timestamp end = new Timestamp(start.getTime() - 3*1000);
        List<URLInfo> list = urlInfoDao.findAll();
        Iterator<URLInfo> infoIterator = list.iterator();
        URLInfo urlInfo;
        Double dd;
        device thedevice;
        while (infoIterator.hasNext()) {
            urlInfo = infoIterator.next();
            System.out.println(urlInfo.getUrl()+"\t"+start+"\t"+end);
            dd = expenseDao.AVGdevice(urlInfo.getUrl(),end,start);
            if (dd == null ) return ;
            thedevice = new device();
            thedevice.setAdevice(String.valueOf(dd));
            thedevice.setUrl(urlInfo.getUrl());
            deviceDao.save(thedevice);
        }
    }

    @PostMapping("/devices")
    public List<String> getdevice(@RequestBody URLInfo url) {
        List<String> tmp;
        tmp = deviceDao.fingDeviceByUrl(url.getUrl());
        return tmp;
    }
}
