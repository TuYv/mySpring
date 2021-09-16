package com.max.springframework.beans.factory.config;

import com.max.springframework.beans.BeansException;

/**
 * 修改新实例化的bean 扩展bean
 */
public interface BeanPostProcessor {

    /**
     * 在 bean对象初始化之前执行
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
