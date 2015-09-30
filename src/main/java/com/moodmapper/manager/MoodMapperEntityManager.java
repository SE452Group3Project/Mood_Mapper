/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.GroupsEntity;
import com.moodmapper.entity.UsersEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author faithfulokoye
 */
public class MoodMapperEntityManager {
   
    private final EntityManager em;         
//    private final EntityManagerFactory emf;  
    
    public MoodMapperEntityManager(EntityManagerFactory emf) {
        emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager(); 
    }
    
    /* GROUPS */ 
    
    public void createGroup(GroupsEntity group) {
        em.getTransaction().begin(); 
        em.persist(group); 
        
    }
    
    public void updateGroup(GroupsEntity group) {
        em.getTransaction().begin(); 
        em.merge(group); 
    }
    
    public void removeGroup(GroupsEntity group) {
        em.getTransaction().begin(); 
        em.remove(group); 
       
    }
    
    public void commit() {
        em.getTransaction().commit(); 
    }
    
    /* USERS */
    
    public void createUser(UsersEntity user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public void updateUser(UsersEntity user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
    }

    public void removeUser(UsersEntity user) {
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    
    
    public void close() {
        em.close(); 
    }
}
