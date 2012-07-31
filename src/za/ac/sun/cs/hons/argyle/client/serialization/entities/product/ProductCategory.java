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
    private Long   id;
    private String type;
    private String secondary;
    private String main;

    public ProductCategory() {
    }

    public ProductCategory(String type, String secondary, String main) {
	super();
	this.type = type;
	this.secondary = secondary;
	this.main = main;
    }

    public void setID(long id) {
	this.id = id;
    }

    public void setType(String type) {
	this.type = type;
    }

    public void setSecondary(String secondary) {
	this.secondary = secondary;
    }

    public void setMain(String main) {
	this.main = main;
    }

    public long getID() {
	return id;
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

}
