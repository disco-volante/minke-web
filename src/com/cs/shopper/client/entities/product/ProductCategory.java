package com.cs.shopper.client.entities.product;

import javax.persistence.Id;

import com.cs.shopper.client.entities.GWTSerializable;
import com.googlecode.objectify.annotation.Entity;


@Entity
public class ProductCategory extends GWTSerializable {
	@Id private Long ID;
	private String type;
	private String secondary;
	private String main;
	public ProductCategory(){};
	public ProductCategory(String type, String secondary,String main){
		setType(type);
		setSecondary(secondary);
		setMain(main);
	}
	public ProductCategory(String type, String secondary,String main, Long ID){
		setID(ID);
		setType(type);
		setSecondary(secondary);
		setMain(main);
		setID(ID);
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSecondary() {
		return secondary;
	}
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	@Override
	public String toString(){
		return type+", "+secondary+", "+main;
		
	}
	public static ProductCategory toProductCategory(String text) {
		String[] elements = text.split(",");
		if(elements.length==3) return new ProductCategory(elements[0],elements[1],elements[2]);
		return null;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}

}
