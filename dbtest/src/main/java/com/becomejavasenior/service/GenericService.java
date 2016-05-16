package com.becomejavasenior.service;



import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shevchenko on 16.05.2016.
 */
public interface GenericService<T> {
    T insertObj(T t);
    void deleteObj(T t);
    void updateObj(T t);
    List<T> getObj1000();

    void insertObjReqNew(T t);
    void deleteObjReqNew(T t);
    void updateObjReqNew(T t);
    List<T> getObj1000ReqNew();

    void insertObjNotSup(T t);
    void deleteObjNotSup(T t);
    void updateObjNotSup(T t);
    List<T> getObj1000NotSup();

    void operationGroup(T t);
    void operationGroupReqNew(T t);
    void operationGroupNotSup(T t);

    void operationGroupData(T t);

}
