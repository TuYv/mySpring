package com.max.springframework.aop;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-22 11:18
 **/
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
