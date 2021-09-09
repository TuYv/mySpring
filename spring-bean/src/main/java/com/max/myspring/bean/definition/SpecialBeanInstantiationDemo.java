package com.max.myspring.bean.definition;

import static java.util.ServiceLoader.load;

import com.max.myspring.bean.factory.DefaultUserFactory;
import com.max.myspring.bean.factory.UserFactory;
import com.max.myspring.ioc.overview.domain.User;
import java.util.Iterator;
import java.util.ServiceLoader;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: mySpring
 * @description: 特殊的 Bean 示例化示例
 * @author: Max.Tu
 * @create: 2021-09-09 21:13
 **/
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 xml 配置文件
        //启动 Spring 应用上下文
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext(
            "META-INF/special-bean-instantiation-context.xml");
        //通过AP获取AutowireCapableBeanFactory的实现
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();

        ServiceLoader<UserFactory> serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoader(serviceLoader);
        // demoServiceLoader();

        //通过AutowireCapableBeanFactory实现创建userFactory对象
        UserFactory userFactory = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(userFactory.createUser());
    }

    public static void demoServiceLoader() {
        ServiceLoader<UserFactory> serviceLoader = load(UserFactory.class,Thread.currentThread().getContextClassLoader());
        displayServiceLoader(serviceLoader);
    }

    private static void displayServiceLoader(ServiceLoader<UserFactory> serviceLoader) {
        Iterator<UserFactory> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            UserFactory userFactory = iterator.next();
            System.out.println(userFactory.createUser());
        }
    }
}
