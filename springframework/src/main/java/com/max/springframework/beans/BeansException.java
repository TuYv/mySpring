package com.max.springframework.beans;

/**
 * @author max
 * @date 2021/9/14 17:56
 */
public class BeansException extends RuntimeException{
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
