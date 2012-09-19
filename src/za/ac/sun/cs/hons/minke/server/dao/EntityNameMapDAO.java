package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.ObjectifyService;

public class EntityNameMapDAO extends ObjectifyDAO<EntityNameMap> {
	static {

		ObjectifyService.register(EntityNameMap.class);

	}

	public EntityNameMapDAO() {
		super(EntityNameMap.class);
	}

	public EntityNameMap get(EntityID entity) throws EntityNotFoundException {
		return get((long)entity.getID());
	}
}
