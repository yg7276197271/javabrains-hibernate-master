package org.obliquid.hibernate._15_Inheritance;

import javax.persistence.Entity;

@Entity
public class TwoWheeler extends Vehicle15{

	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
	
	
}
