package za.ac.sun.cs.hons.argyle.client.serialization.entities.location;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Location extends IsEntity {
    @Id
    private Long      id;
    @Embedded
    private City      city;
    private long      cityID;
    @Embedded
    private GPSCoords coords;

    public Location() {
    }

    public Location(City city, GPSCoords coords) {
	super();
	setCity(city);
	setCoords(coords);
    }

    public long getID() {
	return id;
    }

    public void setID(long id) {
	this.id = id;
    }

    public City getCity() {
	return city;
    }

    public void setCity(City city) {
	this.city = city;
	this.cityID = city.getID();

    }

    public long getCityID() {
	return cityID;
    }

    public void setCoords(GPSCoords coords) {
	this.coords = coords;
    }

    public GPSCoords getCoords() {
	return coords;
    }

    @Override
    public String toString() {
	return city.getName();
    }
}
