package org.obliquid.hibernate._15_Inheritance;

import javax.persistence.Entity;

@Entity
public class FourWheeler  extends Vehicle15{

	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
