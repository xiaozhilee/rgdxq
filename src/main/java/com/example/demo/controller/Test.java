package com.example.demo.controller;


import com.example.demo.dao.ExpenseDao;
import com.example.demo.testexample.TestModel1;
import com.example.demo.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class Test {

    @Autowired
    ExpenseDao expenseDao;

    @Autowired
    MailUtil mailUtil;
    @GetMapping("/test1")
    public TestModel1 dd(){
        List<TestModel1> testModel1s = new LinkedList<>();
        testModel1s.add(new TestModel1("200","你真棒"));
        testModel1s.add(new TestModel1("200","你真棒"));
        testModel1s.add(new TestModel1("200","你真棒"));
        testModel1s.add(new TestModel1("2","错了"));
        testModel1s.add(new TestModel1("4","你太菜"));
        testModel1s.add(new TestModel1("9","不太想"));
        testModel1s.add(new TestModel1("3","网络慢"));
        testModel1s.add(new TestModel1("200","你真棒"));
        testModel1s.add(new TestModel1("5","还行"));
        testModel1s.add(new TestModel1("200","你真棒"));
        Random random =new Random();
        int n =random.nextInt(10);
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(testModel1s.get(n).getErrorCode()+" "+testModel1s.get(n).getMessage());
        return testModel1s.get(n);
    }


}
