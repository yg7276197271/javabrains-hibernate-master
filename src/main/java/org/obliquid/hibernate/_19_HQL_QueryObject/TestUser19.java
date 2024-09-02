package org.obliquid.hibernate._19_HQL_QueryObject;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser19 {

	private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
			.buildSessionFactory();

	public static void insertUsers() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// Create 10 user objects using below query

		for (int i = 0; i < 10; i++) {
			User19 user = new User19();
			user.setUserName("User" + i);
			session.save(user);
		}

		session.getTransaction().commit();
		session.close();
	}

	public static void fetchUsersWithWhereClause() {
		System.out.println("fetchUsersWithWhereClause  ");
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = (Query) session.createQuery("from User19 where userId > 5");
		// Class name User19 is used & instance variable name userId instead of
		// Table name & Column Name in HQL
		List<User19> users = (List<User19>) query.list();
		session.getTransaction().commit();
		session.close();
		System.out.println("User List Size "+users.size());
	}
	public static void fetchPaginatedList(){
		System.out.println("fetchPaginatedList ");
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = (Query) session.createQuery("from User19");
		query.setFirstResult(5);//Starting result //start from 0 so its 6th row
		query.setMaxResults(4);//count -> no of rows
		List<User19> users = (List<User19>) query.list();
		session.getTransaction().commit();
		session.close();
		for(User19 user:users){
			System.out.println(user.getUserName());
		}
	}
	public static void fetchWithSelect(){
		System.out.println("fetchWithSelect ");
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = (Query) session.createQuery("select userName from User19");
		List<String> userNames = (List<String>) query.list();
		session.getTransaction().commit();
		session.close();
		for(String userName:userNames){
			System.out.println(userName);
		}
	}
	public static void main(String[] args) {

		insertUsers();
		//fetchUsersWithWhereClause();
		//fetchPaginatedList();
		//fetchWithSelect();
		//queryWithParamter_position();
		//queryWithParamter_name();
		//namedQuery();
		//namedNativeQuery();
	}

	
	//Parameterized queries are used to avoid SQL injection attack
	public static void queryWithParamter_position() {
		System.out.println("queryWithParamter_position");
		String userIdInput="2";
		String useNameInput="User9";

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = (Query) session.createQuery("select userName from User19 where userId > ? and userName = ?");
		query.setInteger(0, Integer.parseInt(userIdInput));
		query.setString(1, useNameInput);

		List<String> userNames = (List<String>) query.list();
		session.getTransaction().commit();
		session.close();
		for(String userName:userNames){
			System.out.println(userName);
		}
	}
	//Named Query is HQL query written on User19 class with @NamedQuery annotation
		public static void namedQuery() {
			System.out.println("namedQuery");
			String userIdInput="2";

			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = session.getNamedQuery("User19.byId");
			query.setInteger(0, Integer.parseInt(userIdInput));

			List<User19> userList = (List<User19>) query.list();
			session.getTransaction().commit();
			session.close();
			for(User19 user:userList){
				System.out.println(user.getUserName());
			}
		
	}
		//Parameterized queries are used to avoid SQL injection attack
		public static void queryWithParamter_name() {
			System.out.println("queryWithParamter_name");
			String userIdInput="2";
			String useNameInput="User9";

			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = (Query) session.createQuery("select userName from User19 where "
					+ "userId > :userIdInput and userName = :useNameInput");
			query.setInteger("userIdInput", Integer.parseInt(userIdInput));
			query.setString("useNameInput", useNameInput);

			List<String> userNames = (List<String>) query.list();
			session.getTransaction().commit();
			session.close();
			for(String userName:userNames){
				System.out.println(userName);
			}
		
	}
		//namedNativeQuery Query is SQL query written on User19 class with @NamedNativeQuery annotation

		public static void namedNativeQuery() {

			System.out.println("namedNativeQuery");
			String useNameInput="User9";

			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Query query = (Query) session.getNamedQuery("User19.byName");
			query.setString(0, useNameInput);

			List<User19> userList = (List<User19>) query.list();
			session.getTransaction().commit();
			session.close();
			for(User19 user:userList){
				System.out.println(user.getUserName());
			}
			
		}

}
