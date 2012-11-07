package za.ac.sun.cs.hons.minke.client.serialization.entities.product;


import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

/**
 * DB Entity used to store data about a {@link Product}'s categories, e.g. Milk,
 * Dairy, Consumables.
 * 
 * @author godfried
 * 
 */
@Subclass
public class Category extends IsEntity {
	private String name;

	public Category() {
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
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
