package com.max.springframework.context;

import java.util.EventObject;

/**
 * @program: small-spring-step-17
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-19 21:18
 **/
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
