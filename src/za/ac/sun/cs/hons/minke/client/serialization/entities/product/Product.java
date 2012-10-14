package za.ac.sun.cs.hons.minke.client.serialization.entities.product;

import javax.persistence.Embedded;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Subclass;

/**
 * DB Entity used to store data about a specific {@link Product}, e.g. Milk,
 * Dairy, Consumables.
 * 
 * @author godfried
 * 
 */
@Subclass
public class Product extends IsEntity {
	private String name;
	@Embedded
	private Brand brand;
	private long brandID;
	private double size;
	private String measurement;

	public Product() {
	}

	public Product(String name, Brand brand, double size, String measurement) {
		super();
		setName(name);
		setBrand(brand);
		setSize(size);
		setMeasurement(measurement);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		if (brand != null) {
			this.brand = brand;
			this.brandID = brand.getID();
		}
	}

	public long getBrandID() {
		return brandID;
	}

	public double getSize() {
		return size;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	@Override
	public String toString() {
		if(brand == null){
			return name;
		}
		return brand.getName() + " : " + name;
	}

	public void setBrandID(long id) {
		this.brandID = id;

	}
}
