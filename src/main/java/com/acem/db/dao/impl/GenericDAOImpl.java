package com.acem.db.dao.impl;

import com.acem.db.dao.GenericDAO;
import com.acem.db.exception.ExceptionHandler;
import com.acem.db.model.Student;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;


//Generic Hibernate implementation
public class GenericDAOImpl<T,ID> implements GenericDAO<T,ID> {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("collegePersistenceUnit");

    public Class<T> persistenceClass(){
        final ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    @Override
    public Boolean save(T object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        return ExceptionHandler.handleWithFallBack(() -> {
            entityTransaction.begin();
            entityManager.persist(object);
            entityTransaction.commit();
            return true;
        }, () -> {
            entityTransaction.rollback();
            return false;
        });
    }

    @Override
    public Boolean update(T object) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        return ExceptionHandler.handleWithFallBack(() -> {
            entityTransaction.begin();
            entityManager.merge(object);
            entityTransaction.commit();
            return true;
        }, () -> {
            entityTransaction.rollback();
            return false;
        });
    }

    @Override
    public Optional<List<T>> getAll(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(persistenceClass());
        Root<T> root = criteriaQuery.from(persistenceClass());
        criteriaQuery = criteriaQuery.select(root);
        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
        List<T> objects = typedQuery.getResultList();

        return ExceptionHandler.handle(() -> Optional.of(objects), Optional.empty());
    }

    @Override
    public Optional<T> getById(ID id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        T object = entityManager.find(persistenceClass(), id);
        return ExceptionHandler.handle(() -> Optional.of(object), Optional.empty());
    }
}
