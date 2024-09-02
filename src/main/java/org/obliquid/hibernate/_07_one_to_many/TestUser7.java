package org.obliquid.hibernate._07_one_to_many;

import javax.persistence.FetchType;
import javax.persistence.JoinTable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser7 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser7 instance = new TestUser7();
                instance.insertUser();
        }

        private void insertUser() {
                User7 user = new User7();
                user.setUserName("stivlo");

                Vehicle7 vehicle7=new Vehicle7();
                vehicle7.setVehicleName("Tiago");

                Vehicle7 vehicle71=new Vehicle7();
                vehicle71.setVehicleName("Creta");
                
                user.getVehicleList().add(vehicle7);
                user.getVehicleList().add(vehicle71);
                
                //We have to user SetUser for all vehicles as we have added ManyToOne in Vehicle class 
                //For Bidirectional relationship
                vehicle7.setUser7(user);
               vehicle71.setUser7(user);
                
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(user);
                session.save(vehicle7);
                session.save(vehicle71);

                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
                user = (User7) session.get(User7.class, 1);
                System.out.println(user);
                
                
               Vehicle7 vehicle = (Vehicle7) session.get(Vehicle7.class, 1);
                System.out.println(vehicle.getUser7());
                
                //Point to remember is that Hibernate is adding one mapping table declared as per  @JoinTable
                //and join and inverse join columns
                //One user can have multiple vehicles .so we cannot have vehicle id in user table
                //But in Vehicle Table , we can have user id also .which is bidirectional relationship as
                //discussed above. We have to add User Object with ManyToOne annotation in Vehicle table
                //In next example we will eliminate this common table. 
               
        }

}
