package com.becomejavasenior.service.general;

import com.becomejavasenior.dao.GenericDao;
import com.becomejavasenior.model.Entity;

import java.util.List;

/**
 * Created by Shevchenko on 17.05.2016.
 */
public class GeneralServiceImpl implements GeneralService {

    GenericDao genericDao;

    public GenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    @Override
    public Entity insertEntity(Entity entity) {
        return (Entity)genericDao.insert(entity);
    }

    @Override
    public void deleteEntity(Entity entity) {
        genericDao.delete(entity);
    }

    @Override
    public void updateEntity(Entity entity) {
        genericDao.update(entity);
    }

    @Override
    public List<Entity> getEntity1000() {
        return genericDao.select();
    }

    @Override
    public void operationGroup(Entity entity) {
        insertEntity(entity);
        updateEntity(entity);
        deleteEntity(entity);
        getEntity1000();
    }
}
