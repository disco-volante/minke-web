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
    private Long     ID;
    @Embedded
    private Store    store;
    @Embedded
    private Location location;
    private String   name;

    public Branch() {
    }

    public Branch(long ID, String name, Store store, Location location) {
	this.ID = ID;
	this.name = name;
	this.store = store;
	this.location = location;
    }

    public long getID() {
	return ID;
    }

    public String getName() {
	return name;
    }

    public Location getLocation() {
	return location;
    }

    public Store getStore() {
	return store;
    }

    public String toString() {
	return name + " " + store;
    }

}
