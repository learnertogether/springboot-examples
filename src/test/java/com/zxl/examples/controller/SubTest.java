package com.zxl.examples.controller;

/**
 * Created by Administrator on 2017/9/14.
 */
public class SubTest extends TestController{


    @Override
    public synchronized void start() {
        System.out.println("SubTest start.");
        super.start();
    }

    @Override
    public void run() {
        System.out.println("SubTest run.");
    }
}
