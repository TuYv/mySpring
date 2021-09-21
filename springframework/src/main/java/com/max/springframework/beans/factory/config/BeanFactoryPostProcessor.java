package com.max.springframework.beans.factory.config;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 提供一个接口允许用户自定义修改 BeanDefinition 属性信息
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}