package com.max.springframework.context.support;

import com.max.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @program: mySpring
 * @description: 自动配置应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:29
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;
}
