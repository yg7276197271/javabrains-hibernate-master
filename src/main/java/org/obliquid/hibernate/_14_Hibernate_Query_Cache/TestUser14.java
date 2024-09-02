package org.obliquid.hibernate._14_Hibernate_Query_Cache;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser14 {
	public  static void insertUser(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User14 user = new User14();
		user.setUserName("Yogesh Joshi Query Cache");
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
		Query query = session.createQuery("from User14 user where user.userId=1");
		query.setCacheable(true);//look for Query Cache if data is there.If not pull up data and
		//save it to Query cache
		
		List users = query.list();
		session.getTransaction().commit();
		session.close();
		
		Session session1 = sessionFactory.openSession();
		session1.beginTransaction();
		Query query1 = session1.createQuery("from User14 user where user.userId=1");
		query1.setCacheable(true); //look for Query Cache if data is there.If not pull up data and
		//save it to Query cache

		List users1 = query1.list();
		session1.getTransaction().commit();
		session1.close();
		
		//Here only 1 query will be fired provided we have configured Query Cache
		//Add annotation on POJO class , make config file changes (CacheProvide and use query cache to true)
		//And setCacheable to true
	}
}
