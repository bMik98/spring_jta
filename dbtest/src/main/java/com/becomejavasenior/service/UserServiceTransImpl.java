package com.becomejavasenior.service;

import com.becomejavasenior.dao.UserDaoJdbc;
import com.becomejavasenior.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shevchenko on 16.05.2016.
 */
public class UserServiceTransImpl implements UserService {
    @Autowired
    UserDaoJdbc userDaoJdbc;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User insertObj(User user) {
        return userDaoJdbc.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteObj(User user) {
        userDaoJdbc.delete(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateObj(User user) {
        userDaoJdbc.update(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<User> getObj1000() {
        return userDaoJdbc.select();
    }

    //Transaction with propagation.REQUIRES_NEW
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertObjReqNew(User user) {
        userDaoJdbc.insert(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteObjReqNew(User user) {
        userDaoJdbc.delete(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateObjReqNew(User user) {
        userDaoJdbc.update(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<User> getObj1000ReqNew() {
        return userDaoJdbc.select();
    }

    //Transaction with propagation.NOT_SUPPORT
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void insertObjNotSup(User user) {
        userDaoJdbc.insert(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void deleteObjNotSup(User user) {
        userDaoJdbc.delete(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void updateObjNotSup(User user) {
        userDaoJdbc.update(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<User> getObj1000NotSup() {
        return userDaoJdbc.select();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void operationGroup(User user) {
        operationGroupData(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void operationGroupReqNew(User user) {
        operationGroupData(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void operationGroupNotSup(User user) {
        operationGroupData(user);
    }

    @Override
    public void operationGroupData(User user) {
        userDaoJdbc.insert(user);
        userDaoJdbc.delete(user);
        userDaoJdbc.update(user);
        userDaoJdbc.select();
    }
}
