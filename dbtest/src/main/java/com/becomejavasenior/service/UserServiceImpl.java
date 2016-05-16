package com.becomejavasenior.service;

import com.becomejavasenior.dao.UserDaoJdbc;
import com.becomejavasenior.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by shevchenko on 16.05.2016.
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserDaoJdbc userDaoJdbc;


    @Override
    public User insertObj(User user) {
        return userDaoJdbc.insert(user);
    }


    @Override
    public void deleteObj(User user) {
        userDaoJdbc.delete(user);
    }


    @Override
    public void updateObj(User user) {
        userDaoJdbc.update(user);
    }


    @Override
    public List<User> getObj1000() {
        return userDaoJdbc.select();
    }


    @Override
    public void insertObjReqNew(User user) {
        userDaoJdbc.insert(user);
    }


    @Override
    public void deleteObjReqNew(User user) {
        userDaoJdbc.delete(user);
    }


    public void updateObjReqNew(User user) {
        userDaoJdbc.update(user);
    }


    public List<User> getObj1000ReqNew() {
        return userDaoJdbc.select();
    }


    @Override
    public void insertObjNotSup(User user) {
        userDaoJdbc.insert(user);
    }


    @Override
    public void deleteObjNotSup(User user) {
        userDaoJdbc.delete(user);
    }

    @Override
    public void updateObjNotSup(User user) {
        userDaoJdbc.update(user);
    }

    @Override
    public List<User> getObj1000NotSup() {
        return userDaoJdbc.select();
    }

    @Override
    public void operationGroup(User user) {
        operationGroupData(user);
    }

    @Override
    public void operationGroupReqNew(User user) {
        operationGroupData(user);
    }

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
