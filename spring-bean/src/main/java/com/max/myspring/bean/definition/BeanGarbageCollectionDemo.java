package com.max.myspring.bean.definition;

import com.max.myspring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: mySpring
 * @description: Bean 垃圾回收(GC)示例
 * @author: Max.Tu
 * @create: 2021-09-12 16:21
 **/
public class BeanGarbageCollectionDemo {
    public static void main(String[] args) {
        //创建beanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        applicationContext.register(BeanInitializationDemo.class);
        //启动 Spring上下文
        applicationContext.refresh();

        //关闭 Spring上下文
        applicationContext.close();
        //强制触发 GC
        System.gc();
    }
}
