package com.max.springframework.beans.factory.config;

import com.max.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 单例bean管理 和前置处理
 * @author max
 * @date 2021/9/15 15:49
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 增加bean前置处理
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    /**
     * 销毁单例对象
     */
    void destroySingletons();
}
