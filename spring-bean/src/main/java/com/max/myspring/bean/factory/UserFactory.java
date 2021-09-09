package com.max.myspring.bean.factory;

import com.max.myspring.ioc.overview.domain.User;

/**
 * @program: mySpring
 * @description: {@link User} 工厂类
 * @author: Max.Tu
 * @create: 2021-09-09 21:21
 **/
public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
