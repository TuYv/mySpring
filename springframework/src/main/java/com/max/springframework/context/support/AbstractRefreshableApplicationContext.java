package com.max.springframework.context.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.max.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 实现了刷新/配置 BeanFactory的功能，管理了一个DefaultListableBeanFactory对象
 * @program: mySpring
 * @description: 自动配置应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:29
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{
    private DefaultListableBeanFactory beanFactory;

    /**
     * 创建一个beanFactory并加载BeanDefinitions
     * @throws BeansException
     */
    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
