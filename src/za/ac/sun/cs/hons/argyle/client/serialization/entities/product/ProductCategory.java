package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

/**
 * DB Entity used to store data about a {@link Product}'s categories, e.g. Milk,
 * Dairy, Consumables.
 * 
 * @author godfried
 * 
 */
@Entity
public class ProductCategory extends IsEntity {
    @Id
    private Long   ID;
    private String type;
    private String secondary;
    private String main;

    public ProductCategory() {
    }

    public ProductCategory(long ID, String type, String secondary, String main) {
	this.ID = ID;
	this.type = type;
	this.secondary = secondary;
	this.main = main;
    }

    public long getID() {
	return ID;
    }

    public String getType() {
	return type;
    }

    public String getSecondary() {
	return secondary;
    }

    public String getMain() {
	return main;
    }

    @Override
    public String toString() {
	return type + ", " + secondary + ", " + main;

    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (!obj.getClass().equals(ProductCategory.class)) {
	    return false;
	}
	ProductCategory productCategory = (ProductCategory) obj;
	return getMain().equals(productCategory.getMain())
		&& getSecondary().equals(productCategory.getSecondary())
		&& getType().equals(productCategory.getType())
		&& getID() == productCategory.getID();
    }

}
