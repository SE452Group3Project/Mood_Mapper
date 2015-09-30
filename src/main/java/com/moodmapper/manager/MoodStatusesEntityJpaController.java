/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.MoodStatusesEntity;
import com.moodmapper.entity.UsersEntity;
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
public class MoodStatusesEntityJpaController implements Serializable {
    
    private EntityManager em;         
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
    
    public MoodStatusesEntityJpaController(EntityManagerFactory emf) {
        em = emf.createEntityManager(); 
    }
    
    public void createMoodStatus(MoodStatusesEntity moodStatus) {
        em.getTransaction().begin(); 
        em.persist(moodStatus); 
        em.getTransaction().commit(); 
    }
    
    public MoodStatusesEntity searchById(Integer id){
        return em.find(MoodStatusesEntity.class, id); 
    }
    
    public void updateMoodStatus(MoodStatusesEntity moodStatus) {
        em.getTransaction().begin(); 
        em.merge(moodStatus); 
        em.getTransaction().commit(); 
    }
    
    public void removeMoodStatus(MoodStatusesEntity moodStatus) {
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
