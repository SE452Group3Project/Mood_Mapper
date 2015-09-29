
import com.moodmapper.entity.GroupsEntity;
import com.moodmapper.entity.UsersEntity;
import com.moodmapper.manager.GroupsEntityJpaController;
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
    
   private GroupsEntityJpaController gm; 
   private EntityManager em; 
   private EntityManagerFactory emf; 
   private GroupsEntity group1; 
   
   private UsersEntityJpaController um; 
   private EntityManager users_em; 
   private EntityManagerFactory users_emf; 
   private final UsersEntity ownerId = new UsersEntity(3, "huang5", "2333928dd", "2334@134dd4.com"); 

   
   
   
   protected void setUp() throws Exception {
       emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
       em = emf.createEntityManager(); 
       gm = new GroupsEntityJpaController(emf); 
       
       um = new UsersEntityJpaController(emf); 
       um.createUser(ownerId); 
       
       group1 = new GroupsEntity(1, "friends", "83726dd", ownerId); 

   }
   
   protected void close() throws Exception {
       gm.close(); 
       em.close(); 
       emf.close(); 
   }
   
   public void test() {
       gm.createGroup(group1);
       System.out.println("After creation of user in table");
       System.out.println("ID: " + group1.getId());
       System.out.println("Username: " + group1.getName());
       System.out.println("Join Code: " + group1.getJoinCode());
       System.out.println("Owner: " + group1.getOwner());
       
       
       System.out.println("After creation of user in table");
        System.out.println("ID: " + group1.getOwner().getId());
        System.out.println("Username: " + group1.getOwner().getUsername());
        System.out.println("Email: " + group1.getOwner().getEmail());
       
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
