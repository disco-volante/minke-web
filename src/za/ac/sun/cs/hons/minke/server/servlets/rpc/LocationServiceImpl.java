package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import za.ac.sun.cs.hons.minke.client.rpc.LocationService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
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
			return DAOService.entityMapDAO.get(EntityID.CITY);
		
	}

	@Override
	public EntityNameMap getCountries() {
			return DAOService.entityMapDAO.get(EntityID.COUNTRY);
		
	}

	@Override
	public EntityNameMap getProvinces() {
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
