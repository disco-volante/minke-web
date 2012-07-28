package com.cs.shopper.client.entities.location;

import javax.persistence.Id;

import com.cs.shopper.client.entities.GWTSerializable;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class GPSCoords extends GWTSerializable {
	@Id private Long ID;
	private double latitude, longitude;
	public GPSCoords(){super();};
	public GPSCoords(double latitude, double longitude){
		super();
		setLatitude(latitude);
		setLongitude(longitude);
	}
	public GPSCoords(double latitude, double longitude,Long ID){
		setID(ID);
		setLatitude(latitude);
		setLongitude(longitude);
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}


}
