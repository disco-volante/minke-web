package com.cs.shopper.client.entities.store;

import com.cs.shopper.client.entities.GWTSerializable;

public class Store extends GWTSerializable {
	private String name;
	public Store(){};
	public Store(String name){
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
