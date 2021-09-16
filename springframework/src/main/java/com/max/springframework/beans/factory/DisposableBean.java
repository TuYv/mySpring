package com.max.springframework.beans.factory;

/**
 * 销毁bean
 */
public interface DisposableBean {

    void destroy() throws Exception;
}
