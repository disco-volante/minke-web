package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;

import com.googlecode.objectify.ObjectifyService;

public class CityLocationDAO extends ObjectifyDAO<CityLocation> {
    static {

	ObjectifyService.register(CityLocation.class);

    }

    public CityLocationDAO() {
	super(CityLocation.class);
    }

}
