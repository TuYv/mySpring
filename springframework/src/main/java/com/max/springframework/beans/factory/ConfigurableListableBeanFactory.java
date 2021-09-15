package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.max.springframework.beans.factory.config.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
