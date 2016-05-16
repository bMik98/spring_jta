package com.becomejavasenior.dao;

import com.becomejavasenior.model.Product;
import java.util.List;

public interface ProductDao {
    Product insert(final Product product);

    void insertBatch(final List<Product> products);

    void update(final Product product);

    void delete(final Product product);

    Product getById(Integer id);

    List<Product> select();
}
