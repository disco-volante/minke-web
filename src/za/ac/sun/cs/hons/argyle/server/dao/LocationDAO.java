package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.Location;

import com.googlecode.objectify.ObjectifyService;

public class LocationDAO extends ObjectifyDAO<Location> {
    static {

	ObjectifyService.register(Location.class);

    }

    protected LocationDAO(Class<Location> clazz) {
	super(clazz);
    }

}
