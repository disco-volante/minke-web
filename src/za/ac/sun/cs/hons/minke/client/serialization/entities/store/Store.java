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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name)){
			return false;
		}else if(getID() != other.getID()){
			return false;
		}
		return true;
	}

}
