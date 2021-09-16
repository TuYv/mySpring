package com.max.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.PropertyValue;
import com.max.springframework.beans.PropertyValues;
import com.max.springframework.beans.factory.InitializingBean;
import com.max.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.max.springframework.beans.factory.config.BeanDefinition;
import com.max.springframework.beans.factory.config.BeanPostProcessor;
import com.max.springframework.beans.factory.config.BeanReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/**
 * 实现自动装载bean
 * @author max
 * @date 2021/9/14 18:09
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName,bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructor = null;
        Class< ? > beanClass = beanDefinition.getBeanClass();
        //获取类的所有构造函数
        Constructor< ? >[] declaredConstructors = beanClass.getDeclaredConstructors();
        //循环构造函数，找到和参数一致的构造函数
        //todo 这里还应该对比参数和构造函数的类型
        for(Constructor ctor : declaredConstructors) {
            if(args != null && ctor.getParameterTypes().length == args.length) {
                constructor = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor, args);
    }

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        Object value;
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue pv : propertyValues.getPropertyValues()) {
                if ((value = pv.getValue()) instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                //属性填充
                BeanUtil.setFieldValue(bean, pv.getName(), value);
            }
        }catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(
        InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        //2. 执行bean的初始化方法
        try{
            invokeInitMethods(beanName,wrappedBean,beanDefinition);
        }catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }
        //3. 执行 beanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private Object applyBeanPostProcessorsAfterInitialization(Object wrappedBean, String beanName) {
        Object result = wrappedBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    //todo  为什么init是写成一个方法  而 destroy写了一个适配器
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
    // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2. 注解配置 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    private Object applyBeanPostProcessorsBeforeInitialization(Object bean, String beanName) {
        Object result = bean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

}
