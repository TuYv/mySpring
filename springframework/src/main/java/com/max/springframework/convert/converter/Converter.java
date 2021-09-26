package com.max.springframework.convert.converter;

public interface Converter<S, T> {

    T convert(S source);
}
