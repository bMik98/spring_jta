package com.becomejavasenior.dao;

import com.becomejavasenior.model.Order;

import java.util.List;

/**
 * Created by Shevchenko on 17.05.2016.
 */
public interface GenericDao<T>  {
    T insert(final T t);

    void insertBatch(final List<T> tList);

    void update(final T t);

    void delete(final T t);

    T getById(Integer id);

    List<T> select();
}
