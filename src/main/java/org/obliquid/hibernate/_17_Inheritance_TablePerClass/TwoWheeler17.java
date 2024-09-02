package org.obliquid.hibernate._17_Inheritance_TablePerClass;

import javax.persistence.Entity;

@Entity
public class TwoWheeler17 extends Vehicle17{

	private String steeringHandle;

	public String getSteeringHandle() {
		return steeringHandle;
	}

	public void setSteeringHandle(String steeringHandle) {
		this.steeringHandle = steeringHandle;
	}
	
	
}
