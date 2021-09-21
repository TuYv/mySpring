package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;

/**
 * Bean管理的开始 申明了获取bean的几种方法
 * @author max
 * @date 2021/9/14 17:24
 */
public interface BeanFactory {
        Object getBean(String name) throws BeansException;

        Object getBean(String name, Object... args) throws BeansException;

        <T> T getBean(String name, Class<T> requiredType) throws BeansException;

}
