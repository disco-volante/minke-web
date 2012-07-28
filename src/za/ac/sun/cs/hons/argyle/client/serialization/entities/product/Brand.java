package za.ac.sun.cs.hons.argyle.client.serialization.entities.product;

import javax.persistence.Id;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Brand extends IsEntity {
    @Id
    private Long   ID;
    private String name;

    public Brand() {
    }

    public Brand(long ID, String name) {
	this.ID = ID;
	this.name = name;
    }

    public long getID() {
	return ID;
    }

    public String getName() {
	return name;
    }
    public String toString(){
	return name;
    }

}
