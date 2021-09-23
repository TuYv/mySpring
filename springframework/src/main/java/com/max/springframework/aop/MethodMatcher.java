package com.max.springframework.aop;

import java.lang.reflect.Method;

/**
 * 检查目标方法是否符合条件
 */
public interface MethodMatcher {

    boolean matches(Method method, Class< ? > targetClass);
}
