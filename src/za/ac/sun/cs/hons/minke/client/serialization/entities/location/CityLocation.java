package za.ac.sun.cs.hons.minke.client.serialization.entities.location;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.GPSArea;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CityLocation extends Location {
	@Embedded
	private City city;
	private long cityID;

	public CityLocation() {
	}

	public CityLocation(String name, City city, GPSArea coords) {
		super(name, coords);
		setCity(city);
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		if (city != null) {
			this.city = city;
			this.cityID = city.getID();
		}

	}

	public long getCityID() {
		return cityID;
	}

	@Override
	public String toString() {
		return getName() + ", " + getCity().toString();
	}

}
