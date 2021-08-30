package com.max.myspring.ioc.overview.dependency.repository;

import com.max.myspring.ioc.overview.domain.User;
import java.util.Collection;
import lombok.Data;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

/**
 * @program: mySpring
 * @description: 用户信息仓库
 * @author: Max.Tu
 * @create: 2021-08-28 21:09
 **/
@Data
public class UserRepository {
    private Collection<User> users;

    private BeanFactory beanFactory;

    private ObjectFactory<ApplicationContext> objectFactory;

}
