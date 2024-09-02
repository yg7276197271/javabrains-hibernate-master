package org.obliquid.hibernate._21_Query_by_Example;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.obliquid.hibernate._19_HQL_QueryObject.User19;

public class TestUser21 {

	private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
			.buildSessionFactory();

	public static void insertUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// Create 10 user objects using below query

		for (int i = 1; i <= 10; i++) {
			User21 user = new User21();
			user.setUserName("User" + i);
			session.save(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	public static void fetchUserUsingQueryByExample() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		User21 exampleUser = new User21();
		//exampleUser.setUserId(5); //We will get all records as Hibernate ignores Primary Key while 
		//doing query by Example
		exampleUser.setUserName("User5");
		Example example = Example.create(exampleUser);

		 //Example example=Example.create(exampleUser).excludeProperty("userName");
		// here username property is excluded so will get all data

		Criteria criteria = session.createCriteria(User21.class);
		criteria.add(example);

		List<User21> userList = (List<User21>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User21 user : userList) {
			System.out.println(user.getUserName());
		}

	}

	public static void fetchUserUsingQueryByExample1() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		User21 exampleUser = new User21();
		exampleUser.setUserName("User1%");
		Example example = Example.create(exampleUser).enableLike();
		Criteria criteria = session.createCriteria(User21.class);
		criteria.add(example);
		List<User21> userList = (List<User21>) criteria.list();

		session.getTransaction().commit();
		session.close();
		for (User21 user : userList) {
			System.out.println(user.getUserName());
		}

	}

	public static void main(String[] args) {

		insertUsers();
		fetchUserUsingQueryByExample();
		//fetchUserUsingQueryByExample1();
	}

}
