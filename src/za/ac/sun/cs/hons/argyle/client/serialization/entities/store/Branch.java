package za.ac.sun.cs.hons.argyle.client.serialization.entities.store;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;

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
    private Long     iD;
    private String   name;
    @Embedded
    private Store    store;
    private long     storeID;
    @Embedded
    private Location location;
    private long     locationID;

    public Branch() {
    }

    public Branch(String name, Store store, Location location) {
	super();
	setName(name);
	setStore(store);
	setLocation(location);
    }

    public long getID() {
	return iD;
    }

    public String getName() {
	return name;
    }

    public void setID(long iD) {
	this.iD = iD;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Store getStore() {
	return store;
    }

    public void setStore(Store store) {
	this.store = store;
	this.storeID = store.getID();
    }

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
	this.locationID = location.getID();
    }

    public long getStoreID() {
	return storeID;
    }

    public long getLocationID() {
	return locationID;
    }

    @Override
    public String toString() {
	return store.toString() + " @ " + name + " (" + location.toString()
		+ ")";
    }

}
