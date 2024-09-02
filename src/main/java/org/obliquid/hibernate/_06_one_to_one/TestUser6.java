package org.obliquid.hibernate._06_one_to_one;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser6 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser6 instance = new TestUser6();
                instance.insertUser();
        }

        private void insertUser() {
                User6 user = new User6();
                user.setUserName("stivlo");

                Vehicle6 vehicle6=new Vehicle6();
                vehicle6.setVehicleName("Tiago");

                user.setVehicle6(vehicle6);
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(user);
                session.save(vehicle6);
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
                user = (User6) session.get(User6.class, 1);
                System.out.println(user);
                
                
               
        }

}
