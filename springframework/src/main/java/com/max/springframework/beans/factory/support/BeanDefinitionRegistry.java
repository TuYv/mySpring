package com.max.springframework.beans.factory.support;

import com.max.springframework.beans.factory.config.BeanDefinition;

/**
 * bean的注册
 */
public interface BeanDefinitionRegistry {
    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
