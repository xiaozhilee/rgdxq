package com.example.demo.daoservice;

import com.example.demo.dao.UrlInfoDao;
import com.example.demo.entity.URLInfo;
import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Service
public class UrlInfoService implements DaoService{

    private static UrlInfoDao urlinfodao;
    private static final Logger logger = LoggerFactory.getLogger("URLInfoService");
    @Autowired
    public void setRepository(UrlInfoDao urlinfodao) {
        UrlInfoService.urlinfodao = urlinfodao;
    }


    @Override
    public void saveURL(@NotNull URLInfo url) {
        url.setErrorCount(0);
        urlinfodao.save(url);
    }

    @Override
    public void saveAllURLS(List<URLInfo> urls) {
        Iterator<URLInfo> infoIterator = urls.iterator();
        URLInfo urlInfo = null;
        while (infoIterator.hasNext()) {
            urlInfo = infoIterator.next();
            urlInfo.setErrorCount(0);
            urlinfodao.save(urlInfo);
        }
    }

    @Override
    public List<URLInfo> fingAllURLS() {
        List<URLInfo> allurl = new ArrayList<>();
        allurl = urlinfodao.findAll();
        return allurl;
    }

    @Override
    public User findByUserName(String username) {
        return null;
    }
}
