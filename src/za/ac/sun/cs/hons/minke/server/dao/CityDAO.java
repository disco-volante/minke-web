package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;

import com.googlecode.objectify.ObjectifyService;

public class CityDAO extends ObjectifyDAO<City> {
    static {

	ObjectifyService.register(City.class);

    }

    public CityDAO() {
	super(City.class);
    }

}
