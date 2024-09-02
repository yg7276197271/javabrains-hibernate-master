package org.obliquid.hibernate._19_HQL_QueryObject;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@NamedQuery(name="User19.byId",query="from User19 where userId = ?")
@NamedNativeQuery(name="User19.byName",query="select * from USER19 where USER_NAME = ?" ,resultClass=User19.class)
public class User19 {

        private int userId;
        private String userName;
		
		@Id
		@Column(name="USER_ID")
        @GeneratedValue
        public int getUserId() {
                return userId;
        }
        public void setUserId(int userId) {
                this.userId = userId;
        }
        @Column(name="USER_NAME")
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
