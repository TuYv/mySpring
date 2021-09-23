package com.max.springframework.aop;

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
        return this.targe.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.targe;
    }
}
