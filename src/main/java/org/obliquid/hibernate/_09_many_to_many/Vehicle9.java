package org.obliquid.hibernate._09_many_to_many;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle9 {
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
	@ManyToMany(mappedBy="vehicleList")
	//If we dont add mapped By then total 4 tables will get created.1 user 1 vehicle
	//and two tables to maintain joining relationship
	//We can add mappedBy in User9 class also.in that case will remove mappedby from here
	private Collection<User9> userList = new ArrayList<User9>();
	
	public Collection<User9> getUserList() {
		return userList;
	}
	public void setUserList(Collection<User9> userList) {
		this.userList = userList;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	
	
}
