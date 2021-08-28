package com.max.myspring.ioc.overview.dependency.lookup;

import com.max.myspring.ioc.overview.annotation.Super;
import com.max.myspring.ioc.overview.domain.User;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过名称的方式依赖查找
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-08-26 23:33
 **/
public class DependencyLookupDemo {

    public static void main(String[] args) {
        //配置 xml 配置文件
        //启动 Spring 应用上下文
        BeanFactory beanFactory =  new ClassPathXmlApplicationContext("META_INF/dependency-lookup-context.xml");
        //类型查找
        lookupByType(beanFactory);
        //类型查找集合
        lookupCollectionByType(beanFactory);
        //通过注解查找
        lookUpByAnnotation(beanFactory);

        //实时查找
        //lookupInRealTime(beanFactory);
        //延时查找
        //lookupInLazyTime(beanFactory);
    }

    private static void lookUpByAnnotation(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("所有注解@Super的类型查找集合：" + users);
        }
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String,User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("类型查找集合：" + users);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("类型查找："+ user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找：" + user);

    }
    private static void lookupInLazyTime(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        System.out.println("延迟查找：" + user);
    }
}
