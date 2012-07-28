package com.cs.shopper.client.entities.product;

import javax.persistence.Id;

import com.cs.shopper.client.entities.GWTSerializable;
import com.cs.shopper.client.entities.store.Branch;
import com.googlecode.objectify.annotation.Entity;
@Entity
public class ProductData extends GWTSerializable {
	@Id private Long ID;
	private String name;
	private Branch branch;
	private ProductCategory type;
	private double size;
	private String measurement;
	private double price;
	public ProductData(){}
	public ProductData(String name, Branch branch, ProductCategory type, double size, String measurement,double price){
		setName(name);
		setBranch(branch);
		setType(type);
		setSize(size);
		setMeasurement(measurement);
		setPrice(price);
	}
	public ProductData(String name, Branch branch, ProductCategory type, double size, String measurement,double price,Long ID){
		setName(name);
		setBranch(branch);
		setType(type);
		setSize(size);
		setMeasurement(measurement);
		setPrice(price);
		setID(ID);

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ProductCategory getType() {
		return type;
	}
	public void setType(ProductCategory type2) {
		this.type = type2;
	}
	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long ID) {
		this.ID = ID;
	}


}
