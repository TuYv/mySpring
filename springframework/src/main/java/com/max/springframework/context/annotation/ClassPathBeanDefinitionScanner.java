package com.max.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.max.springframework.beans.factory.config.BeanDefinition;
import com.max.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.max.springframework.stereotype.Component;
import java.util.Set;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-24 12:17
 **/
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(
        BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponent(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                //解析 Bean 的作用域 singleton，prototype
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                registry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class< ? > beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class< ? > beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) return scope.value();
        return StrUtil.EMPTY;
    }
}
