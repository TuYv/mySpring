package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;

import java.util.Map;

/**
 * 申明了根据type获取bean列表的方法 和 返回所有bean name的方法
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T>Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表里的所有bean名称
     * @return
     */
    String[] getBeanDefinitionNames();
}
