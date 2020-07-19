package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class Page {

    @GetMapping("/page/{pagename}")
    public String regist(@PathVariable String pagename) {
        return "html/" + pagename +".html";
    }

}
