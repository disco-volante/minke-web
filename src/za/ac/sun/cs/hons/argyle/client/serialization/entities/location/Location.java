package za.ac.sun.cs.hons.argyle.client.serialization.entities.location;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Location extends IsEntity {
    @Id
    private Long      ID;
    @Embedded
    private City      city;
    @Embedded
    private GPSCoords coords;

    public Location() {
    }

    public Location(long ID, GPSCoords coords, City city) {
	this.ID = ID;
	this.coords = coords;
	this.city = city;
    }

    public long getID() {
	return ID;
    }

    public City getCity() {
	return city;
    }

    public GPSCoords getCoords() {
	return coords;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!obj.getClass().equals(Location.class)) {
	    return false;
	}
	Location loc = (Location) obj;
	return getCity().equals(loc.getCity())
		&& getCoords().equals(loc.getCoords())
		&& getID() == loc.getID();
    }

}
