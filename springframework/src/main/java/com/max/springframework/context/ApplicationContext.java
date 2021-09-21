package com.max.springframework.context;

import com.max.springframework.beans.factory.HierarchicalBeanFactory;
import com.max.springframework.beans.factory.ListableBeanFactory;
import com.max.springframework.core.io.ResourceLoader;

/**
 * 应用上下文
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory,
    ResourceLoader, ApplicationEventPublisher {

}
