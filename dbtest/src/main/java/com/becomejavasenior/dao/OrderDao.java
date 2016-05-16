package com.becomejavasenior.dao;

import com.becomejavasenior.model.Order;

import java.util.List;

public interface OrderDao {
    Order insert(final Order order);

    void insertBatch(final List<Order> orders);

    void update(final Order order);

    void delete(final Order order);

    Order getById(Integer id);

    List<Order> select();
}
