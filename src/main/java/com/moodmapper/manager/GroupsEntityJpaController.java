/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.GroupsEntity;
import com.moodmapper.entity.UsersEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author faithfulokoye
 */
public class GroupsEntityJpaController implements Serializable {
    
    private EntityManager em;         
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
    
    public GroupsEntityJpaController(EntityManagerFactory emf) {
        em = emf.createEntityManager(); 
    }
    
    public void createGroup(GroupsEntity group) {
        em.getTransaction().begin(); 
        em.merge(group); 
        em.getTransaction().commit(); 
    }
    
    public GroupsEntity searchById(Integer id){
        return em.find(GroupsEntity.class, id); 
    }
    
    public void updateGroup(GroupsEntity group) {
        em.getTransaction().begin(); 
        em.merge(group); 
        em.getTransaction().commit(); 
    }
    
    public void begin() {
        em.getTransaction().begin(); 
    }
    
    public void commit() {
        em.getTransaction().commit(); 
       
    }
    public void removeGroup(GroupsEntity group) {
        em.getTransaction().begin(); 
        em.remove(group); 
        em.getTransaction().commit(); 
       
    }
    
    public void setOwner(int groupId, int userId) {
        GroupsEntity group = em.find(GroupsEntity.class, groupId); 
        UsersEntity user = em.find(UsersEntity.class, userId);
        user.addGroupOwned(group);
      
    }
    
    public List getAll() {
        Query query = em.createQuery("select a from GroupsEntity a"); 
        List list = query.getResultList(); 
        return list; 
    }
    
    public void close() {
        em.close(); 
    }
}
