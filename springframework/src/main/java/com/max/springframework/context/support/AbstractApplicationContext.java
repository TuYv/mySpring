package com.max.springframework.context.support;

import com.max.springframework.context.ConfigurableApplicationContext;
import com.max.springframework.core.io.DefaultResourceLoader;

/**
 * @program: mySpring
 * @description: 抽象应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:27
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements
    ConfigurableApplicationContext {

}
