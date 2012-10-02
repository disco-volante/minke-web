package za.ac.sun.cs.hons.minke.client.serialization.entities.location;


import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Location extends IsEntity {
	private int lat, lon;
	private String name;

	public Location() {
	};

	public Location(String name, int lat, int lon) {
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

	
	public int getLon() {
		return lon;
	}

	public void setLon(int lon) {
		this.lon = lon;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return getName();
	}

}
