package org.obliquid.hibernate._10_CascadeTypes_OtherThings;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser10 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser10 instance = new TestUser10();
                instance.insertUser();
        }

        private void insertUser() {
                User10 user = new User10();
                user.setUserName("stivlo");
               
                
                Vehicle10 vehicle10=new Vehicle10();
                vehicle10.setVehicleName("Car");

                Vehicle10 vehicle101=new Vehicle10();
                vehicle101.setVehicleName("Jeep");
                
                user.getVehicleList().add(vehicle10);
                user.getVehicleList().add(vehicle101);
               
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
               // session.save(user) ; //If we use save then we cannot mention cascadeType as PERSIST
                
                session.persist(user); 
                //We have to use persist instead of save when we mention
                //cascade type as persist on one to many relation in user class.
                
                //persist() method doesn't guarantee that the identifier value will be assigned to the persistent 
                //instance immediately,  the assignment might happen at flush time.
                
                // session.save(vehicle10);
                // session.save(vehicle101);
                //If we have huge list of vehicles , its not efficient to write vehicle.save() 
                //for all the vehicle  Objects
                //And if we dont write it and try to save only user then will get below execption
                // Exception in thread "main" org.hibernate.TransientObjectException:
                
                //So we will mention cascade = PERSIST on vehicle list in user class
                //It will automatically save all vehicle objects when we are doing session.save(user)
            
            
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
                
                user = (User10) session.get(User10.class, 1);
                System.out.println("Getting User from DB " +user);
                System.out.println("Getting VEhicleList Size from DB " +user.getVehicleList().size());
               
                //The orphanRemoval option was introduced in JPA 2.0.
                //This provides a way to delete orphaned entities from the database.
                //While CascadeType.REMOVE is a way to delete a child entity or 
                //entities whenever the deletion of its parent happens.
                
                
                //deleteOrphanRemoval();
                deleteUserAndVehicles();
                
               
        }
      
        
		private void deleteUserAndVehicles() {
			 System.out.println("Delete User And assoictaed vehicles");
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User10 user = (User10) session.get(User10.class, 1);

            session.delete(user); //User and associated vehicles will get deleted
            session.getTransaction().commit();
            session.close();
		}
		
		private void deleteOrphanRemoval() {
			 System.out.println("deleteOrphanRemoval");
           Session session = sessionFactory.openSession();
           session.beginTransaction();
           User10 user = (User10) session.get(User10.class, 1);
           System.out.println("Vehicle List size before removing association : " + user.getVehicleList().size());

           System.out.println("Removing one vehicle assoicated with User");
           user.getVehicleList().remove(user.getVehicleList().iterator().next());
           System.out.println("Vehicle List size after removing association : : " + user.getVehicleList().size());

           session.getTransaction().commit();
           session.close();
		}

}
