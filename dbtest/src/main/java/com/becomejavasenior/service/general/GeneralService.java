package com.becomejavasenior.service.general;

import com.becomejavasenior.model.Entity;
import com.becomejavasenior.model.User;

import java.util.List;

/**
 * Created by Shevchenko on 17.05.2016.
 */
public interface GeneralService {
    Entity insertEntity(Entity entity);
    void deleteEntity(Entity entity);
    void updateEntity(Entity entity);
    List<Entity> getEntity1000();

    void operationGroup(Entity entity);

}
