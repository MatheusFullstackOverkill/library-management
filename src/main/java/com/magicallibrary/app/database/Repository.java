package com.magicallibrary.app.database;

import java.util.HashMap;
import java.util.List;

import org.hibernate.query.Query;

import com.magicallibrary.app.App;

import jakarta.persistence.NoResultException;

public abstract class Repository {
    protected static <T> List<T> baseList(Class<T> listClass, QueryStringWrapper queryStringWrapper, HashMap<String, Object> params) {
        App.session.beginTransaction();

        System.out.println(queryStringWrapper.queryString);
        System.out.println(params);

        Query<T> resultsQuery = App.session.createQuery(queryStringWrapper.queryString, listClass);

        if (params != null) {
            params.forEach((key, value) -> resultsQuery.setParameter(key, value));
        };

        List<T> results = resultsQuery.list();

        App.session.getTransaction().commit();

        return results;
    }

    protected static <T> T baseCreate(T entity) {
        App.session.beginTransaction();

        App.session.persist(entity);

        App.session.getTransaction().commit();

        return entity;
    }

    protected static <T> T baseRetrieve(Class<T> retrieveClass, long id) {
        App.session.beginTransaction();

        Query<T> query = App.session.createQuery("FROM " + retrieveClass.getName() + " WHERE id = :id", retrieveClass);
        query.setParameter("id", id);

        try {
            var result = query.getSingleResult();
            App.session.getTransaction().commit();

            return result;
        } catch (NoResultException e) {
            App.session.getTransaction().commit();

            return null;
        }
    }

    protected static <T> T baseUpdate(Class<T> retrieveClass, T entity) {
        App.session.beginTransaction();

        T mergedObject = App.session.merge(entity);

        App.session.getTransaction().commit();

        return mergedObject;
    }

    protected static <T> T baseDelete(Class<T> deleteClass, T entity) {
        App.session.beginTransaction();

        App.session.remove(entity);

        App.session.getTransaction().commit();

        return entity;
    };
}
