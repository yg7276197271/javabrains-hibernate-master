package org.obliquid.hibernate._08_one_to_many_MappedBy;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user8")
public class User8 {

        private int userId;
        private String userName;
        private Collection<Vehicle8> vehicleList = new ArrayList<Vehicle8>();

        @OneToMany(mappedBy="user8") 
        //This refers to user8 object in vehicle class
        //We have user8 object in Vehicle8 class
        //so as we have added mappedBy, there will not be any join table created
		public Collection<Vehicle8> getVehicleList() {
			return vehicleList;
		}
		public void setVehicleList(Collection<Vehicle8> vehicleList) {
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
