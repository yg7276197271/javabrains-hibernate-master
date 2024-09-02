package org.obliquid.hibernate._15_Inheritance;

import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser15 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser15 instance = new TestUser15();
                instance.insertUser();
        }

        private void insertUser() {
            
                Vehicle15 vehicle=new Vehicle15();
                vehicle.setVehicleName("Tiago");

                TwoWheeler bike = new TwoWheeler();
                bike.setVehicleName("Bike");
                bike.setSteeringHandle("Bike Steering Hanlde");
                
                FourWheeler car=new FourWheeler();
                car.setVehicleName("Porsche");
                car.setSteeringWheel("Porsche Steering wheel");
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(vehicle);
                session.save(bike);
                session.save(car);

                //By Default Single Table  Strategy is implemented by hibernate
                //We have only one table Vehicle 
                //Steering Handle & steering wheel are columns in the same table
                //Dtype is an additional column added by Hibernate to
                //Differentiate between TwoWheeler & FourWheeler
                
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
               
                
                
               
        }

}
