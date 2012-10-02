package za.ac.sun.cs.hons.minke.client.serialization.entities;

import javax.persistence.Id;

import za.ac.sun.cs.hons.minke.client.serialization.GWTSerializable;

import com.googlecode.objectify.annotation.Cached;
import com.googlecode.objectify.annotation.Entity;

@Cached
@Entity
public abstract class IsEntity extends GWTSerializable implements HasID {
	@Id
	protected Long ID;

	@Override
	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

}
