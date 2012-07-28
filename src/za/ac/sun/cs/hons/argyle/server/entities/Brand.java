package za.ac.sun.cs.hons.argyle.server.entities;

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

    public Brand(Long id, String name) {
	super();
	this.id = id;
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

}
