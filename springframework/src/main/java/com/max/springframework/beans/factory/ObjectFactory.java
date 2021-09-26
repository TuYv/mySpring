package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
