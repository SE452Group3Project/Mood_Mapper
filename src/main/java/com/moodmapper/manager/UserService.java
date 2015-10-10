/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Suqing
 */
public class UserService implements Serializable {

    private EntityManager em;

    public UserService(EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    public void createUser(UserEntity user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public UserEntity searchById(Integer id) {
        return em.find(UserEntity.class, id);
    }

    public void updateUser(UserEntity user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void removeUser(UserEntity user) {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    public List getAll() {
        Query query = em.createQuery("select a from UsersEntity a");
        List list = query.getResultList();
        return list;
    }

    public void close() {
        em.close();
    }
}
