package za.ac.sun.cs.hons.argyle.server.entities;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.Key;
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
    private Long		 id;
    private String	       name;
    private Key<Brand>	   brandKey;
    private Key<ProductCategory> categoryKey;
    private double	       size;
    private String	       measurement;

    public Product() {
    }

    public Product(Long id, String name, Key<Brand> brandKey,
	    Key<ProductCategory> categoryKey, double size, String measurement) {
	super();
	this.id = id;
	this.name = name;
	this.brandKey = brandKey;
	this.categoryKey = categoryKey;
	this.size = size;
	this.measurement = measurement;
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

    public Key<Brand> getBrandKey() {
	return brandKey;
    }

    public void setBrandKey(Key<Brand> brandKey) {
	this.brandKey = brandKey;
    }

    public Key<ProductCategory> getCategoryKey() {
	return categoryKey;
    }

    public void setCategoryKey(Key<ProductCategory> categoryKey) {
	this.categoryKey = categoryKey;
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

}
