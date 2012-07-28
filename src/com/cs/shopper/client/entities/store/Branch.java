package com.cs.shopper.client.entities.store;

import javax.persistence.Id;

import com.cs.shopper.client.entities.GWTSerializable;
import com.cs.shopper.client.entities.location.Location;

public class Branch extends GWTSerializable {
	@Id private Long ID;
	private Store store; 
	private Location location;
	public Branch(){}
	public Branch( Store store, Location location){
		setStore(store);
		setLocation(location);
	}
	public Branch( Store store, Location location, Long ID){
		setID(ID);
		setStore(store);
		setLocation(location);
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location2) {
		this.location = location2;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store2) {
		this.store = store2;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	

}
