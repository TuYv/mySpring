package com.max.myspring.bean.factory;

import com.max.myspring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @program: mySpring
 * @description: {@link User} Bean 的 {@link org.springframework.beans.factory.FactoryBean} 实现
 * @author: Max.Tu
 * @create: 2021-09-09 21:34
 **/
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
