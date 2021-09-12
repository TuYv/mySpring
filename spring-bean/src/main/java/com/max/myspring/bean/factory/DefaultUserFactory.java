package com.max.myspring.bean.factory;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;

/**
 * @program: mySpring
 * @description: 默认实现
 * @author: Max.Tu
 * @create: 2021-09-09 21:24
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean {

    //1. 基于@PostConstruct 注解实现
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct : UserFactory 初始化中...");
    }

    @Override
    public void initUserFactory() {
        System.out.println("自定义初始化方法initUserFactory() : UserFactory 初始化中...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() : UserFactory 初始化中...");
    }
}
