package org.obliquid.hibernate._16_Inheritance_SingleTable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.FetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.obliquid.hibernate._02_value_object.Address;

public class TestUser16 {

        private static SessionFactory sessionFactory = new Configuration().configure("postgresql.cfg.xml")
                        .buildSessionFactory();

        public static void main(String[] args) {
                TestUser16 instance = new TestUser16();
                instance.insertUser();
        }

        private void insertUser() {
            
                Vehicle16 vehicle=new Vehicle16();
                vehicle.setVehicleName("Tiago");

                TwoWheeler16 bike = new TwoWheeler16();
                bike.setVehicleName("FZS Bike");
                bike.setSteeringHandle("Bike Steering Hanlde");
                
                FourWheeler16 car=new FourWheeler16();
                car.setVehicleName("Porsche");
                car.setSteeringWheel("Porsche Steering wheel");
                
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                session.save(vehicle);
                session.save(bike);
                session.save(car);

               //On Vehicle Class we have added @Inheritance to specify the strategy as Single Table
               //Also we have added @DiscriminatorColumn to have column name as VEHICLE_TYPE
              //as differentiating column name
               //Also on twowheeler & fourwheeler class we have added @DiscriminatorValue
               //to mention the corresponding value in Vehcile_type column
                
                session.getTransaction().commit();
                session.close();
                session = sessionFactory.openSession();
             
               
                
                
               
        }

}
