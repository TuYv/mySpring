package com.max.springframework.beans.factory;

public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<T> getObjecType();

    boolean isSingleton();
}
