package org.obliquid.hibernate._03_set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser3 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser3 instance = new TestUser3();
                instance.insertUser();
        }

        private void insertUser() {
                User3 user = new User3();
                user.setUserName("stivlo");

                User3 user1 = new User3();
                user1.setUserName("Yogesh");
                
                Address addr1 = new Address();
                addr1.setStreet("Street Name");
                addr1.setCity("City Name");
                addr1.setPostCode("postcode");

                Address addr2 = new Address();
                addr2.setStreet("Street Name 2");
                addr2.setCity("City Name 2");
                addr2.setPostCode("postcode 2");
                
                Address addr3 = new Address();
                addr3.setStreet("Street Name 3");
                addr3.setCity("City Name 3");
                addr3.setPostCode("postcode 3");

                user.getListOfAddresses().add(addr1);
                user.getListOfAddresses().add(addr2);

                user1.getListOfAddresses().add(addr3);

                
                Session session = sessionFactory.getCurrentSession();
                session.beginTransaction();
                session.save(user);
                session.save(user1);

                session.getTransaction().commit();
        }

}
