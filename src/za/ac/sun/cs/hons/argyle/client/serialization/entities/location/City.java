package za.ac.sun.cs.hons.argyle.client.serialization.entities.location;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class City extends IsEntity {
    @Id
    private Long      id;
    private String    name;
    private String    province;
    private String    country;
    @Embedded
    private GPSCoords coords;

    public City() {
    }

    public City(String name, String province, String country,
	    GPSCoords coords) {
	super();
	this.name = name;
	this.province = province;
	this.country = country;
	this.coords = coords;
    }

    public void setID(long id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setProvince(String province) {
	this.province = province;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    public long getID() {
	return id;
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

    public void setCoords(GPSCoords coords) {
	this.coords = coords;
    }

    @Override
    public String toString() {
	return getName() + ", " + getProvince() + ", " + getCountry();
    }

}
