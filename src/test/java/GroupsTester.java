
import com.moodmapper.entity.GroupsEntity;
import com.moodmapper.entity.UsersEntity;
import com.moodmapper.manager.GroupsEntityJpaController;
import com.moodmapper.manager.UsersEntityJpaController;
import java.util.Collection;
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
   private EntityManager em;
   private GroupsEntityJpaController gm; 
   private UsersEntityJpaController um; 
   
   //private MoodMapperEntityManager mmEntityManager; 
   
   private GroupsEntity group1; 
   private GroupsEntity group2; 
   private UsersEntity ownerId;
 
   private final UsersEntity group_member1 = new UsersEntity(2, "jenny", "23828937rdk", "2839797@2389.com"); 
   private final UsersEntity group_member2 = new UsersEntity(3, "jenny101", "23828937rdkdiwi", "2839dw797@23dkw89.com"); 
   
   protected void setUp() throws Exception {
       emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU"); 
      
       em = emf.createEntityManager();
       gm = new GroupsEntityJpaController(emf); 
       um = new UsersEntityJpaController(emf); 
        
   }
   
   protected void close() throws Exception {
       gm.close(); 
       em.close();
       emf.close(); 
   }
   
   public void test() {
       
       um.createUser(group_member1);
       um.createUser(group_member2); 
       
       ownerId = new UsersEntity(1, "huang10", "2333928dd", "2334@134dd4.com");
       group1 = new GroupsEntity(1, "friends", "dkslsjiewiou");
       group2 = new GroupsEntity(2, "se452", "sklduwioeu"); 
       group1.setOwner(ownerId);
       group2.setOwner(ownerId);
       
       gm.createGroup(group1);
       gm.createGroup(group2); 
       
       System.out.println("After creation of groups in table");
       System.out.println("ID: " + group1.getId());
       System.out.println("Group Name: " + group1.getName());
       System.out.println("Join Code: " + group1.getJoinCode());
       System.out.println("Owner: " + group1.getOwner().toString());
       
       group1.addGroupMember(group_member1);
       
       group2.addGroupMember(group_member2); 
       gm.updateGroup(group2); 
       
       group1.addGroupMember(group_member2);
       gm.updateGroup(group1);
     
        System.out.println("After creation of user in table");
        System.out.println("Owner ID: " + group1.getOwner().getId());
        System.out.println("Owener Username: " + group1.getOwner().getUsername());
        System.out.println("Owener Email: " + group1.getOwner().getEmail());
        System.out.println("Owener Groups Owned: ");
        Collection<GroupsEntity> groupsOwned = group1.getOwner().getGroupsOwned();
        for (GroupsEntity element : groupsOwned) {
            System.out.print(element.getName() + " "); 
        }
        System.out.println();
        
        System.out.println("Group Members: ");
        Collection<UsersEntity> groupMembers = group1.getGroupMembers();
        for (UsersEntity element : groupMembers) {
            System.out.print(element.getUsername() + ": "); 
            System.out.print("Groups joined: "); 
            
            Collection<GroupsEntity> groupsJoined = element.getGroupsJoined();
            for (GroupsEntity element2 : groupsJoined) {
                System.out.print(element2.getName() + " "); 
            }
            System.out.println("");
        }
        
        
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
