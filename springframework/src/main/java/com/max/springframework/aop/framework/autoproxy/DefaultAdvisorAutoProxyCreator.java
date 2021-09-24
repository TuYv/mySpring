package com.max.springframework.aop.framework.autoproxy;

import com.max.springframework.aop.AdvisedSupport;
import com.max.springframework.aop.Advisor;
import com.max.springframework.aop.ClassFilter;
import com.max.springframework.aop.Pointcut;
import com.max.springframework.aop.TargetSource;
import com.max.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.max.springframework.aop.framework.ProxyFactory;
import com.max.springframework.beans.BeansException;
import com.max.springframework.beans.factory.BeanFactory;
import com.max.springframework.beans.factory.BeanFactoryAware;
import com.max.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.max.springframework.beans.factory.support.DefaultListableBeanFactory;
import java.util.Collection;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-23 21:53
 **/
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor,
    BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (isInfrastructureClass(beanClass)) return null;
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for(AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if(!classFilter.matches(beanClass)) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            return new ProxyFactory(advisedSupport).getProxy();
        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }
}