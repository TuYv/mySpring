package com.max.springframework.convert.support;

import com.max.springframework.convert.converter.Converter;
import com.max.springframework.convert.converter.ConverterFactory;
import com.sun.istack.internal.Nullable;
import org.springframework.util.NumberUtils;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-25 23:43
 **/
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {


    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }

}
