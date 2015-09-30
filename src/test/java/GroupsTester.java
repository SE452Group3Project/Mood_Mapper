
import com.moodmapper.entity.GroupsEntity;
import com.moodmapper.entity.UsersEntity;
import com.moodmapper.manager.GroupsEntityJpaController;
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
public class GroupsTester {
    
   private EntityManagerFactory emf; 
  
   private GroupsEntityJpaController gm; 
   private UsersEntityJpaController um; 
   
   private MoodMapperEntityManager mmEntityManager; 
   
   private GroupsEntity group1; 
   private UsersEntity ownerId; 
//   private final UsersEntity group_member1 = new UsersEntity(2, "jenny", "23828937rdk", "2839797@2389.com"); 
//   private final UsersEntity group_member2 = new UsersEntity(3, "jenny101", "23828937rdkdiwi", "2839dw797@23dkw89.com"); 
   
   protected void setUp() throws Exception {
       emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
      
       gm = new GroupsEntityJpaController(emf); 
       um = new UsersEntityJpaController(emf); 
       
       ownerId = new UsersEntity(1, "huang10", "2333928dd", "2334@134dd4.com");
      
       group1 = new GroupsEntity(1, "friends", "dkslsjiewiou");
       group1.setOwner(ownerId);
       
       gm.createGroup(group1);
//       mmEntityManager = new MoodMapperEntityManager(emf); 
//       mmEntityManager.createGroup(group1);
//       mmEntityManager.commit();

        
   }
   
   protected void close() throws Exception {
       gm.close(); 
       emf.close(); 
   }
   
   public void test() {
       gm.createGroup(group1);
       System.out.println("After creation of groups in table");
       System.out.println("ID: " + group1.getId());
       System.out.println("Username: " + group1.getName());
       System.out.println("Join Code: " + group1.getJoinCode());
       System.out.println("Owner: " + group1.getOwner());
       
       
       System.out.println("After creation of user in table");
        System.out.println("ID: " + group1.getOwner().getId());
        System.out.println("Username: " + group1.getOwner().getUsername());
        System.out.println("Email: " + group1.getOwner().getEmail());
        System.out.println("Groups Owned: " + group1.getOwner().getGroupsOwned().iterator().next().getName());
        
        
        System.out.println("List all group members");
        System.out.println("Group Members: " + group1.getGroupMembers());
       
   }
   
   public static void main(String args[])
   {
       System.out.println("Inside TestJPA main"); 
       GroupsTester testJPA = new GroupsTester(); 
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
