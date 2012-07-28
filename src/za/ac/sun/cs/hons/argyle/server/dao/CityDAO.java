package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.City;

import com.googlecode.objectify.ObjectifyService;

public class CityDAO extends ObjectifyDAO<City> {
    static {

	ObjectifyService.register(City.class);

    }

    protected CityDAO(Class<City> clazz) {
	super(clazz);
    }

}
