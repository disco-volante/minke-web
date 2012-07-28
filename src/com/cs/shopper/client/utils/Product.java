package com.cs.shopper.client.utils;
import java.text.DecimalFormat;

import com.cs.shopper.client.entities.product.ProductData;
import com.extjs.gxt.ui.client.data.BaseModel;

public class Product extends BaseModel {
	private static final long serialVersionUID = 1L;
	public Product() {
	}
	public Product(ProductData data) {
		set("name",data.getName());
		set("type", data.getType());
		set("branch", data.getBranch().getID());
		//set("store", data.getBranch());
		setSize("size", data.getSize());
		set("measurement",data.getMeasurement());
		setPrice("price", data.getPrice());
	}
	public void setPrice(String name,Double value){
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		value = Double.valueOf(twoDForm.format(value));
		set(name,value);
		
	}
	public void setSize(String name,Double value){
		DecimalFormat threeDForm = new DecimalFormat("#.###");
		value = Double.valueOf(threeDForm.format(value));
		set(name,value);
		
	}
	public String getType() {
		return (String) get("type");
	}
	public String getName() {
		return (String) get("name");
	}
	public String getBranch() {
		return (String) get("branch");
	}
	public double getSize() {
		Double size =  (Double) get("size");
		return size.doubleValue();
	}
	public double getPrice() {
		Double price = (Double) get("price");
		return price.doubleValue();
	}
	public String getMeasurement() {
		return (String) get("measurement");
	}
	@Override
	public String toString() {
		return getName();
	}
}