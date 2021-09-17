package com.max.springframework.beans;

import com.max.springframework.beans.factory.BeanClassLoaderAware;
import com.max.springframework.beans.factory.BeanFactory;
import com.max.springframework.beans.factory.BeanFactoryAware;
import com.max.springframework.beans.factory.BeanNameAware;
import com.max.springframework.context.ApplicationContext;
import com.max.springframework.context.ApplicationContextAware;
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
public class UserService implements BeanNameAware, BeanClassLoaderAware, ApplicationContextAware,
    BeanFactoryAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is：" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
