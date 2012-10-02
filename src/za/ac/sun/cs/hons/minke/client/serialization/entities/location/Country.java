package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class Country extends Location {

	public Country() {
	}

	public Country(String name) {
		super(name, 0, 0);
	}

	@Override
	public String toString() {
		return getName();
	}

}
