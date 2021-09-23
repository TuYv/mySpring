package com.max.springframework.aop.framework;

import com.max.springframework.aop.AdvisedSupport;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-23 10:45
 **/
public class Cglib2AopProxy implements AopProxy{

    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        private DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)
            throws Throwable {
            CglibMethodInvocation cglibMethodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(),method,objects, methodProxy);
            if (advised.getMethodMatcher().matches(method,advised.getTargetSource().getTarget().getClass())) {
                return advised.getMethodInterceptor().invoke(cglibMethodInvocation);
            }
            return cglibMethodInvocation.proceed();
        }

        private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

            private final MethodProxy methodProxy;

            public CglibMethodInvocation(Object target, Method method, Object[] arguments,
                MethodProxy methodProxy) {
                super(target, method, arguments);
                this.methodProxy = methodProxy;
            }

            @Override
            public Object proceed() throws Throwable {
                return this.methodProxy.invoke(this.target, this.arguments);
            }
        }
    }
}
