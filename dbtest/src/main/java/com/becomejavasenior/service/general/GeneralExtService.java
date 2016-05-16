package com.becomejavasenior.service.general;

import com.becomejavasenior.model.Entity;

import java.util.List;

/**
 * Created by Shevchenko on 17.05.2016.
 */
public interface GeneralExtService {
    Entity insertEntityReqNew(Entity entity);
    void deleteEntityReqNew(Entity entity);
    void updateEntityReqNew(Entity entity);
    List<Entity> getEntity1000ReqNew();

    Entity insertEntityNotSup(Entity entity);
    void deleteEntityNotSup(Entity entity);
    void updateEntityNotSup(Entity entity);
    List<Entity> getEntity1000NotSup();

    void operationGroupReqNew(Entity entity);
    void operationGroupNotSup(Entity entity);
}
