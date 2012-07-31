package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Brand extends IsEntity {
    @Id
    private Long   id;
    private String name;

    public Brand() {
    }

    public Brand(String name) {
	super();
	this.name = name;
    }

    public void setID(long id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public long getID() {
	return id;
    }

    public String getName() {
	return name;
    }

    public String toString() {
	return name;
    }

}
