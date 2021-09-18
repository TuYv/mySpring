package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;

public interface FactoryBean<T> {

    T getObject() throws BeansException;

    Class<T> getObjecType();

    boolean isSingleton();
}
