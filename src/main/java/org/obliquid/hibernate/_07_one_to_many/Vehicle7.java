package org.obliquid.hibernate._07_one_to_many;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle7 {
	@Id
	@GeneratedValue
	private int vehicleId;
	private String vehicleName;
	
	@ManyToOne 
	//If we add this then it will be Bidirectional relationship between user and vehicle
	//In Vehicle Table we will have UserId also.
	//We have to do setUser in Test class for all vehicles
	private User7 user7;

	public User7 getUser7() {
		return user7;
	}
	public void setUser7(User7 user7) {
		this.user7 = user7;
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
