package com.max.springframework.context.event;

/**
 * @program: small-spring-step-17
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-19 21:32
 **/
public class ContextClosedEvent extends ApplicationContextEvent{

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
