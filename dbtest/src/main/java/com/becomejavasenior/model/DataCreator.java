package com.becomejavasenior.model;

import org.fluttercode.datafactory.impl.DataFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataCreator {
    private DataFactory dataFactory;

    public DataCreator(DataFactory dataFactory) {
        this.dataFactory = dataFactory;
    }

    public List<User> generateUserList(final int number) {
        List<User> users = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            users.add(generateUser());
        }
        return users;
    }

    public List<Product> generateProductList(final int number) {
        List<Product> products = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            products.add(generateProduct());
        }
        return products;
    }

    public List<Order> generateOrderList(final int number) {
        List<Order> orders = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            orders.add(generateOrder());
        }
        return orders;
    }

    public User generateUser() {
        User user = new User();
        user.setFtiny((byte) dataFactory.getNumberBetween(-128, 127));
        user.setFsmall((short) dataFactory.getNumberBetween(-32768, 32767));
        user.setFbig((long) dataFactory.getNumberBetween(0, 100000000));
        user.setFdouble((double) dataFactory.getNumberBetween(0, 10000000) / 3.1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            user.setFdate(new Timestamp(dataFactory
                    .getDateBetween(sdf.parse("01-01-1976 00:00:00"), sdf.parse("15-05-2016 23:59:59"))
                    .getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setFyear(dataFactory.getNumberBetween(1901, 2155));
        user.setFchar(dataFactory.getRandomText(2));
        user.setFvchar(dataFactory.getRandomText(1, 250));
        user.setFdec(new BigDecimal(dataFactory.getNumberBetween(0, 32767)));
        return user;
    }

    public Product generateProduct() {
        Product product = new Product();
        product.setFtiny((byte) dataFactory.getNumberBetween(-128, 127));
        product.setFsmall((short) dataFactory.getNumberBetween(-32768, 32767));
        product.setFbig((long) dataFactory.getNumberBetween(0, 100000000));
        product.setFdouble((double) dataFactory.getNumberBetween(0, 10000000) / 3.1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            product.setFdate(new Timestamp(dataFactory
                    .getDateBetween(sdf.parse("01-01-1976 00:00:00"), sdf.parse("15-05-2016 23:59:59"))
                    .getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        product.setFyear(dataFactory.getNumberBetween(1901, 2155));
        product.setFchar(dataFactory.getRandomText(2));
        product.setFvchar(dataFactory.getRandomText(1, 250));
        product.setFdec(new BigDecimal(dataFactory.getNumberBetween(0, 32767)));
        return product;
    }

    public Order generateOrder() {
        Order order = new Order();
        order.setFtiny((byte) dataFactory.getNumberBetween(-128, 127));
        order.setFsmall((short) dataFactory.getNumberBetween(-32768, 32767));
        order.setFbig((long) dataFactory.getNumberBetween(0, 100000000));
        order.setFdouble((double) dataFactory.getNumberBetween(0, 10000000) / 3.1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        try {
            order.setFdate(new Timestamp(dataFactory
                    .getDateBetween(sdf.parse("01-01-1976 00:00:00"), sdf.parse("15-05-2016 23:59:59"))
                    .getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        order.setFyear(dataFactory.getNumberBetween(1901, 2155));
        order.setFchar(dataFactory.getRandomText(2));
        order.setFvchar(dataFactory.getRandomText(1, 250));
        order.setFdec(new BigDecimal(dataFactory.getNumberBetween(0, 32767)));
        return order;
    }
}
