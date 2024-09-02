package org.obliquid.hibernate._09_many_to_many;

import java.util.ArrayList;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class User9 {

        private int userId;
        private String userName;
        private Collection<Vehicle9> vehicleList = new ArrayList<Vehicle9>();

        //We should add JoinTable & join columns in one class and in other class mention mapped by
        @ManyToMany
        @JoinTable(name="USER9_VEHICLE9_ManyToMany" , joinColumns=@JoinColumn(name="USER_ID"),
		inverseJoinColumns=@JoinColumn(name="VEHICLE_ID"))
		public Collection<Vehicle9> getVehicleList() {
			return vehicleList;
		}
		public void setVehicleList(Collection<Vehicle9> vehicleList) {
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
