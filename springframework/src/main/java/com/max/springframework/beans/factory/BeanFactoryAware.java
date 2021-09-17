package com.max.springframework.beans.factory;

import com.max.springframework.beans.BeansException;

/**
 * 实现此接口，既能感知到所属的 BeanFactory
 */
public interface BeanFactoryAware extends Aware {

   void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}