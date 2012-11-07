package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class CityDAO extends ObjectifyDAO<City> {
	static {

		ObjectifyService.register(City.class);

	}

	public CityDAO() {
		super(City.class);
	}

	@Override
	public void delete(City city) {
		List<CityLocation> cls = DAOService.cityLocationDAO.listByProperties(
				new String[] { "cityID" }, new Object[] { city.getID() });
		if (cls != null) {
			for (CityLocation cl : cls) {
				DAOService.cityLocationDAO.delete(cl);
			}
		}
		super.delete(city);
	}

	@Override
	public Key<City> add(City city) {
		if (city != null && get(city.getID()) != null) {
			List<CityLocation> cls = DAOService.cityLocationDAO
					.listByProperties(new String[] { "cityID" },
							new Object[] { city.getID() });
			if (cls != null) {
				for (CityLocation cl : cls) {
					cl.setCity(city);
					DAOService.cityLocationDAO.add(cl);
				}
			}
		}
		return super.add(city);
	}

}
