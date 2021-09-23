package com.max.springframework.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
