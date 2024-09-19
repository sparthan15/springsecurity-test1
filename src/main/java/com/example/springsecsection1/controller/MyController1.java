package com.example.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController1 {

    @GetMapping("test1")
    public String test1(){
        return "test1";
    }

    @GetMapping("test2")
    public String test2(){
        return "test2";
    }

}
