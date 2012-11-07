package za.ac.sun.cs.hons.minke.client.serialization.entities.store;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;

import com.googlecode.objectify.annotation.Subclass;

/**
 * DB Entity used to store data about a specific store's branch, e.g. Spar - Die
 * Boord.
 * 
 * @author godfried
 * 
 */
@Subclass
public class Branch extends IsEntity {
	private String name;
	@Embedded
	private Store store;
	private long storeID;
	@Embedded
	private CityLocation location;
	private long locationID;

	public Branch() {
	}

	public Branch(String name, Store store, CityLocation location) {
		super();
		setName(name);
		setStore(store);
		setLocation(location);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		if (store != null) {
			this.store = store;
			this.storeID = store.getID();
		}
	}

	public CityLocation getLocation() {
		return location;
	}

	public void setLocation(CityLocation location) {
		if (location != null) {
			this.location = location;
			this.locationID = location.getID();
		}
	}

	public long getStoreID() {
		return storeID;
	}

	public long getLocationID() {
		return locationID;
	}

	@Override
	public String toString() {
		if( store == null){
			return name;
		}
		return store.toString() + " @ " + name;
	}

	public void setCityLocationID(long locationID) {
		this.locationID = locationID;
	}

	public void setStoreID(long storeID) {
		this.storeID = storeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + (int) (locationID ^ (locationID >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		result = prime * result + (int) (storeID ^ (storeID >>> 32));
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
		Branch other = (Branch) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (locationID != other.locationID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		if (storeID != other.storeID){
			return false;
		}else if(getID() != other.getID()){
			return false;
		}
		return true;
	}

}
