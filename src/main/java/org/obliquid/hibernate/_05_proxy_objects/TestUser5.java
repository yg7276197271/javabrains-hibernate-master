package org.obliquid.hibernate._05_proxy_objects;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser5 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser5 instance = new TestUser5();
                instance.insertUser();
        }

        private void insertUser() {
                User5 user = new User5();
                user.setUserName("stivlo");

                Address addr1 = new Address();
                addr1.setStreet("Street Name");
                addr1.setCity("City Name");
                addr1.setPostCode("postcode");

                Address addr2 = new Address();
                addr2.setStreet("Street Name 2");
                addr2.setCity("City Name 2");
                addr2.setPostCode("postcode 2");

                user.getListOfAddresses().add(addr1);
                user.getListOfAddresses().add(addr2);

                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
                session.close();

                user = null; //to show that we are retrieving it
                session = sessionFactory.openSession();
                //we are getting the user object without the addresses
                user = (User5) session.get(User5.class, 1);
                session.close();
                System.out.println(user);
                
                
                
                //If we close the session then ListOfAddresses will not get fetched
                //Reason is we have used lazy loading here.
                //So address list will get fetch only when we call user.getListOfAddresses() not before that.
                //Hibernate Creates a proxy object for every class object 
                //And when session is closed and then Proxy object cannot  fetch the list of addresses 
                //so we got org.hibernate.LazyInitializationException
                //We can make fetch = FetchType.EAGER to solve the issue
                
                System.out.println(user.getListOfAddresses());
                //If fetch type is lazy and session is not closed , then the moment
                //we call the getter then we can see the query in the log
                //If fetch type is eager then we will see all data is fetched at the time when
                //we have done seesion.get(User)
                if(session.isOpen()){
                	 session.close();
                }
               
        }

}
