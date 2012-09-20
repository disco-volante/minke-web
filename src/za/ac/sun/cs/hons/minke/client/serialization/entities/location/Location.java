package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Location extends IsEntity {
	@Embedded
	private GPSCoords coords;
	private String name;

	public Location() {
	};

	public Location(String name, GPSCoords coords) {
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

	public GPSCoords getCoords() {
		return coords;
	}

	public void setCoords(GPSCoords coords) {
		this.coords = coords;
	}

	@Override
	public String toString() {
		return getName();
	}

}
