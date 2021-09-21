package com.max.springframework.context.event;

import com.max.springframework.beans.factory.BeanFactory;
import com.max.springframework.context.ApplicationEvent;
import com.max.springframework.context.ApplicationListener;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-20 11:06
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
