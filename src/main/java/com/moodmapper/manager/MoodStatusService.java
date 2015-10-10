/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.MoodStatusEntity;
import com.moodmapper.entity.UserEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author geoffbrown
 */
public class MoodStatusService implements Serializable {
    
    private EntityManager em;         
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
    
    public MoodStatusService(EntityManagerFactory emf) {
        em = emf.createEntityManager(); 
    }
    
    public void createMoodStatus(MoodStatusEntity moodStatus) {
        em.getTransaction().begin(); 
        em.persist(moodStatus); 
        em.getTransaction().commit(); 
    }
    
    public MoodStatusEntity searchById(Integer id){
        return em.find(MoodStatusEntity.class, id); 
    }
    
    public void updateMoodStatus(MoodStatusEntity moodStatus) {
        em.getTransaction().begin(); 
        em.merge(moodStatus); 
        em.getTransaction().commit(); 
    }
    
    public void removeMoodStatus(MoodStatusEntity moodStatus) {
        em.getTransaction().begin(); 
        em.remove(moodStatus); 
        em.getTransaction().commit(); 
       
    }
    
      
    public List getAll() {
        Query query = em.createQuery("select a from MoodStatusesEntity a"); 
        List list = query.getResultList(); 
        return list; 
    }
    
    public void close() {
        em.close(); 
    }
}
