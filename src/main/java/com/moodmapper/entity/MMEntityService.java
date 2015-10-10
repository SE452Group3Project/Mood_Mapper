/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 *
 * @author faithfulokoye
 */
public class MMEntityService implements Serializable {
    
    public void save(EntityManagerFactory emf) {
      EntityManager em; 
      em = emf.createEntityManager();
      EntityTransaction tx = em.getTransaction(); 
       
       tx.begin(); 
       em.merge(this); 
       tx.commit(); 
       em.close(); 
    }
    
}
