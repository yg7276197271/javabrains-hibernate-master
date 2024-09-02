package org.obliquid.hibernate._07_one_to_many;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user7")
public class User7 {

        private int userId;
        private String userName;
        private Collection<Vehicle7> vehicleList = new ArrayList<Vehicle7>();
        
        @OneToMany
        @JoinTable(name="USER7_VEHICLE7" , joinColumns=@JoinColumn(name="USER_ID"),
        			inverseJoinColumns=@JoinColumn(name="VEHICLE_ID"))
		public Collection<Vehicle7> getVehicleList() {
			return vehicleList;
		}
		public void setVehicleList(Collection<Vehicle7> vehicleList) {
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

        /**
         * FetchType can be LAZY (default) or EAGER.
         * 
         * @return the listOfAddresses
         */
       
        @Override
        public String toString() {
                return "username: " + getUserName();
        }

}
