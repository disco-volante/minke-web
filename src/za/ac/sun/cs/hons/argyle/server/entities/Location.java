package za.ac.sun.cs.hons.argyle.server.entities;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class Location extends IsEntity {
    @Id
    private Long      id;
    private Key<City> cityKey;
    @Embedded
    private GPSCoords coords;

    public Location() {
    }

    public Location(Long id, Key<City> cityKey, GPSCoords coords) {
	super();
	this.id = id;
	this.cityKey = cityKey;
	this.coords = coords;
    }

    public long getID() {
	return id;
    }

    public void setID(long id) {
	this.id = id;
    }

    public void setCoords(GPSCoords coords) {
	this.coords = coords;
    }

    public Key<City> getCityKey() {
	return cityKey;
    }

    public void setCityKey(Key<City> cityKey) {
	this.cityKey = cityKey;
    }

    public GPSCoords getCoords() {
	return coords;
    }

}
