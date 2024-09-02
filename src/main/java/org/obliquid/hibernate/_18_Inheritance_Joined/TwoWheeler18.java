package org.obliquid.hibernate._18_Inheritance_Joined;

import javax.persistence.Entity;

@Entity
public class TwoWheeler18 extends Vehicle18{

	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
	
	
}
