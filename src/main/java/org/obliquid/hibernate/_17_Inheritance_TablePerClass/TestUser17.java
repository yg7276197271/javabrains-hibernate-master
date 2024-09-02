package org.obliquid.hibernate._17_Inheritance_TablePerClass;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser17 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser17 instance = new TestUser17();
                instance.insertUser();
        }

        private void insertUser() {
            
                Vehicle17 vehicle=new Vehicle17();
                vehicle.setVehicleName("Tiago");

                TwoWheeler17 bike = new TwoWheeler17();
                bike.setVehicleName("Bike FZS");
                bike.setSteeringHandle("Bike Steering Hanlde");
                
                FourWheeler17 car=new FourWheeler17();
                car.setVehicleName("Porsche");
                car.setSteeringWheel("Porsche Steering wheel");
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(vehicle);
                session.save(bike);
                session.save(car);

               //On Vehicle Class we have added @Inheritance to specify the strategy as TABLE_PER_CLASS
                //There will be three tables created Vehicle ,TwoWheeler & FourWheeler
                //In TwoWheeler & FourWheeler tables , vehicle Id and Vehicle Name are added Automatically
                //And Vehicle Table contains Vehicle id and vehicle name
                
                //Generator Type IDENTITY is not supported .
                //@GeneratedValue(strategy=GenerationType.TABLE is used in vehicle class.
                
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
               
                
                
               
        }

}
