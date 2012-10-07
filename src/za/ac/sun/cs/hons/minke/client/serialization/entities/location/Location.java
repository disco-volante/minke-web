package za.ac.sun.cs.hons.minke.client.serialization.entities.location;


import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Location extends IsEntity {
	private double lat, lon;
	private String name;

	public Location() {
	};

	public Location(String name, double lat, double lon) {
		super();
		this.setName(name);
		this.setLat(lat);
		this.setLon(lon);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return getName();
	}

}
