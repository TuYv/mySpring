package com.max.springframework.beans.factory.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.config.BeanDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: mySpring
 * @description: JDK实例化策略
 * @author: Max.Tu
 * @create: 2021-09-14 22:50
 **/
public class SimpleInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,
        Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (ctor == null) {
                return clazz.getDeclaredConstructor().newInstance();
            } else {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }
}
