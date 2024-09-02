package org.obliquid.hibernate._20_Criteria_API;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.obliquid.hibernate._19_HQL_QueryObject.User19;

public class TestUser20 {
	//HQL is to perform both select and non-select operations on the data, 
	//but Criteria is only for selecting the data, we cannot perform non-select operations using criteria
	//HQL is suitable for executing Static Queries, 
	//where as Criteria is suitable for executing Dynamic Queries
	//Criteria used to take more time to execute then HQL
	//With Criteria we are safe with SQL Injection because of its dynamic query generation 
	//but in HQL as your queries are either fixed or parameterized, there is no safe from SQL Injection.
	//JPA does not have Criteria Query
	private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
			.buildSessionFactory();

	public static void insertUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// Create 10 user objects using below query

		for (int i = 0; i < 10; i++) {
			User20 user = new User20();
			user.setUserName("User" + i);
			session.save(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	public static void fetchUserUsingCriteriaAPI() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class);
		criteria.add(Restrictions.eq("userName", "User7"));

		List<User20> userList = (List<User20>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User20 user : userList) {
			System.out.println(user.getUserName());
		}

	}

	public static void main(String[] args) {

		insertUsers();
		 //fetchUserUsingCriteriaAPI();
		 //fetchUserUsingCriteriaAPI_Restrictions();
		//fetchUserUsingCriteriaAPI_Restrictions1();
		 //fetchUserUsingCriteriaAPI_Restrictions();
		 //fetchUserUsingCriteriaAPI_Restrictions2();
		 //fetchUserUsingCriteriaAPI_Projections1();
		 //fetchUserUsingCriteriaAPI_Projections2();
		fetchUserUsingCriteriaAPI_Order1();

	}

	public static void fetchUserUsingCriteriaAPI_Order1() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class).addOrder(Order.desc("userId"));

		List<User20> userList = (List<User20>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User20 user : userList) {
			System.out.println(user.getUserName());
		}

	}

	public static void fetchUserUsingCriteriaAPI_Projections1() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class).setProjection(Projections.property("userId"));

		List<Integer> userList = (List<Integer>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (Integer user : userList) {
			System.out.println(user);
		}

	}

	public static void fetchUserUsingCriteriaAPI_Projections2() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class).setProjection(Projections.max("userId"));
		// .setProjection(Projections.count("userId"));

		List<Integer> userList = (List<Integer>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (Integer user : userList) {
			System.out.println(user);
		}

	}

	private static void fetchUserUsingCriteriaAPI_Restrictions2() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class);
		criteria.add(Restrictions.or(Restrictions.between("userId", 1, 3), Restrictions.between("userId", 7, 50)));

		List<User20> userList = (List<User20>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User20 user : userList) {
			System.out.println(user.getUserName());
		}

	}

	private static void fetchUserUsingCriteriaAPI_Restrictions1() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class);
		criteria.add(Restrictions.like("userName", "%user%"));
		criteria.add(Restrictions.between("userId", 5, 7));

		List<User20> userList = (List<User20>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User20 user : userList) {
			System.out.println(user.getUserName());
		}

	}

	private static void fetchUserUsingCriteriaAPI_Restrictions() {

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(User20.class);
		criteria.add(Restrictions.gt("userId", 5));

		List<User20> userList = (List<User20>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User20 user : userList) {
			System.out.println(user.getUserName());
		}

	}

}
