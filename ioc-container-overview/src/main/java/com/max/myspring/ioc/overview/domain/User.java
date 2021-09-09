package com.max.myspring.ioc.overview.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户类
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-08-26 23:42
 **/
@Data
public class User {
    private Long id;

    private String name;

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("lisi");
        return user;
    }
}
