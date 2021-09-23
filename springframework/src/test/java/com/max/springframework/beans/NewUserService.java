package com.max.springframework.beans;

import java.util.Random;

/**
 * @program: mySpring
 * @description:
 * @author: Max.Tu
 * @create: 2021-09-23 11:19
 **/
public class NewUserService implements IUserService{

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
