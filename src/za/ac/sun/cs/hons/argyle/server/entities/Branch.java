package za.ac.sun.cs.hons.argyle.server.entities;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

/**
 * DB Entity used to store data about a specific store's branch, e.g. Spar - Die
 * Boord.
 * 
 * @author godfried
 * 
 */
@Entity
public class Branch extends IsEntity {
    @Id
    private Long	  iD;
    private String	name;
    private Key<Store>    storeKey;
    private Key<Location> locationKey;

    public Branch() {
    }

    public Branch(Long iD, String name, Key<Store> storeKey,
	    Key<Location> locationKey) {
	super();
	this.iD = iD;
	this.name = name;
	this.storeKey = storeKey;
	this.locationKey = locationKey;
    }

    public long getID() {
	return iD;
    }

    public String getName() {
	return name;
    }

    public Key<Store> getStoreKey() {
	return storeKey;
    }

    public void setStoreKey(Key<Store> storeKey) {
	this.storeKey = storeKey;
    }

    public Key<Location> getLocationKey() {
	return locationKey;
    }

    public void setLocationKey(Key<Location> locationKey) {
	this.locationKey = locationKey;
    }

    public void setID(long iD) {
	this.iD = iD;
    }

    public void setName(String name) {
	this.name = name;
    }

}
