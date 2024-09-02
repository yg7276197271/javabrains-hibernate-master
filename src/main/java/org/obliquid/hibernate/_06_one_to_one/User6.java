package org.obliquid.hibernate._06_one_to_one;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user6")
public class User6 {

        private int userId;
        private String userName;
        
       
        private Vehicle6 vehicle6;
        
        
        @OneToOne
        @JoinColumn(name="VEHICLE_ID")
        public Vehicle6 getVehicle6() {
			return vehicle6;
		}
		public void setVehicle6(Vehicle6 vehicle6) {
			this.vehicle6 = vehicle6;
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
