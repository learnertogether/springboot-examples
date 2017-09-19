package com.zxl.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Administrator on 2017/7/19.
 */
//@RestController
//@EnableAutoConfiguration
//@ComponentScan
    @SpringBootApplication ////与@Configuration @EnableAutoConfiguration相同@ComponentScan
    @EnableScheduling
public class MyApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApplication.class, args);
    }

//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable String name){
//        return "hello "+name;
//    }
}
