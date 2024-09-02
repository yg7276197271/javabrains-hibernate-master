package org.obliquid.hibernate._17_Inheritance_TablePerClass;

import javax.persistence.Entity;

@Entity
public class FourWheeler17  extends Vehicle17{

	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
