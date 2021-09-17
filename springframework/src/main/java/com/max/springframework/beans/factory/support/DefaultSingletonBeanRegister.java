package com.max.springframework.beans.factory.support;

import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.DisposableBean;
import com.max.springframework.beans.factory.config.SingletonBeanRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 单例注册表的默认实现
 * @author max
 * @date 2021/9/14 18:00
 */
public class DefaultSingletonBeanRegister implements SingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
