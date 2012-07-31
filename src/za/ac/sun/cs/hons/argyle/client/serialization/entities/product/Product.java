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
    private Long	    id;
    private String	  name;
    @Embedded
    private Brand	   brand;
    private long	    brandID;
    @Embedded
    private ProductCategory category;
    private long	    categoryID;
    private double	  size;
    private String	  measurement;

    public Product() {
    }

    public Product(String name, Brand brand, ProductCategory category,
	    double size, String measurement) {
	super();
	setName(name);
	setBrand(brand);
	setCategory(category);
	setSize(size);
	setMeasurement(measurement);
    }

    public void setID(long id) {
	this.id = id;
    }

    public long getID() {
	return id;
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
	this.brand = brand;
	this.brandID = brand.getID();
    }

    public ProductCategory getCategory() {
	return category;
    }

    public void setCategory(ProductCategory category) {
	this.category = category;
	this.categoryID = category.getID();
    }

    public long getBrandID() {
	return brandID;
    }

    public long getCategoryID() {
	return categoryID;
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
	return brand.getName() + " " + name + " (" + category.getType() + ")";
    }
}
