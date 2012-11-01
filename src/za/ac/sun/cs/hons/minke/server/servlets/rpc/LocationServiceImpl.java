package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.rpc.LocationService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author godfried
 * 
 */
public class LocationServiceImpl extends RemoteServiceServlet implements
		LocationService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public EntityNameMap getCities() {
		EntityNameMap map = DAOService.entityMapDAO.get((long) EntityID.CITY
				.getID());
		List<City> cities = DAOService.cityDAO.listAll();
		map.add(cities.toArray(new City[cities.size()]));
		DAOService.entityMapDAO.add(map);
		return DAOService.entityMapDAO.get(EntityID.CITY);

	}

	@Override
	public EntityNameMap getCountries() {
		EntityNameMap map = DAOService.entityMapDAO.get((long) EntityID.COUNTRY
				.getID());
		List<Country> countries = DAOService.countryDAO.listAll();
		map.add(countries.toArray(new Country[countries.size()]));
		DAOService.entityMapDAO.add(map);
		return DAOService.entityMapDAO.get(EntityID.COUNTRY);

	}

	@Override
	public EntityNameMap getProvinces() {
		EntityNameMap map = DAOService.entityMapDAO.get((long) EntityID.PROVINCE
				.getID());
		List<Province> provinces = DAOService.provinceDAO.listAll();
		map.add(provinces.toArray(new Province[provinces.size()]));
		DAOService.entityMapDAO.add(map);
		return DAOService.entityMapDAO.get(EntityID.PROVINCE);
	}

	@Override
	public City getCity(Branch branch) {
		CityLocation retrieved = DAOService.cityLocationDAO.get(branch
				.getLocationID());
		return DAOService.cityDAO.get(retrieved.getCityID());

	}

	@Override
	public Location getLocation(Branch branch) {
		return DAOService.cityLocationDAO.get(branch.getLocationID());
	}

	@Override
	public City getCity(BranchProduct bp) {
		Branch retrieved = DAOService.branchDAO.get(bp.getBranchID());
		return retrieved.getLocation().getCity();

	}

	@Override
	public Location getLocation(Long locID) {
		return DAOService.cityDAO.get(locID);

	}
}
