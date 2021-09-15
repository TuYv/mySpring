package com.max.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: mySpring
 * @description: bean 属性信息
 * @author: Max.Tu
 * @create: 2021-09-15 00:40
 **/
@AllArgsConstructor
public class PropertyValue {
    private final String name;
    private final Object value;

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
