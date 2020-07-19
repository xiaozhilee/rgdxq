package com.example.demo.controller;

import com.example.demo.entity.Expense;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@RestController
public class JHtest {

/*    @RequestMapping(value = "/JHtest",method = {RequestMethod.GET,RequestMethod.POST})
    public String test(@RequestBody String text) throws JSONException {
        System.out.println(text);
        return "{\"code\":\"200\"}";
    }*/

    @RequestMapping(value = "/JHtest",method = RequestMethod.GET)
    @RolesAllowed("ROLE_ALL")
    public String test1() throws JSONException {
        System.out.println("text");
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{\"code\":\"200\"}";
    }

    @RequestMapping(value = "/JHtest",method = RequestMethod.POST)
    @RolesAllowed("ROLE_ALL")
    public String test2(@RequestBody Expense expense) throws JSONException {
        System.out.println(expense);
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{\"code\":\"200\"}";
    }

    @RequestMapping(value = "/JHtest/error",method = RequestMethod.GET)
    @RolesAllowed("ROLE_ALL")
    public String test3() throws JSONException {
        System.out.println("text");
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{\"code\":\"400\"}";
    }

    @RequestMapping(value = "/JHtest/error",method = RequestMethod.POST)
    @RolesAllowed("ROLE_ALL")
    public String test4(@RequestBody Expense expense) throws JSONException {
        System.out.println(expense);
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "{\"code\":\"400\"}";
    }
}

