package com.zxl.examples.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/8/3.
 */
@Aspect
@Component
public class LogAspect {

    /**
     *
     * execution(* com.sample.service.impl..*.*(..))
     解释如下：
     符号	含义
     execution（）
     表达式的主体；
     第一个”*“符号
     表示返回值的类型任意；
     com.sample.service.impl	AOP所切的服务的包名，即，我们的业务部分
     包名后面的”..“	表示当前包及子包
     第二个”*“	表示类名，*即所有类。此处可以自定义，下文有举例
     .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型

     execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)  除了返回类型模式、方法名模式和参数模式外，其它项都是可选的。
     *
     *
     *
     * @param joinPoint
     */


    @AfterReturning("execution(* com.zxl.examples.service..*.*(..))")
    public void logServiceAccessAfterReturning(JoinPoint joinPoint) {
        System.out.println("Completed: " + joinPoint);
    }

//    @Before("execution(* com.zxl.examples.service..*.*(..))")
    public void logServiceAccessBefore(JoinPoint joinPoint){
        System.out.println("Before: " + joinPoint);
    }

    @After("execution(* com.zxl.examples.service..*.*(..))")
    public void logServiceAccessAfter(JoinPoint joinPoint){
        System.out.println("After: " + joinPoint);
    }

    /**
     * around 开启后会覆盖before
     * @param joinPoint
     */
//    @Around("execution(* com.zxl.examples.service..*.*(..))")
    public void logServiceAccessAround(JoinPoint joinPoint){
        System.out.println("Around: " + joinPoint);
    }

    //声明一个切入点
    @Pointcut("execution(* com.zxl.examples.service..*.*(..))")
    public void logServiceAccessPointCut(){
    }

    @Before("logServiceAccessPointCut()")
    public void losgUseThePointCut(JoinPoint joinPoint){
        System.out.println("THIS IS USE THE POINT CUT: " + joinPoint);
    }



}
