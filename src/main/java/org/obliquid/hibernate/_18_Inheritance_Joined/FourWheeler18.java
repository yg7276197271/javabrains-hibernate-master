package org.obliquid.hibernate._18_Inheritance_Joined;

import javax.persistence.Entity;

@Entity
public class FourWheeler18  extends Vehicle18{

	private String steeringWheel;

	public String getSteeringWheel() {
		return steeringWheel;
	}

	public void setSteeringWheel(String steeringWheel) {
		this.steeringWheel = steeringWheel;
	}
	
}
