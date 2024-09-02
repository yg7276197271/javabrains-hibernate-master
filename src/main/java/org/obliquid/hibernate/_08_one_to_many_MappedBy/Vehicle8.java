package org.obliquid.hibernate._08_one_to_many_MappedBy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Vehicle8 {
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
	@NotFound(action=NotFoundAction.IGNORE)
	@ManyToOne
	@JoinColumn(name="USER_ID")
	//If we add join column then this column will get created in Vehicle table &
	//There will not be any third table for maintaining user id & vehicle id as it is mapped by user8 
	private User8 user8;

	
	public User8 getUser8() {
		return user8;
	}
	public void setUser8(User8 user8) {
		this.user8 = user8;
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
