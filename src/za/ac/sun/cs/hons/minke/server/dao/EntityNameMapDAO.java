package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.googlecode.objectify.ObjectifyService;

public class EntityNameMapDAO extends ObjectifyDAO<EntityNameMap> {
	static {

		ObjectifyService.register(EntityNameMap.class);

	}

	public EntityNameMapDAO() {
		super(EntityNameMap.class);
	}

	public EntityNameMap get(EntityID entity) {
		return get((long)entity.getID());
	}
}
