package com.max.springframework.convert.support;

import com.max.springframework.convert.converter.ConverterRegistry;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-25 23:27
 **/
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    private static void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
