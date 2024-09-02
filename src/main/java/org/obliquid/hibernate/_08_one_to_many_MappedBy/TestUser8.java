package org.obliquid.hibernate._08_one_to_many_MappedBy;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;
import org.obliquid.hibernate._07_one_to_many.Vehicle7;

public class TestUser8 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser8 instance = new TestUser8();
                instance.insertUser();
        }

        private void insertUser() {
                User8 user = new User8();
                user.setUserName("stivlo");

                Vehicle8 vehicle8=new Vehicle8();
                vehicle8.setVehicleName("Tiago");

                Vehicle8 vehicle81=new Vehicle8();
                vehicle81.setVehicleName("Creta");
                
                user.getVehicleList().add(vehicle81);
                user.getVehicleList().add(vehicle81);
                
                //We have to user SetUser for all vehicles as we have added ManyToOne in Vehicle class 
                //For Bidirectional relationship
                vehicle8.setUser8(user);
                vehicle81.setUser8(user);
                
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(user);
                session.save(vehicle8);
                session.save(vehicle81);
                //Only 2 tables will be created .No third table for maintaining ids
                //As we have used bidirectional relationship with mapped by
                //Use oneTomany with mappedBy in user class
                //Use ManyToOne with join column in Vehicle class
            
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
                user = (User8) session.get(User8.class, 1);
                System.out.println(user);
                
                Vehicle8 vehicle = (Vehicle8) session.get(Vehicle8.class, 1);
                System.out.println(vehicle.getUser8());
               
        }

}
