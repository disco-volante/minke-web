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

	public void setCityLocationID(long long1) {
		// TODO Auto-generated method stub

	}

	public void setStoreID(long storeID) {
		this.storeID = storeID;
	}

}
