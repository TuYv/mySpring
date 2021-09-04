package com.max.myspring.ioc.overview.dependency.injection;

import com.max.myspring.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 * 通过名称的方式依赖查找
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-08-26 23:33
 **/
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        //配置 xml 配置文件
        //启动 Spring 应用上下文
        //BeanFactory beanFactory =  new ClassPathXmlApplicationContext(
        ApplicationContext beanFactory =  new ClassPathXmlApplicationContext(
            "META-INF/dependency-injection-context.xml");
        //自定义 Bean
        UserRepository userRepository = beanFactory.getBean("userRepository",UserRepository.class);

        System.out.println(userRepository.getBeanFactory() == beanFactory);

        //依赖注入(内建依赖)
        ObjectFactory objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        //System.out.println(userRepository.getUsers());
        //依赖查找
        //System.out.println(beanFactory.getBean(BeanFactory.class));

        //容器内建
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean:" + environment);

    }

    private static void whoIsIocContainer(UserRepository userRepository,BeanFactory beanFactory) {
        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }

}
