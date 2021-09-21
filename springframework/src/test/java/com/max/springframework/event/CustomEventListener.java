package com.max.springframework.event;

import com.max.springframework.context.ApplicationListener;
import java.util.Date;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-20 11:45
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到" + event.getSource() + "消息，时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
