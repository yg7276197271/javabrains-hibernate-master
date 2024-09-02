package org.obliquid.hibernate._11_CRUD_Operations;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User11 {

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
