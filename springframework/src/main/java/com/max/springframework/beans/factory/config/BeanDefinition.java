package com.max.springframework.beans.factory.config;

/**
 * 存储bean的信息
 * @author max
 * @date 2021/9/14 17:21
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
