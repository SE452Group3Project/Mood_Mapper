import com.moodmapper.entity.CommentsEntity;
import com.moodmapper.entity.MoodStatusesEntity;
import com.moodmapper.entity.UsersEntity;
import com.moodmapper.manager.CommentsEntityJpaController;
import com.moodmapper.manager.MoodStatusesEntityJpaController;
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
 * @author jasekurasz
 */
public class CommentsTester {
   private CommentsEntityJpaController cm; 
   private EntityManager em; 
   private EntityManagerFactory emf; 
   private CommentsEntity comment1;
   
   private UsersEntityJpaController um; 
   private MoodStatusesEntityJpaController mm;
   private EntityManager users_em; 
   private EntityManagerFactory users_emf; 
   private final UsersEntity ownerId = new UsersEntity(4, "huang11", "fhaklgkewalg", "agekwlfes@gmail.com");
   private final MoodStatusesEntity moodId = new MoodStatusesEntity(1, ownerId, 4, "Happy", "A Reflective Paragraph Here.", 7, true);
   
   protected void setUp() throws Exception {
       emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU");
       em = emf.createEntityManager();
       cm = new CommentsEntityJpaController(emf);
       mm = new MoodStatusesEntityJpaController(emf);
       um = new UsersEntityJpaController(emf);
   }
   
   protected void close() throws Exception {
       cm.close();
       em.close();
       emf.close();
   }
   
   public void test() {
       um.createUser(ownerId);
       mm.createMoodStatus(moodId);
       
       comment1 = new CommentsEntity(1, "new comment string", moodId, ownerId);
       cm.createComment(comment1);
       System.out.println("After creation of comment in table");
       System.out.println("ID: " + comment1.getId());
       System.out.println("Mood Status ID: " + comment1.getMoodStatus().getId());
       System.out.println("User ID: " + comment1.getUser().getId());
       System.out.println("Content: " + comment1.getContent());
       System.out.println("Timestamp: " + comment1.getTimestamp());
       
       System.out.println("After creation of user in table");
       System.out.println("Username: " + comment1.getUser().getUsername());
       System.out.println("Email: " + comment1.getUser().getEmail());
   }
       
   public static void main(String args[]) 
   {
       System.out.println("Inside TestJPA main"); 
       CommentsTester testJPA = new CommentsTester(); 
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
