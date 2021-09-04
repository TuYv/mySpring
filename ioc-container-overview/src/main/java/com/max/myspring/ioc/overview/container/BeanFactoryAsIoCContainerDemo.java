package com.max.myspring.ioc.overview.container;

import com.max.myspring.ioc.overview.domain.User;
import java.util.Map;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @program: mySpring
 * @description: {@link BeanFactory} Ioc 容器示例
 * @author: Max.Tu
 * @create: 2021-09-03 00:11
 **/
public class BeanFactoryAsIoCContainerDemo {

    public static void main(String[] args) {
        //创建beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        int beanCount = reader.loadBeanDefinitions(location);
        System.out.println("加载的bean数量:" + beanCount);
        //依赖查找输出对象
        lookupCollectionByType(beanFactory);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("类型查找集合：" + users);
        }
    }
}
