package com.max.myspring.bean.definition;

import com.max.myspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: mySpring
 * @description: Bean 示例化示例
 * @author: Max.Tu
 * @create: 2021-09-09 21:13
 **/
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 xml 配置文件
        //启动 Spring 应用上下文
        BeanFactory beanFactory =  new ClassPathXmlApplicationContext(
            "META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-by-static-method", User.class);
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method",User.class);
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean",User.class);
        System.out.println(user);
        System.out.println(userByInstanceMethod);
        System.out.println(userByFactoryBean);
        System.out.println(user == userByInstanceMethod);
    }
}
