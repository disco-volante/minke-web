package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;

import com.googlecode.objectify.ObjectifyService;

public class CountryDAO extends ObjectifyDAO<Country> {
	static {

		ObjectifyService.register(Country.class);

	}

	public CountryDAO() {
		super(Country.class);
	}
	
	@Override
	public void delete(Country country) {
		List<Province> provinces = DAOService.provinceDAO
				.listByProperties(new String[] { "countryID" },
						new Object[] { country.getID() });
		for (Province province : provinces) {
			DAOService.provinceDAO.delete(province);
		}
		super.delete(country);
	}

}
