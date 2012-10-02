package za.ac.sun.cs.hons.minke.client.serialization.entities.store;


import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

/**
 * DB Entity used to store data about a specific store, e.g. Spar.
 * 
 * @author godfried
 * 
 */
@Subclass
public class Store extends IsEntity {
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
	public Store(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}

}
