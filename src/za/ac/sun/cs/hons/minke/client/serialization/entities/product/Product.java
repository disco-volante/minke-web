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
	public CharSequence getSizeString() {
		int intified = (int) size;
		if (size / intified != 1) {
			return size + " " + measurement;
		}
		return intified + " " + measurement;
	}
	@Override
	public String toString() {
		if (brand == null) {
			return getSizeString() + " " + name;
		}
		return getSizeString() + " " + brand.toString() + " " + name;

	}


	public void setBrandID(long id) {
		this.brandID = id;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + (int) (brandID ^ (brandID >>> 32));
		result = prime * result
				+ ((measurement == null) ? 0 : measurement.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = (long)(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Product other = (Product) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (brandID != other.brandID)
			return false;
		if (measurement == null) {
			if (other.measurement != null)
				return false;
		} else if (!measurement.equals(other.measurement))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size){
			return false;
		}else if(getID() != other.getID()){
			return false;
		}
		return true;
	}
}
