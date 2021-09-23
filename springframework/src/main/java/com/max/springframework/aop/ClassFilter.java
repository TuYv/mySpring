package com.max.springframework.aop;

/**
 * 用来区分类上的过滤器
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);

}
