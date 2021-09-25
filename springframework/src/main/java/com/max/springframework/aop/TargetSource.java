package com.max.springframework.aop;

import com.max.springframework.util.ClassUtils;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-22 11:46
 **/
public class TargetSource {

    private final Object targe;

    public TargetSource(Object targe) {
        this.targe = targe;
    }

    /**
     * 获得所有实现的方法
     * @return
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.targe.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz)? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    public Object getTarget() {
        return this.targe;
    }
}
