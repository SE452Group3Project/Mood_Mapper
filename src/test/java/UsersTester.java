/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.moodmapper.manager.UserService;
import com.moodmapper.entity.UserEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Suqing
 */
public class UsersTester {

    private UserService um;
    private EntityManager em;
    private EntityManagerFactory emf;
    private static final UserEntity user1 = new UserEntity(1, "huang1", "123", "123@123.com");
    private static final UserEntity user2 = new UserEntity(2, "huang2", "1234", "1234@1234.com");

    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory("com.moodmapper_MoodMapper_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        um = new UserService(emf);
    }

    protected void close() throws Exception {
        um.close();
        em.close();
        emf.close();
    }

    public void test() {
        um.createUser(user1);
        UserEntity user = um.searchById(1);
        System.out.println("After creation of user in table");
        System.out.println("ID: " + user.getId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());

        user.setUsername("Suqing");
        user.setEmail("aaa@gamil.com");
        user.setFirstName("Suqing");
        user.setLastName("Huang");
        um.updateUser(user);

        user = um.searchById(1);
        System.out.println("After update.");
        //System.out.println("ID: " + user.getId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());

        System.out.println("Adding two more record");
        um.createUser(user2);
        UserEntity user3 = new UserEntity(3, "huang3", "12345", "12345@12345.com");
        um.createUser(user3);
        user3.setLastName("HUANG");
        um.createUser(user3);

        List list = um.getAll();
        System.out.println("Number of users: " + list.size());
    }

    public static void main(String args[]) {
        System.out.println("Inside TestJPA main");
        UsersTester testJPA = new UsersTester();
        try {
            testJPA.setUp();
            testJPA.test();
            testJPA.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("End of TestJPA main");
    }
}
