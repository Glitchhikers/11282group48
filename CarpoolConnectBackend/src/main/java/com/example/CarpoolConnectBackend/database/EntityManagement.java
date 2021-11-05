package com.example.CarpoolConnectBackend.database;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


public class EntityManagement<T> {

    @PersistenceContext
    EntityManager manager;

    public T obj;

    public EntityManagement(T obj) {
        this.obj = obj;
    }

    @Transactional
    public void sendToDatabase() {
        manager.persist(obj);
    }

}
