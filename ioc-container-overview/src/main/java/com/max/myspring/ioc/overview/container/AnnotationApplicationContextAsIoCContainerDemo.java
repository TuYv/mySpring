package com.max.myspring.ioc.overview.container;

import com.max.myspring.ioc.overview.domain.User;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: mySpring
 * @description: 注解能力 Ioc 容器示例
 * @author: Max.Tu
 * @create: 2021-09-03 00:11
 **/
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        //创建beanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //把当前类作为配置类 configration class
        applicationContext.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        //启动应用上下文
        applicationContext.refresh();
        //依赖查找输出对象
        lookupCollectionByType(applicationContext);

    }
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("张三");
        return user;
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("类型查找集合：" + users);
        }
    }
}
