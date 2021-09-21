package com.max.springframework.context.event;


import com.max.springframework.context.ApplicationContext;
import com.max.springframework.context.ApplicationEvent;

/**
 * @program: small-spring-step-17
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-19 21:20
 **/
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
