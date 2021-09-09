package com.max.myspring.bean.definition;

import com.max.myspring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: mySpring
 * @description: Bean 别名实例
 * @author: Max.Tu
 * @create: 2021-09-08 23:17
 **/
public class BeanAliasDemo {

    public static void main(String[] args) {
        //配置 xml 配置文件
        //启动 Spring 应用上下文
        BeanFactory beanFactory =  new ClassPathXmlApplicationContext(
            "META-INF/bean-definitions-context.xml");
        //通过别名获取bean
        User user = beanFactory.getBean("user",User.class);
        User maxUser = beanFactory.getBean("max-user",User.class);
        System.out.println("两个bean是否相同： " + (user == maxUser));
    }

}
