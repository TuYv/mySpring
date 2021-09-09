package com.max.myspring.bean.definition;

import com.max.myspring.ioc.overview.domain.User;
import java.util.Map;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @program: mySpring
 * @description: 注解 BeanDefinition 示例
 * @author: Max.Tu
 * @create: 2021-09-08 23:23
 **/
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        //创建beanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        //通过BeanDefinition注册API实现
        registerUserBeanDefinition(applicationContext, "a-user");
        registerUserBeanDefinition(applicationContext);
        applicationContext.refresh();
        //1.通过Bean方式定义
        //2.通过@Component方式
        //3.Import导入

        Map<String,Config> configBeans = applicationContext.getBeansOfType(Config.class);
        System.out.println("Config 类型中所有的Beans "+ configBeans);
        System.out.println("User 类型中所有的Beans "+ applicationContext.getBeansOfType(User.class));
        //显示的关闭sprin上下文
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1).addPropertyValue("name","zhangsan");
        //判断 beanName参数是否存在
        if(StringUtils.hasText(beanName)) {
            //注册BeanDefinition
            registry.registerBeanDefinition(beanName,beanDefinitionBuilder.getBeanDefinition());
        }else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(),registry);
        }
    }

    /**
     * 非命名Bean注册方式
     * @param registry
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry,null);
    }
    @Component //定义当前类作为 Spring Bean (组件)
    public static class Config {

        /**
         * 通过注解定义一个Bean
         * @return
         */
        @Bean(name = {"user","max-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("张三");
            return user;
        }
    }
}
