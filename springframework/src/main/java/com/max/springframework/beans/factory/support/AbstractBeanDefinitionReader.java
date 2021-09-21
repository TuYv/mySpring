package com.max.springframework.beans.factory.support;

import com.max.springframework.core.io.DefaultResourceLoader;
import com.max.springframework.core.io.ResourceLoader;

/**
 * 申明bean信息read 需要的两个对象 bean信息的注册 和 资源获取的加载器
 * @author max
 * @date 2021/9/15 16:46
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
