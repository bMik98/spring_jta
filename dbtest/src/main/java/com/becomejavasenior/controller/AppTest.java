package com.becomejavasenior.controller;

import com.becomejavasenior.dao.OrderDao;
import com.becomejavasenior.dao.ProductDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppTest {
    public static void main(String[] args) {
        new AppTest().run();
    }

    public void run() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        ProductDao productDao = (ProductDao) context.getBean("productDao");
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");
        System.out.println(userDao.select().size());
        System.out.println(productDao.select().size());
        System.out.println(orderDao.select().size());
        User user = new User();
        user.setFtiny((byte) 100);
        user = userDao.insert(user);
        System.out.println(user.getId());
        System.out.println(user.getFtiny());
        user.setFtiny((byte) 101);
        userDao.update(user);
        user = userDao.getById(user);
        System.out.println(user.getFtiny());
        userDao.delete(user);
        if(userDao.getById(user) == null) {
            System.out.println("delete success");
        } else {
            System.out.println("did not delete!");
        }
    }
}
