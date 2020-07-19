package com.example.demo.controller;

import com.example.demo.dao.ExpenseDao;
import com.example.demo.dao.UrlInfoDao;
import com.example.demo.entity.Expense;
import com.example.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExpenseController {
    @Autowired
    UrlInfoDao urlInfoDao;
    @Autowired
    ExpenseDao expenseDao;
    @Autowired
    MailUtil mailUtil;
    @PostMapping("/expense")
    public Map<String,String> createExpense(@RequestBody Expense expense){
        Map<String,String> map = new HashMap<>();
        expenseDao.save(expense);
        if(expense.getType()==0){
            urlInfoDao.updateErrorCountByUrl(expense.getUrl());
            if(urlInfoDao.getOne(expense.getUrl()).getErrorCount()%100==0){
                List<Expense> expenses=expenseDao.getErrorMessageByUrl(expense.getUrl());
                String message = expenses.get(0).getUrl()+"出错\n"
                        +expenses.get(0).getDdate()+" "+expenses.get(0).getMessage()+"\n"
                        +expenses.get(1).getDdate()+" "+expenses.get(1).getMessage()+"\n"
                        +expenses.get(2).getDdate()+" "+expenses.get(2).getMessage()+"\n";
                mailUtil.sendSimpleMail(urlInfoDao.getOne(expense.getUrl()).getMail(),message);

            }

        }
        map.put("code","200");
        return map;
    }

    @PostMapping("/expenses")
    public List<Expense> getExpenses(@RequestBody UrlModel urlModel){

        System.out.println(urlModel.getUrl());
        return expenseDao.getMessageByUrl(urlModel.getUrl());
    }
}
