package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import za.ac.sun.cs.hons.minke.client.serialization.GPSArea;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Country extends Location {

	public Country() {
	}

	public Country(String name, GPSArea coords) {
		super(name, coords);
	}

	@Override
	public String toString() {
		return getName();
	}

}
