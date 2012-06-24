package com.cs.shopper.client.entities.location;

import com.cs.shopper.client.entities.GWTSerializable;


public class Location extends GWTSerializable {
	private String locID;
	private City city;
	private GPSCoords coords;
	public Location(){};
	public Location(String ID,City city,GPSCoords coords){
		setLocID(ID);
		setCity(city);
		setCoords(coords);
	}
	public String getLocID() {
		return locID;
	}
	public void setLocID(String locID) {
		this.locID = locID;
	}
	public String getCountry() {
		return city.getCountry();
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public GPSCoords getCoords() {
		return coords;
	}
	public void setCoords(GPSCoords coords) {
		this.coords = coords;
	}

}
