package za.ac.sun.cs.hons.minke.server.rpc;

import za.ac.sun.cs.hons.minke.client.rpc.LocationService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;

import com.google.appengine.api.datastore.EntityNotFoundException;
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
		try {
			return DAOService.entityMapDAO.get(EntityID.CITY);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EntityNameMap getCountries() {
		try {
			return DAOService.entityMapDAO.get(EntityID.COUNTRY);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EntityNameMap getProvinces() {
		try {
			return DAOService.entityMapDAO.get(EntityID.PROVINCE);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public City getCity(Branch branch) {
		try {
			CityLocation retrieved = DAOService.cityLocationDAO.get(branch
					.getLocationID());
			return DAOService.cityDAO.get(retrieved.getCityID());
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Location getLocation(Branch branch) {
		try {
			return DAOService.cityLocationDAO.get(branch.getLocationID());
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public City getCity(BranchProduct bp) {
		try {
			Branch retrieved = DAOService.branchDAO.get(bp.getBranchID());
			return retrieved.getLocation().getCity();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Location getLocation(Long locID) {
		try {
			return DAOService.cityDAO.get(locID);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
