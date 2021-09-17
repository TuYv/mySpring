package com.max.springframework.context.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.max.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.max.springframework.beans.factory.config.BeanPostProcessor;
import com.max.springframework.context.ConfigurableApplicationContext;
import com.max.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @program: mySpring
 * @description: 抽象应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:27
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements
    ConfigurableApplicationContext {

    @Override
    public void refresh() throws BeansException {
        //1.创建BeanFactory 并加载BeanDefinition
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在 Bean 实例化之前执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //5.于其他Bean实例化之前BeanFactoryPostProcessor执行注册
        registerBeanPostProcessors(beanFactory);

        //6. 提前实例化单例 Bean 对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
