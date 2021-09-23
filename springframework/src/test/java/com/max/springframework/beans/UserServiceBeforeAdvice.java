package com.max.springframework.beans;

import com.max.springframework.aop.framework.MethodBeforeAdvice;
import java.lang.reflect.Method;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-23 22:31
 **/
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法: " + method.getName());
    }
}
