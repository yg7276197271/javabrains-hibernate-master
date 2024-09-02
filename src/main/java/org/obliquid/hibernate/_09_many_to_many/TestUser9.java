package org.obliquid.hibernate._09_many_to_many;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser9 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser9 instance = new TestUser9();
                instance.insertUser();
        }

        private void insertUser() {
                User9 user = new User9();
                user.setUserName("stivlo");
               
                
                Vehicle9 vehicle9=new Vehicle9();
                vehicle9.setVehicleName("Tiago");

                Vehicle9 vehicle91=new Vehicle9();
                vehicle91.setVehicleName("Creta");
                
                user.getVehicleList().add(vehicle91);
                user.getVehicleList().add(vehicle9);
                
                vehicle9.getUserList().add(user);
                vehicle91.getUserList().add(user);
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(user);
                session.save(vehicle9);
                session.save(vehicle91);
                
            
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
                user = (User9) session.get(User9.class, 1);
                System.out.println(user);
                
                
               
        }

}
