package com.cs.shopper.client.entities.location;

import javax.persistence.Id;

import com.cs.shopper.client.entities.GWTSerializable;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class City extends GWTSerializable {
	@Id private Long ID;
	private String name;
	private String province;
	private String country;
	public City(){};
	public City(String name, String province, String country) {
		setName(name);
		setProvince(province);
		setCountry(country);
	}
	public City(String name, String province, String country,Long ID) {
		setID(ID);
		setName(name);
		setProvince(province);
		setCountry(country);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString(){
		return getName()+", "+getProvince()+", "+getCountry();
	}
	public static City toCity(String text) {
		String[] elements = text.split(",");
		if(elements.length==3) return new City(elements[0],elements[1],elements[2]);
		return null;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}

}
