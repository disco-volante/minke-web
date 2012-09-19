package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;

import com.googlecode.objectify.ObjectifyService;

public class ProvinceDAO extends ObjectifyDAO<Province> {
	static {

		ObjectifyService.register(Province.class);

	}

	public ProvinceDAO() {
		super(Province.class);
	}

}
