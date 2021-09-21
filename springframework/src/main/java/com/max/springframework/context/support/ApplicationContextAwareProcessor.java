package com.max.springframework.context.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.config.BeanPostProcessor;
import com.max.springframework.context.ApplicationContext;
import com.max.springframework.context.ApplicationContextAware;

/**
 * @program: mySpring
 * @description: 包装处理器
 * @author: Max.Tu
 * @create: 2021-09-17 22:32
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(
        ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }
}
