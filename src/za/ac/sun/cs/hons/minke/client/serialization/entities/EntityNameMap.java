package za.ac.sun.cs.hons.minke.client.serialization.entities;

import java.util.Collection;
import java.util.HashMap;

import com.googlecode.objectify.annotation.Serialized;

public class EntityNameMap extends IsEntity {
	@Serialized
	HashMap<String, Long> nameMap;
	private EntityID entity;

	public EntityNameMap(){};
	public EntityNameMap(EntityID entity) {
		this.ID = (long) entity.getID();
		this.entity = entity;
		nameMap = new HashMap<String, Long>();

	}

	public void add(long id, String name) {
		nameMap.put(name, id);
	}


	public long getID(String name) {
		return nameMap.get(name);
	}



	public void add(IsEntity entity) {
		add(entity.getID(), entity.toString());
	}

	public void add(IsEntity... entities) {
		for (IsEntity entity : entities) {
			add(entity);
		}
	}

	public Collection<String> getNames() {
		return nameMap.keySet();
	}

	public EntityID getEntity() {
		return entity;
	}

	public boolean contains(String loc) {
		return nameMap.containsKey(loc);
	}

	public boolean isEmpty() {
		return nameMap.size() > 0;
	}


}
