package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;

/**
 * @author max
 * @date 2021/9/14 17:24
 */
public interface BeanFactory {
        Object getBean(String name) throws BeansException;

        Object getBean(String name, Object... args) throws BeansException;

}
