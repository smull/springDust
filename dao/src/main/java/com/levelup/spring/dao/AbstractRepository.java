package com.levelup.spring.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Transactional
public abstract class AbstractRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public T getById(Long id, Class entityClass){
        T object = (T) entityManager.find(entityClass, id);
        return object;
    }

    public T create(T object){
        entityManager.persist(object);
        return object;
    }
    public T update(T object){
        entityManager.merge(object);
        return object;
    }
    public Boolean delete(Long id, Class entityClass){
        T objectForRemove = (T) entityManager.find(entityClass, id);
        entityManager.remove(objectForRemove);
        return true;
    }

}
