package com.example.demo.controller;


import antlr.StringUtils;
import com.example.demo.dao.ExpenseDao;
import com.example.demo.dao.UrlInfoDao;
import com.example.demo.entity.CodeModel;
import com.example.demo.entity.Expense;
import com.example.demo.entity.URLInfo;
import com.example.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/surl")
public class URLController {
    @Autowired
    UrlInfoDao urlInfoDao;
    @Autowired
    ExpenseDao expenseDao;
    @Autowired
    MailUtil mailUtil;

    @GetMapping("/surls")
    public List<URLInfo> getURLInfo(){
        List<URLInfo> urlInfos = urlInfoDao.findAll();
        Timestamp end, start;
        int a, b;
        double c = 0;
        URLInfo tmpurl ;
        for (int i = 0; i < urlInfos.size(); i++) {
            end = new Timestamp(System.currentTimeMillis());
            start = new Timestamp(end.getTime() - 60*1000);
            tmpurl = urlInfos.get(i);
            a = expenseDao.type0num(tmpurl.getUrl(),start,end);
            b = expenseDao.type1num(tmpurl.getUrl(),start,end);
            c =  b*1.00 / (a + b) ;
            if(a==0 && b ==0) {
                urlInfos.get(i).setErrorCount(0);
            } else if( c > 0.6) {
                urlInfos.get(i).setErrorCount(1);
            } else {
                urlInfos.get(i).setErrorCount(2);
            }
        }
        return urlInfos;
    }

    @PostMapping("/surl")
    public void createURL(@NotNull @RequestBody URLInfo urlInfo){
        urlInfo.setErrorCount(0);
        urlInfo.setDdate(new Date());
        urlInfo.setUrl(urlInfo.getUrl().replace("\r","").replace("\n",""));
        urlInfoDao.save(urlInfo);
    }

    @DeleteMapping("/surl")
    public Map<String,String> deleteUrl(@NotNull @RequestBody URLInfo urlInfo) {
        Map<String,String> resultMap = new HashMap<>();
        if (!urlInfo.getUrl().isEmpty()) {
            try {
                urlInfoDao.deleteByUrl(urlInfo.getUrl());
            }catch (RuntimeException exception) {
                exception.printStackTrace();
                resultMap.put("code","400");
                resultMap.put("message","SQL 执行失败");
                return resultMap;
            }
        } else {
            resultMap.put("code","404");
            resultMap.put("message","找不到url");
            return resultMap;
        }
        resultMap.put("code","200");
        resultMap.put("message","删除成功");
        return resultMap;
    }

    @PostMapping("/urlcode")
    public Map<String,String> aboutUrl(@NotNull @RequestBody URLInfo urlInfo) {
        Date end = new Date();
        Date start = new Date(end.getTime() - 60*1000);
        int a = expenseDao.type0num(urlInfo.getUrl(),end,start);
        int b = expenseDao.type1num(urlInfo.getUrl(),end,start);
        System.out.println(b+ "\t" +a);
        Map<String,String> resultMap = new HashMap<>();
        System.out.println(a);
        double c = 0;
        try {
            c =  b*1.00 / (a + b) ;
        }catch (ArithmeticException arithmeticException) { }

        if(a==0 && b ==0) {
            {
                resultMap.put("code", "0");
                resultMap.put("cwl", String.valueOf(c));
                return resultMap;
            }
        }
        if( c > 0.6) {
            resultMap.put("code", "1");
            resultMap.put("cwl", String.valueOf(c));
            return resultMap;
        }else {
            resultMap.put("code", "2");
            resultMap.put("cwl", String.valueOf(c));
            return resultMap;
        }


    }

/*    @GetMapping("/scode/{url}")//获取当前url的code和key
    public CodeModel getCodeModel(@PathVariable("url") String url){
        System.out.println(url);
        return new CodeModel(urlInfoDao.getOne(url).getKkey(),urlInfoDao.getOne(url).getCode());
    }*/
   /* @PostMapping("/expense")
    public void createExpense(@RequestBody Expense expense){

        expenseDao.save(expense);
        if(expense.getType()==0){
            urlInfoDao.updateErrorCountByUrl(expense.getUrl());
            if(urlInfoDao.getOne(expense.getUrl()).getErrorCount()%3==0){
                List<Expense> expenses=expenseDao.getErrorMessageByUrl(expense.getUrl());
                String message = expenses.get(0).getUrl()+"出错\n"
                        +expenses.get(0).getDdate()+" "+expenses.get(0).getMessage()+"\n"
                        +expenses.get(1).getDdate()+" "+expenses.get(1).getMessage()+"\n"
                        +expenses.get(2).getDdate()+" "+expenses.get(2).getMessage()+"\n";
                mailUtil.sendSimpleMail(urlInfoDao.getOne(expense.getUrl()).getMail(),message);

            }

        }
    }*/


}
