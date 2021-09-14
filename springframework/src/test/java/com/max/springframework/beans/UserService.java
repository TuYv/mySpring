package com.max.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author max
 * @date 2021/9/14 18:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    private String uId;

    private UserDao userDao;

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(uId));
    }

}
