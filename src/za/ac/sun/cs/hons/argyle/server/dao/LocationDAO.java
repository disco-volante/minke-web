package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;

import com.googlecode.objectify.ObjectifyService;

public class LocationDAO extends ObjectifyDAO<Location> {
    static {

	ObjectifyService.register(Location.class);

    }

    public LocationDAO() {
	super(Location.class);
    }

}
