
import com.moodmapper.entity.MoodStatusesEntity;
import com.moodmapper.entity.UsersEntity;
import com.moodmapper.manager.MoodStatusesEntityJpaController;
import com.moodmapper.manager.MoodMapperEntityManager;
import com.moodmapper.manager.UsersEntityJpaController;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author faithfulokoye
 */
public class MoodStatusesTester {
    
   private EntityManagerFactory emf; 
  
   private MoodStatusesEntityJpaController msm; 
   private UsersEntityJpaController um; 
   
   private MoodMapperEntityManager mmEntityManager; 
   
   private MoodStatusesEntity  moodStatus;
   private UsersEntity ownerId; 
//   private final UsersEntity group_member1 = new UsersEntity(2, "jenny", "23828937rdk", "2839797@2389.com"); 
//   private final UsersEntity group_member2 = new UsersEntity(3, "jenny101", "23828937rdkdiwi", "2839dw797@23dkw89.com"); 
   
   protected void setUp() throws Exception {
       emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
      
       msm = new MoodStatusesEntityJpaController(emf);
       um = new UsersEntityJpaController(emf); 
       
       
       ownerId = new UsersEntity(7, "UniqueUserName7", "7dkfdjklfds", "7@77.com");
       
       moodStatus = new MoodStatusesEntity(7, ownerId, 7, "Happy", "A Reflective Paragraph Here.", 7, true);
       


        
   }
   
   protected void close() throws Exception {
       msm.close(); 
       emf.close(); 
   }
   
   public void test() {
       um.createUser(ownerId);
       
       msm.createMoodStatus(moodStatus);
       System.out.println("After creation of Mood Status in table");
       System.out.println("Mood Status: " + moodStatus.getId());
       System.out.println("UserName : " + moodStatus.getUser().getUsername());
       System.out.println("Time: " + moodStatus.getTimeStamp());
       System.out.println("Pleasantness Rating: " + moodStatus.getPleasantnessRating());
       System.out.println("Descriptive Word: " + moodStatus.getDescriptiveWord());
       System.out.println("Reflective Paragraph: " + moodStatus.getReflectiveParagraph());
       System.out.println("Energy Rating: " + moodStatus.getEnergyRating());
       System.out.println("Is Private?: " + (moodStatus.getIsPrivate() ? "Yes" : "No"));
       
       
       /*System.out.println("After creation of user in table");
        System.out.println("ID: " + group1.getOwner().getId());
        System.out.println("Username: " + group1.getOwner().getUsername());
        System.out.println("Email: " + group1.getOwner().getEmail());
        System.out.println("Groups Owned: " + group1.getOwner().getGroupsOwned().iterator().next().getName());
        
        
        System.out.println("List all group members");
        System.out.println("Group Members: " + group1.getGroupMembers());*/
       
   }
   
   public static void main(String args[])
   {
       System.out.println("Inside TestJPA main"); 
       MoodStatusesTester testJPA = new MoodStatusesTester(); 
       try {
           testJPA.setUp(); 
           testJPA.test(); 
           testJPA.close(); 
       } catch (Exception e){
           e.printStackTrace();
       }
       
       System.out.println("End of TestJPA main"); 
   }    
}
