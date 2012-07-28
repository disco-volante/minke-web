package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import javax.persistence.Embedded;
import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

/**
 * DB Entity used to store data about a specific {@link Product}, e.g. Milk,
 * Dairy, Consumables.
 * 
 * @author godfried
 * 
 */
@Entity
public class Product extends IsEntity {
    @Id
    private Long	    ID;
    private String	  name;
    @Embedded
    private Brand	   brand;
    @Embedded
    private ProductCategory category;
    private double	  size;
    private String	  measurement;

    public Product() {
    }

    public Product(long ID, String name, Brand brand, ProductCategory category,
	    double size, String measurement) {
	this.ID = ID;
	this.name = name;
	this.brand = brand;
	this.category = category;
	this.size = size;
	this.measurement = measurement;
    }

    public long getID() {
	return ID;
    }

    public String getName() {
	return name;
    }

    public Brand getBrand() {
	return brand;
    }

    public ProductCategory getCategory() {
	return category;
    }

    public double getSize() {
	return size;
    }

    public String getMeasurement() {
	return measurement;
    }

    public String toString() {
	return name +" - "+ getBrand() + " ("+getCategory().getType()+")";
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!obj.getClass().equals(Product.class)) {
	    return false;
	}
	Product product = (Product) obj;
	return getName().equals(product.getName())
		&& getCategory().equals(product.getCategory())
		&& getSize() == product.getSize()
		&& getMeasurement().equals(product.getMeasurement())
		&& getID() == product.getID();
    }

}
