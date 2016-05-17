package com.becomejavasenior.service.general;

import com.becomejavasenior.dao.GenericDao;
import com.becomejavasenior.model.Entity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Shevchenko on 17.05.2016.
 */
public class GeneralServiceTransImpl implements GeneralTransService {
    GenericDao genericDao;

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Entity insertEntity(Entity entity) {
        return (Entity)genericDao.insert(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteEntity(Entity entity) {
        genericDao.delete(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateEntity(Entity entity) {
        genericDao.update(entity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Entity> getEntity1000() {
        return genericDao.select();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void operationGroup(Entity entity) {
        insertEntity(entity);
        updateEntity(entity);
        deleteEntity(entity);
        getEntity1000();
    }


    //Transaction with propagation.REQUIRES_NEW
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Entity insertEntityReqNew(Entity entity) {
        return (Entity)genericDao.insert(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteEntityReqNew(Entity entity) {
        genericDao.delete(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateEntityReqNew(Entity entity) {
        genericDao.update(entity);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public List<Entity> getEntity1000ReqNew() {
        return genericDao.select();
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void operationGroupReqNew(Entity entity) {
        insertEntityReqNew(entity);
        updateEntityReqNew(entity);
        deleteEntityReqNew(entity);
        getEntity1000ReqNew();
    }

    //Transaction with propagation.NOT_SUPPORTED
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public Entity insertEntityNotSup(Entity entity) {
        return (Entity)genericDao.insert(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void deleteEntityNotSup(Entity entity) {
        genericDao.delete(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void updateEntityNotSup(Entity entity) {
        genericDao.update(entity);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public List<Entity> getEntity1000NotSup() {
        return genericDao.select();
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public void operationGroupNotSup(Entity entity) {
        insertEntityNotSup(entity);
        updateEntityNotSup(entity);
        deleteEntityNotSup(entity);
        getEntity1000NotSup();
    }
}
