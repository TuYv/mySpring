package com.max.springframework.event;

import com.max.springframework.context.ApplicationListener;
import com.max.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-20 11:49
 **/
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
