package com.max.springframework.context.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.max.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.max.springframework.beans.factory.config.BeanPostProcessor;
import com.max.springframework.context.ApplicationEvent;
import com.max.springframework.context.ApplicationListener;
import com.max.springframework.context.ConfigurableApplicationContext;
import com.max.springframework.context.event.ApplicationEventMulticaster;
import com.max.springframework.context.event.ContextClosedEvent;
import com.max.springframework.context.event.ContextRefreshedEvent;
import com.max.springframework.context.event.SimpleApplicationEventMulticaster;
import com.max.springframework.core.io.DefaultResourceLoader;
import java.util.Collection;
import java.util.Map;

/**
 * 定义了applicationContext的主要逻辑，并实现了部分功能
 *
 * @program: mySpring
 * @description: 抽象应用上下文
 * @author: Max.Tu
 * @create: 2021-09-17 01:27
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements
    ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 1. 创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();

        // 2. 获取 BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3. 添加 ApplicationContextAwareProcessor，让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4. 在 Bean 实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5. BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // 6. 初始化事件发布者
        initApplicationEventMulticaster();

        // 7. 注册事件监听器
        registerListeners();

        // 8. 提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9. 发布容器刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 为实现了BeanFactoryProcessor的beanFactory执行postProcess方法
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for(BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 将该上下文管理的beanFactory放入事件播放者中，并为beanFactory添加这个事件播放者的bean
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 将事件监听者放入广播中
     */
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        applicationListeners.forEach(x -> applicationEventMulticaster.addApplicationListener(x));
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 将实现了BeanPostProcessor的bean放入beanFactory中以供后续使用
     * @param beanFactory
     */
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
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        //执行销毁单例 bean 的销毁方法
        getBeanFactory().destroySingletons();
    }
}
