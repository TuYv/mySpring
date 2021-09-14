package com.max.springframework.beans.factory.config;

/**
 * @program: mySpring
 * @description: Bean的引用
 * @author: Max.Tu
 * @create: 2021-09-15 00:40
 **/
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
