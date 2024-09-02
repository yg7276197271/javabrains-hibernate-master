package org.obliquid.hibernate._12_Transient_Persistent_Detached;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class User12 {

        private int userId;
        private String userName;
      
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
