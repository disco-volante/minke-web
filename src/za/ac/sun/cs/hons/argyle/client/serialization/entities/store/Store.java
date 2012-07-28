package za.ac.sun.cs.hons.argyle.client.serialization.entities.store;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

/**
 * DB Entity used to store data about a specific store, e.g. Spar.
 * 
 * @author godfried
 * 
 */
@Entity
public class Store extends IsEntity {
    @Id
    private Long   ID;
    private String name;

    public Store() {
    }

    /**
     * Creates a new Store object.
     * 
     * @param ID
     *            the object's ID.
     * @param name
     *            the object's name.
     */
    public Store(long ID, String name) {
	this.ID = ID;
	this.name = name;
    }

    public long getID() {
	return ID;
    }

    public String getName() {
	return name;
    }

    public String toString() {
	return name;
    }

}
