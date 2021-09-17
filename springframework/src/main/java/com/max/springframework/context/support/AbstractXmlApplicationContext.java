package com.max.springframework.context.support;

import com.max.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.max.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @program: mySpring
 * @description: XML应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:38
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
