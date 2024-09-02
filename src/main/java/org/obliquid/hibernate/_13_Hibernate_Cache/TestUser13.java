package org.obliquid.hibernate._13_Hibernate_Cache;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser13 {
	public  static void insertUser(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User13 user = new User13();
		user.setUserName("Yogesh Joshi");
		session.save(user);
		session.getTransaction().commit();
		session.close();
	}
	private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
			.buildSessionFactory();

	public static void main(String[] args) {
		System.out.println("Creating some data as pre requisite");
		insertUser();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User13 user = (User13) session.get(User13.class, 1);
		user.setUserName("New");
		
		User13 userNew = (User13) session.get(User13.class, 1);
		 User13 userNew1 = (User13) session.get(User13.class, 1);
		 System.out.println(userNew1.getUserName());
		 
		 //Here we are firing session.get 3 times but only one select query is fired
		 //As hibernate uses first level cache i.e session cache to store user object
		 //Also we have updated username in between and we get the updated username in result
		session.getTransaction().commit();
		session.close();
		
		System.out.println("Getting the User Object in new session");
		
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		
		user = (User13) session1.get(User13.class, 1);
		//Once the previous session is closed then cache is removed.
		//So in new session again one select query is fired/.
		//But If we configure the second level cache using Echcache Provider then this query will not be fired
		//So Hibernate first looks in 1st level cache that is session then 2nd level cache.
		//As object found is second level cache it will be returned
		//Second level cache is across all sessions, across application , across clusters
		session1.getTransaction().commit();
		session1.close();
	}

}
