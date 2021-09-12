package com.max.myspring.bean.definition;

import com.max.myspring.bean.factory.DefaultUserFactory;
import com.max.myspring.bean.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: mySpring
 * @description: Bean 初始化 Demo
 * @author: Max.Tu
 * @create: 2021-09-12 14:14
 **/
public class BeanInitializationDemo {

    public static void main(String[] args) {
        //创建beanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        applicationContext.register(BeanInitializationDemo.class);
        //启动 Spring上下文
        applicationContext.refresh();
        // 非延迟初始化在 spring 应用上下文启动完成后 被初始化
        System.out.println("spring 应用上下文已启动...");
        //依赖查找 UserFactory
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        //关闭 Spring上下文
        applicationContext.close();
    }

    @Bean(initMethod =  "initUserFactory")
    @Lazy
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
