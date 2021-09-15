package com.max.springframework.beans.factory.config;

import com.max.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author max
 * @date 2021/9/15 15:49
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
