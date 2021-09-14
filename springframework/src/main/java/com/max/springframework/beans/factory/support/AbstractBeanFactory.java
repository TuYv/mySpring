package com.max.springframework.beans.factory.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.BeanFactory;
import com.max.springframework.beans.factory.config.BeanDefinition;

/**
 * bean工厂的虚拟类
 * @author max
 * @date 2021/9/14 18:02
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegister implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
