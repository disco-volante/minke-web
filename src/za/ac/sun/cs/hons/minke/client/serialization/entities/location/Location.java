package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.GPSArea;
import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Location extends IsEntity {
	@Embedded
	private GPSArea coords;
	private String name;

	public Location() {
	};

	public Location(String name, GPSArea coords) {
		super();
		this.setName(name);
		this.setCoords(coords);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GPSArea getCoords() {
		return coords;
	}

	public void setCoords(GPSArea coords) {
		this.coords = coords;
	}

	@Override
	public String toString() {
		return getName();
	}

}
