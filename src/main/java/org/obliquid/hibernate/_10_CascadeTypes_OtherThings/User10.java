package org.obliquid.hibernate._10_CascadeTypes_OtherThings;

import java.util.ArrayList;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
public class User10 {

        private int userId;
        private String userName;
       
        private Collection<Vehicle10> vehicleList = new ArrayList<Vehicle10>();
        
        @OneToMany(orphanRemoval = true ,cascade=CascadeType.ALL)

        //@OneToMany(cascade=CascadeType.ALL)
        //@OneToMany(cascade=CascadeType.PERSIST)
		public Collection<Vehicle10> getVehicleList() {
			return vehicleList;
		}
		public void setVehicleList(Collection<Vehicle10> vehicleList) {
			this.vehicleList = vehicleList;
		}
		@Id
        @GeneratedValue
        public int getUserId() {
                return userId;
        }
        public void setUserId(int userId) {
                this.userId = userId;
        }
      
        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

     
       
        @Override
        public String toString() {
                return "username: " + getUserName();
        }

}
