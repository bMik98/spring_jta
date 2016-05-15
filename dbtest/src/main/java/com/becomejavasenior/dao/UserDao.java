package com.becomejavasenior.dao;

import com.becomejavasenior.model.User;
import java.util.List;

public interface UserDao {
    User insert(User user);
    User insertBatch(List<User> users);
    void update(User user);
    void delete((User user);
}
