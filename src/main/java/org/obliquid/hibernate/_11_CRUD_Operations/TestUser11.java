package org.obliquid.hibernate._11_CRUD_Operations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser11 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();
        public static void saveUser(){
        	System.out.println("inside saveUser");
        	Session session = sessionFactory.openSession();
            session.beginTransaction();
        	User11 usersave = new User11();
            usersave.setUserName("Yogesh");
            session.save(usersave);
            session.evict(usersave);
            System.out.println(session.save(usersave));;//returns generated identifier
            //The above operation will create duplicate objects in the database and
            //this is where the Save method differs from Persist
            session.getTransaction().commit();
            session.close();
        }
        public static void persistUser(){
        	System.out.println("inside persistUser");

        	//persist() makes a transient instance persistent. However, 
        	//it does not guarantee that the identifier value will be assigned 
        	//to the persistent instance immediately, the assignment might happen at flush time.
        	//persist() also guarantees that it will not execute an INSERT statement 
        	//if it is called outside of transaction boundaries.
        	//This is useful in long-running conversations with an extended Session/persistence context.
        	  Session session = sessionFactory.openSession();
              session.beginTransaction();
        	   User11 userPersist = new User11();
            userPersist.setUserName("Yogesh");
            session.persist(userPersist); //Return type of persist is void
            //If we detach this object from the session and attach it again, 
            //then it will throw a Persistence Exception which will not happen in session.save()
            session.evict(userPersist);
            session.persist(userPersist);
            session.getTransaction().commit();
            session.close();
        }
        public static void mergeUser(){
        	//The Merge method is used to update the Persistent object with new values 
        	//from a Detached object.
        	//This is used especially used in Serialization / Deserialization use cases.

        	//The main intention of the merge method is to update a persistent entity instance 
        	//with new field values from a detached entity instance.
        	
        	 Session session = sessionFactory.openSession();
             session.beginTransaction();
        	 User11 userMerge = new User11();
             userMerge.setUserName("Yogesh");
             session.save(userMerge);
             session.evict(userMerge);
             userMerge.setUserName("Indrayani");
             session.merge(userMerge); //If we use session.save() here it will create different object
             session.getTransaction().commit();
             session.close();
        }
        public static void create10Users(){
        	Session session = sessionFactory.openSession();
            session.beginTransaction();
            //Create
            //Create 10 user objects using below query
            for(int i=0;i<10;i++){
          	  User11 user = new User11();
                user.setUserName("User"+i);
                session.save(user) ;
            }
            session.getTransaction().commit();
            session.close();
        }
        public static void getUser(){
        	Session session = sessionFactory.openSession();
            session.beginTransaction();
              
              //GET
              User11 user = (User11) session.get(User11.class, 6);
              System.out.println("User name is before session close : "+user.getUserName());
              
              //Try to access the object that does not exist using session.get
             User11 userGet = (User11) session.get(User11.class, 100);
             System.out.println("userGet is "+ userGet); // null will be return. get() will hit DB
             
              //Try to access the object that does not exist using session.Load
             /* User11 userLoad = (User11) session.load(User11.class, 100);
              System.out.println("userLoad is "+ userLoad);*/ //Exception is thrown
              // No row with the given identifier exists
              
              session.getTransaction().commit();
              session.close();
              System.out.println("User name is After  session close : "+user.getUserName());
              //we can get username after session is close
              //this is becoz of hibernate Proxy object
              //they are saved in first level cache by default.So we can get it after session close
              
        }
        public static void updateUser(){
        	//Update
             Session session1 = sessionFactory.openSession();
             session1.beginTransaction();
             User11 user1 = (User11) session1.get(User11.class, 6);
             session1.getTransaction().commit();
             session1.close();
             user1.setUserName("Updated Username");
             session1 = sessionFactory.openSession();
             session1.beginTransaction();
             session1.update(user1);//Detached object is attached to persistent context
             session1.getTransaction().commit();
             session1.close();
        }
        public static void  deleteUser(){
        	Session session2 = sessionFactory.openSession();
            session2.beginTransaction();
            User11 user2 = (User11) session2.get(User11.class, 6);
            session2.delete(user2);
            session2.getTransaction().commit();
            session2.close();
        }
        private static void updateVsMerge() {
        	 Session session1 = sessionFactory.openSession();
             session1.beginTransaction();
             saveUser();
             User11 user1 = (User11) session1.get(User11.class, 1);
             session1.close();
             user1.setUserName("Updated Username");
        	 Session session2 = sessionFactory.openSession();
             User11 user1New = (User11) session2.get(User11.class, 1);
             session2.beginTransaction();
             System.out.println(user1 == user1New);
             session2.update(user1); //this will fail with below exception
             //a different object with the same identifier value was already associated with the session
             //if we use session.merge() then no exception.
           //  session2.merge(user1);
             session2.getTransaction().commit();
             
		}
        private static void saveVsSaveOrUpdate() {
        	Session session = sessionFactory.openSession();
            session.beginTransaction();
        	User11 usersave = new User11();
            usersave.setUserName("Yogesh");
            session.save(usersave);
            session.evict(usersave);
           // session.save(usersave); -> If we use save here then duplicate entry will get inserted

            session.saveOrUpdate(usersave); 
            //saveOrUpdate -> either INSERT or UPDATE SQL query depending upon 
            //whether an object exists in the database or not.
            
            //save() -> Transient to Persistent
            //saveOrUpdate -> Transient to Persistent and Detached to Persistent
            				//often used to re-attach a detached object into a Session

            session.getTransaction().commit();
            session.close();
        	
		}
        public static void main(String[] args) {
        	
        	//saveUser();
        	//persistUser();
        	//mergeUser();
        	
        	//updateVsMerge();
        	saveVsSaveOrUpdate();
        	create10Users();
        	//getUser();
        	//updateUser();
            //deleteUser();
        	
        	
        	//Difference in update vs merge ->
        	//merge() -> It first checks the same object exist in cache
        	//If exist then it will update the cache with the changes, 
        	//else if object is not exist in cache then it will load the values to cache.

        	//When we call update() method, if the same object is already exist in session cache 
        	//then the update() method throws an exception called “NonUniqueObjectException”     
        	
        	//if the same object is not exist in session cache then the update() method will update the object.
        }
		
		

       

}
