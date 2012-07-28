package za.ac.sun.cs.hons.argyle.client.serialization.entities.location;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class City extends IsEntity {
    @Id
    private Long      ID;
    private String    name;
    private String    province;
    private String    country;
    @Embedded
    private GPSCoords coords;

    public City() {
    }

    public City(long ID, GPSCoords coords, String name, String province,
	    String country) {
	this.ID = ID;
	this.name = name;
	this.province = province;
	this.country = country;
	this.coords = coords;
    }

    public long getID() {
	return ID;
    }

    public String getName() {
	return name;
    }

    public String getProvince() {
	return province;
    }

    public String getCountry() {
	return country;
    }

    public GPSCoords getCoords() {
	return coords;
    }

    @Override
    public String toString() {
	return getName() + ", " + getProvince() + ", " + getCountry();
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!obj.getClass().equals(City.class)) {
	    return false;
	}
	City city = (City) obj;
	return getCountry().equals(city.getCountry())
		&& getProvince().equals(city.getProvince())
		&& getName().equals(city.getName())
		&& getCoords().equals(city.getCoords())
		&& getID() == city.getID();
    }

}
