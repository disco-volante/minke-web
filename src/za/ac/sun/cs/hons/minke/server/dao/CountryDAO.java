package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;

import com.googlecode.objectify.Key;
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
		List<Province> provinces = DAOService.provinceDAO.listByProperties(
				new String[] { "countryID" }, new Object[] { country.getID() });
		if (provinces != null) {
			for (Province province : provinces) {
				DAOService.provinceDAO.delete(province);
			}
		}
		super.delete(country);
	}

	@Override
	public Key<Country> add(Country country) {
		if (country != null && get(country.getID()) != null) {
			List<Province> provinces = DAOService.provinceDAO.listByProperties(
					new String[] { "countryID" },
					new Object[] { country.getID() });
			if (provinces != null) {
				for (Province province : provinces) {
					province.setCountry(country);
					DAOService.provinceDAO.add(province);
				}
			}
		}
		return super.add(country);
	}

}
