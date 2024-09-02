package org.obliquid.hibernate._18_Inheritance_Joined;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestUser18 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser18 instance = new TestUser18();
                instance.insertUser();
        }

        private void insertUser() {
            
                Vehicle18 vehicle=new Vehicle18();
                vehicle.setVehicleName("Tiago");

                TwoWheeler18 bike = new TwoWheeler18();
                bike.setVehicleName("Bike");
                bike.setSteeringHandle("Bike Steering Hanlde");
                
                FourWheeler18 car=new FourWheeler18();
                car.setVehicleName("Porsche");
                car.setSteeringWheel("Porsche Steering wheel");
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(vehicle);
                session.save(bike);
                session.save(car);

               //On Vehicle Class we have added @Inheritance to specify the strategy as JOINED
                //There will be three tables created Vehicle ,TwoWheeler & FourWheeler
                //In TwoWheeler & FourWheeler tables ,columns specific to the child classes are present
                //along with Vehicle Id column for joining with parent class
                //In Vehicle Table we have column specific to parent class that is vehicle name & Id
                
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
               
                
                
               
        }

}
