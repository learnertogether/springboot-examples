package com.zxl.examples.job;

import com.zxl.examples.service.UserSerivceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/27.
 */
@Component
public class UserJob {

    @Autowired
    UserSerivceImpl userSerivce;

    @Scheduled(cron="0/10 * * * * ?")
    private void printSomething(){
        System.out.println("------------------------------this is a test job");
    }

    @Scheduled(cron="0/10 * * * * ?")
    private void callService(){
        System.out.println("###############################this is a test job,the call service method value is : "
                +userSerivce.canCache());
    }

    @Scheduled(fixedRate = 10000)
    private void printSomething2(){
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^this is a test job2");
    }
}
