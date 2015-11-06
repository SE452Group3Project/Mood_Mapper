/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moodmapper.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author faithfulokoye
 */
public class MMEntityServiceTest {
    
        
    private static EntityManagerFactory emf; 
    private EntityManager em;
      
   private static GroupEntity group1; 
   private static GroupEntity group2; 
   private static UserEntity ownerId;
   
   private static CommentEntity comment1; 
   private static MoodStatusEntity moodstatus1; 
 
  private static final UserEntity group_member1 = new UserEntity(2, "jenny", "23828937rdk", "2839797@2389.com"); 
  private static final UserEntity group_member2 = new UserEntity(3, "jenny101", "23828937rdkdiwi", "2839dw797@23dkw89.com"); 
  
    public MMEntityServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try {
                   emf = Persistence.createEntityManagerFactory("MoodMapperTestPU--noDataSource"); 


       } catch( Exception e){
           e.printStackTrace();
       }
        
        ownerId = new UserEntity(1, "huang10", "2333928dd", "2334@134dd4.com");
       group1 = new GroupEntity(1, "friends");
       group2 = new GroupEntity(2, "se452"); 
       
       
       
                         em = emf.createEntityManager();

       

       moodstatus1 = new MoodStatusEntity(); 
       moodstatus1.setId(1);
       moodstatus1.setDescriptiveWord("great");
       moodstatus1.setEnergyRating(3); 
       moodstatus1.setIsPrivate(Boolean.TRUE);
       moodstatus1.setPleasantnessRating(3);
       moodstatus1.setReflectiveParagraph("He will finish what He started");
       
       comment1 = new CommentEntity(); 
       comment1.setContent("This is really inspirational");
       comment1.setId(1);
    }
    
    @After
    public void tearDown() {
        emf.close();
        
    }

    /**
     * Test of save method, of class MMEntityService.
     */
    //@Test
    public void testSave() {
       ownerId.save(emf); 

       group1.setOwner(ownerId); 
       group2.setOwner(ownerId);
       
       group1.addGroupMember(group_member1);   
       group2.addGroupMember(group_member2); 
       group1.addGroupMember(group_member2);
      
      
       group2.save(emf); 
       group1.save(emf); 
       
       
       moodstatus1.setUser(group_member1);
       moodstatus1.save(emf); 
       
       comment1.setMoodStatus(moodstatus1);
       comment1.setUser(group_member2);
       comment1.save(emf); 
       
   
       GroupEntity grouptest1 = em.find(GroupEntity.class, 1);   
       
       System.out.println("After creation of groups in table");
       System.out.println("ID: " + grouptest1.getId());
       System.out.println("Group Name: " + grouptest1.getName());
       System.out.println("Join Code: " + grouptest1.getJoinCode());
       System.out.println("Owner: " + grouptest1.getOwner().toString());
       
      
  
        System.out.println("After creation of user in table");
        System.out.println("Owner ID: " + grouptest1.getOwner().getId());
        System.out.println("Owener Username: " + grouptest1.getOwner().getUsername());
        System.out.println("Owener Email: " + grouptest1.getOwner().getEmail());
        System.out.println("Owener Groups Owned: ");
        Collection<GroupEntity> groupsOwned = grouptest1.getOwner().getGroupsOwned();
        for (GroupEntity element : groupsOwned) {
            System.out.print(element.getName() + " "); 
        }
        System.out.println();
        
        System.out.println("Group Members: ");
        Collection<UserEntity> groupMembers = grouptest1.getGroupMembers();
        for (UserEntity element : groupMembers) {
            System.out.print(element.getUsername() + ": "); 
            System.out.print("Groups joined: "); 
            
            Collection<GroupEntity> groupsJoined = element.getGroupsJoined();
            for (GroupEntity element2 : groupsJoined) {
                System.out.print(element2.getName() + " "); 
            }
            System.out.println("");
        }
        
        MoodStatusEntity moodStatus1 = em.find(MoodStatusEntity.class, 1); 
        

        System.out.println("After inserting MoodStatus, Comments & Owners of Each: "); 
        System.out.println("Reflective Paragraph: " + moodStatus1.getReflectiveParagraph());
        System.out.println("Descriptive Word: " + moodStatus1.getDescriptiveWord());
        System.out.println("Mood Status Owner Username: " + moodStatus1.getUser().getUsername()); 
        System.out.println("Reverse: Owner Mood Statuses: "); 
        
        Collection<MoodStatusEntity> moodStatuses = moodStatus1.getUser().getMoodStatuses();
        for (MoodStatusEntity element : moodStatuses) {
            
            System.out.println("Descriptive word: " + element.getDescriptiveWord()); 
        }
        
        System.out.println("Reverse - Owner Comments: "); 
        
        Collection<CommentEntity> comments = moodStatus1.getUser().getComments();
        for (CommentEntity element : comments) {
            
            System.out.println("Comment:" + element.getContent()); 
        }
        
         System.out.println("Mood Statuses Comments: "); 
        
        Collection<CommentEntity> comments2 = moodStatus1.getComments();
        for (CommentEntity element : comments2) {
            
            System.out.println("Comment: " + element.getContent()); 
            System.out.println("Reverse - Comment's Mood Status Descriptive Word: " + element.getMoodStatus().getDescriptiveWord()); 
            System.out.println("Reverse - Comment's Owner Username" + element.getUser().getUsername()); 

        }
        
        String userNameSearched = "JeNNy";


        TypedQuery<UserEntity> query = em.createNamedQuery("UserEntity.searchByUsername", UserEntity.class).setParameter("username", "%" + userNameSearched + "%");
        List<UserEntity> searchResults = query.getResultList();

        for (UserEntity element : searchResults) {
            System.out.println(element.getUsername() + " was found!");
            System.out.println("Id: " + element.getId());
                }
    
    

       String groupNameSearched = "friends";   

       TypedQuery<GroupEntity> groupQuery = em.createNamedQuery("GroupEntity.searchByGroupName", GroupEntity.class).setParameter("name", "%" + groupNameSearched + "%");
       List<GroupEntity> groupSearchResults = groupQuery.getResultList();

       for (GroupEntity element : groupSearchResults) {
           System.out.println(element.getName() + " was found!");
           System.out.println("Id:  " + element.getId());
               }
           }

}
