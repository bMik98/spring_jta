package com.becomejavasenior.controller;

import com.becomejavasenior.dao.OrderDao;
import com.becomejavasenior.dao.ProductDao;
import com.becomejavasenior.dao.UserDao;
import com.becomejavasenior.model.DataCreator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppGenerator {
    public static final int PACKAGE_SIZE = 500;
    ApplicationContext context;
    DataCreator dataCreator;

    public AppGenerator(String xmlConfigFileName) {
        context = new ClassPathXmlApplicationContext(xmlConfigFileName);
        dataCreator = (DataCreator) context.getBean("dataCreator");
    }

    public static void main(String[] args) {
        new AppGenerator("beans.xml").fillTables();
    }

    public void fillTables() {
        fillUserTable(20);
        fillProductTable(20);
        fillOrderTable(20);
    }

    public void fillUserTable(final int packageNumber) {
        UserDao userDao = (UserDao) context.getBean("userDao");
        System.out.println("Filling in user table...");
        for (int i = 0; i < packageNumber; i++) {
            userDao.insertBatch(dataCreator.generateUserList(PACKAGE_SIZE));
            System.out.printf("%d user records...%n", (i + 1) * PACKAGE_SIZE);
        }
        System.out.println("user table is done.");
    }

    public void fillProductTable(final int packageNumber) {
        ProductDao productDao = (ProductDao) context.getBean("productDao");
        System.out.println("Filling in product table...");
        for (int i = 0; i < packageNumber; i++) {
            productDao.insertBatch(dataCreator.generateProductList(PACKAGE_SIZE));
            System.out.printf("%d product records...%n", (i + 1) * PACKAGE_SIZE);
        }
        System.out.println("product table is done.");
    }

    public void fillOrderTable(final int packageNumber) {
        OrderDao orderDao = (OrderDao) context.getBean("orderDao");
        System.out.println("Filling in order table...");
        for (int i = 0; i < packageNumber; i++) {
            orderDao.insertBatch(dataCreator.generateOrderList(PACKAGE_SIZE));
            System.out.printf("%d order records...%n", (i + 1) * PACKAGE_SIZE);
        }
        System.out.println("order table is done.");
    }
}
