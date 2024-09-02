package org.obliquid.hibernate._12_Transient_Persistent_Detached;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser12 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
        	System.out.println("Understanding session get & delete below");
        	 User12 user = new User12();
        	 user.setUserName("Yogesh");//Transient State
        	 //We have not handed over the object to hibernate yet
        	
              Session session = sessionFactory.openSession();
              session.beginTransaction();
              session.save(user);//Once we call save then its in persistent state.
              //Whatever changes we make to the object within the session are
              //automatically saved to Db by hibernate
              //We are changing username twice within session
              //But only one update query will get fired.
              //Hibernate intelligently tracks the object and saves only last state change
              
              user.setUserName("Indrayani");
              user.setUserName("Bapat");

              session.getTransaction().commit();
              session.close();
              user.setUserName("Mihir"); // this is not updated to DB
              //As it is outside the session
            
              
              
             Session session1 = sessionFactory.openSession();
              session1.beginTransaction();
              User12 user12=new User12();//-> object is in transient state
              
              user12= (User12) session1.get(User12.class, 1);
              //Once we call session.get then the object state changes from transient to persistent
              System.out.println("User name is :"+user12.getUserName());
              
              //session1.delete(user12);//Delete will change object state from persistent to transient .
              
              session1.getTransaction().commit();
              session1.close();
              
              
            //Understanding session.update below
              System.out.println("Understanding session.update below");
              Session session2 = sessionFactory.openSession();
              session2.beginTransaction();
              User12 userUpdate= (User12) session2.get(User12.class, 1);//Persistent state
              System.out.println("User name of (already existing)  is :"+userUpdate.getUserName());
              
              session2.getTransaction().commit();
              session2.close(); //User object state changes to detached
              
              userUpdate.setUserName("Setting username after session close");
              
              session2=sessionFactory.openSession();
              session2.beginTransaction();
              System.out.println("Calling session.update()");

              session2.update(userUpdate);//Update will change user state from detached to persistent
              //And user name will change to "Setting username after session close"
          
            
              //userUpdate.setUserName("User name changed after update");
              //If we again change the use name as above then hibernate will track latest 
              //changes made to object and will save username as "User name changed after update"
              session2.getTransaction().commit();
              session2.close();
              
              
              System.out.println("understanding selectBeforeUpdate below");
               session2 = sessionFactory.openSession();
              session2.beginTransaction();
              System.out.println("Getting User Object from DB");
              User12 userselectBeforeUpdate= (User12) session2.get(User12.class, 1);//Persistent state
              session2.getTransaction().commit();
              session2.close(); //User object state changes to detached
              session2=sessionFactory.openSession();
              session2.beginTransaction();
              System.out.println("executing session.update() ");
              session2.update(userselectBeforeUpdate);
              //Update will change user state from detached to persistent
              //But we have not changed username value
              //As we have marked selectBeforeUpdate=true on Entity class
              //Hibernate is going to check for the changes first by firing select query
              //If user object values are changed  then only fire update query else no update query
              session2.getTransaction().commit();
              session2.close();

              
        }

       

}
