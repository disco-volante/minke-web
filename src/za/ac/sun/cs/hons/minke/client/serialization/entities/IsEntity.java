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
		if(ID == null){
			return -1;
		}
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IsEntity other = (IsEntity) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}

}
