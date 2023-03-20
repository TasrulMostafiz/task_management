package com.example.task_management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class TaskAop {
    Logger logger = LoggerFactory.getLogger(TaskAop.class);

    @Before("execution(* com.example.task_management.service.impl.TaskServiceImpl+.*(..))")
    public void before(JoinPoint joinPoint){
        logger.info("Before - "+joinPoint.getSignature().getName());
        logger.info("Before - "+joinPoint.getTarget().getClass());
        logger.info("Before - "+ Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.example.task_management.service.impl.TaskServiceImpl+.*(..))")
    public void after(JoinPoint joinPoint){
        logger.info("After - "+joinPoint.getTarget().getClass());
        logger.info("After - "+joinPoint.getSignature().getName());
    }

    @AfterReturning(value = "execution(* com.example.task_management.service.impl.TaskServiceImpl+.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result){
        logger.info("After Return - "+joinPoint.getTarget().getClass());
        logger.info("After Return - "+joinPoint.getSignature().getName());
        logger.info("Returned - "+result);
    }

    @AfterThrowing(value = "execution(* com.example.task_management.service.impl.TaskServiceImpl+.*(..))",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        logger.info("After Throwing - "+joinPoint.getTarget().getClass());
        logger.info("After Throwing - "+joinPoint.getSignature().getName());
        logger.info("Exception - "+ex.getMessage());
    }
}
