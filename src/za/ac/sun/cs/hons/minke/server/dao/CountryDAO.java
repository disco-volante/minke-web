package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;

import com.googlecode.objectify.ObjectifyService;

public class CountryDAO extends ObjectifyDAO<Country> {
	static {

		ObjectifyService.register(Country.class);

	}

	public CountryDAO() {
		super(Country.class);
	}

}
