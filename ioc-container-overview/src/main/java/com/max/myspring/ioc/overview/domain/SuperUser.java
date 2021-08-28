package com.max.myspring.ioc.overview.domain;

import com.max.myspring.ioc.overview.annotation.Super;
import lombok.Data;

/**
 * @program: mySpring
 * @description: 超级用户
 * @author: Max.Tu
 * @create: 2021-08-27 00:27
 **/
@Data
@Super
public class SuperUser extends User{

    private String address;

}
