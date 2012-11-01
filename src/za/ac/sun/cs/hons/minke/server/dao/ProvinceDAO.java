package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;

import com.googlecode.objectify.ObjectifyService;

public class ProvinceDAO extends ObjectifyDAO<Province> {
	static {

		ObjectifyService.register(Province.class);

	}

	public ProvinceDAO() {
		super(Province.class);
	}
	
	@Override
	public void delete(Province province) {
		List<City> cities = DAOService.cityDAO
				.listByProperties(new String[] { "provinceID" },
						new Object[] { province.getID() });
		for (City c : cities) {
			DAOService.cityDAO.delete(c);
		}
		super.delete(province);
	}

}
