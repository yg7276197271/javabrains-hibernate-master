package org.obliquid.hibernate._01_create_read;

import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

	private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
			.buildSessionFactory();

	public static void main(String[] args) {
		HibernateTest instance = new HibernateTest();
		instance.insertUser();
		//instance.loadUser();
		instance.getUser();

	}

	private void getUser() {
		System.out.println("Inside getUser method ");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//session.get() will return null if object is not found
		UserDetail user2 = (UserDetail) session.get(UserDetail.class, 10);
		System.out.println(user2);
		
		
		// session.get() is eager loading
		UserDetail user = (UserDetail) session.get(UserDetail.class, 1);
		System.out.println("UserDetail GET Method called");
		System.out.println("UserDetail ID = " + user.getUserId());
		System.out.println("Now getting properties of USER1");
		System.out.println(user.getUserName());
		System.out.println(user.getDescription());

		session.close();
		
		System.out.println("  ");

	}

	private void loadUser() {
		
		System.out.println("Inside loadUser method ");

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		//session.load() will throw ObjectNotFound Exception  if entry is not present in DB

		/*UserDetail user2 = (UserDetail) session.load(UserDetail.class, 10);
		System.out.println(user2);*/
		
		// session.load is lazy loading
		UserDetail user1 = (UserDetail) session.load(UserDetail.class, 1);
		System.out.println("UserDetail load called");
		System.out.println("UserDetail ID= " + user1.getUserId());//No select query is fired here
		System.out.println("Now getting properties of USER1");
		System.out.println(user1.getUserName());
		System.out.println(user1.getDescription());
		session.close();
		System.out.println("  ");

	}

	private void insertUser() {
		UserDetail user = new UserDetail();
		user.setUserName("First User");
		user.setAddress("First User's address");
		String description = "First description that is going to be very long since we would like "
				+ "to have a description that is longer than 255 characters. How can we "
				+ "do that? Should I keep on writing nonsense? Or is it enough already? "
				+ "I think it isn't yet... we have to keep on adding things and useless "
				+ "words until the limit is reached.";
		user.setDescription(description);
		user.setJoinedDate(Date.valueOf("2011-10-01"));

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);

		UserDetail user2 = new UserDetail();
		user2.setUserName("Second user");
		session.save(user2);

		session.getTransaction().commit();
		session.close();
	}

}
