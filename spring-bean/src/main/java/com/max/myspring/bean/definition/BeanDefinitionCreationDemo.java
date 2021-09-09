package com.max.myspring.bean.definition;

import com.max.myspring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @program: mySpring
 * @description: {@link BeanDefinition} 构建实例
 * @author: Max.Tu
 * @create: 2021-09-04 16:37
 **/
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {
       //1.通过 BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id","1");
        beanDefinitionBuilder.addPropertyValue("name","张三");
        //获取beanDefinition实例
        BeanDefinition bd = beanDefinitionBuilder.getBeanDefinition();

        //2.通过AbstractBeanDefinition 及其派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置Bean类型
        genericBeanDefinition.setBeanClass(User.class);
        //通过 MutablePropertyValues 批量设置属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue("id","1");
        mutablePropertyValues.addPropertyValue("name","张三");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }

}
