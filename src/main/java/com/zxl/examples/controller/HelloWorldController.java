package com.zxl.examples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/7/19.
 */
@RestController
public class HelloWorldController {


    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "hello "+name;
    }


}
