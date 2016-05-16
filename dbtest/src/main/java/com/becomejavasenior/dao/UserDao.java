package com.becomejavasenior.dao;

import com.becomejavasenior.model.User;
import java.util.List;

public interface UserDao {
    User insert(final User user);

    void insertBatch(final List<User> users);

    void update(final User user);

    void delete(final User user);

    User getById(final User user);

    List<User> select();
}
