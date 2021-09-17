package com.max.springframework.beans.factory.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.BeanFactory;
import com.max.springframework.beans.factory.config.BeanDefinition;
import com.max.springframework.beans.factory.config.BeanPostProcessor;
import com.max.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.max.springframework.util.ClassUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * bean工厂的虚拟类
 * @author max
 * @date 2021/9/14 18:02
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegister implements ConfigurableBeanFactory {
    /** ClassLoader to resolve bean class names with, if necessary */
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor>getBeanPostProcessors() {
        return beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
