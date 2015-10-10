/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.manager;

import com.moodmapper.entity.CommentEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author jasekurasz
 */
public class CommentService implements Serializable {
    private EntityManager em; 
    
    public CommentService(EntityManagerFactory emf) {
        em = emf.createEntityManager(); 
    }
    
    public void createComment(CommentEntity comment) {
        em.getTransaction().begin(); 
        em.persist(comment); 
        em.getTransaction().commit(); 
    }
    
    public CommentEntity searchById(Integer id){
        return em.find(CommentEntity.class, id); 
    }
    
    public void updateComment(CommentEntity comment) {
        em.getTransaction().begin(); 
        em.merge(comment); 
        em.getTransaction().commit(); 
    }
    
    public void removeComment(CommentEntity comment) {
        em.getTransaction().begin(); 
        em.remove(comment); 
        em.getTransaction().commit(); 
       
    }
   
    
    public List getAll() {
        Query query = em.createQuery("select a from CommentsEntity a"); 
        List list = query.getResultList(); 
        return list; 
    }
    
    public void close() {
        em.close(); 
    }
}
