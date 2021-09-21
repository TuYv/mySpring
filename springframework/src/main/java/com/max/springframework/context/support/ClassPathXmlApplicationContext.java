package com.max.springframework.context.support;

import com.max.springframework.beans.BeansException;

/**
 * 通过配置一个/多个路径可以从xml中获取bean信息并调用了{@link AbstractApplicationContext} 中的refresh()方法 完成了BeanFactory的自动配置
 * @program: mySpring
 * @description: 文件应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:38
 **/
public class ClassPathXmlApplicationContext extends  AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }
    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
