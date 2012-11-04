package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;

import com.googlecode.objectify.ObjectifyService;

public class LocationDAO extends ObjectifyDAO<Location> {
	static {

		ObjectifyService.register(Location.class);

	}

	public LocationDAO() {
		super(Location.class);
	}

}
