package com.max.springframework.event;

import com.max.springframework.context.ApplicationListener;
import com.max.springframework.context.event.ContextClosedEvent;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-20 11:50
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件:" + this.getClass().getName());
    }
}
