/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.GroupEntity;
import com.moodmapper.entity.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author faithfulokoye
 */
public class GroupService implements Serializable {
    
    private EntityManager em;         
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
    
    public GroupService(EntityManagerFactory emf) {
        em = emf.createEntityManager(); 
    }
    
//    public void saveGroup(GroupEntity group){
//       EntityTransaction tx = em.getTransaction(); 
//       
//       tx.begin(); 
//       GroupEntity groupToUpdate; 
//       if((group.getId() != null) && (group.getId() > 0)) {
//           groupToUpdate = em.find(GroupEntity.class, group.getId()); 
//           groupToUpdate.setName(group.getName());
//           groupToUpdate.setJoinCode(group.getJoinCode());
//           groupToUpdate.setOwner(group.getOwner());
//           groupToUpdate.
//       }
//       
//    }
    
    
    public void createGroup(GroupEntity group) {
        em.getTransaction().begin(); 
        em.persist(group); 
        em.getTransaction().commit(); 
    }
    
    public GroupEntity searchById(Integer id){
        return em.find(GroupEntity.class, id); 
    }
    
    public void updateGroup(GroupEntity group) {
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
    public void removeGroup(GroupEntity group) {
        em.getTransaction().begin(); 
        em.remove(group); 
        em.getTransaction().commit(); 
       
    }
    
    public void setOwner(int groupId, int userId) {
        GroupEntity group = em.find(GroupEntity.class, groupId); 
        UserEntity user = em.find(UserEntity.class, userId);
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
