package com.max.springframework.beans.factory.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.config.BeanDefinition;


/**
 * 实现自动装载bean
 * @author max
 * @date 2021/9/14 18:09
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        }catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName,bean);
        return bean;
    }
}
