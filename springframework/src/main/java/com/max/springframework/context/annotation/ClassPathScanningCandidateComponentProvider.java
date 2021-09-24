package com.max.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.max.springframework.beans.factory.config.BeanDefinition;
import com.max.springframework.stereotype.Component;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-24 12:14
 **/
public class ClassPathScanningCandidateComponentProvider {

    public Set<BeanDefinition> findCandidateComponent(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class< ? > clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
