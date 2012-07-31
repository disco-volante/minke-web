package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;
import java.util.List;

import za.ac.sun.cs.hons.argyle.client.rpc.LocationService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LocationServiceImpl extends RemoteServiceServlet implements
	LocationService {
    private static final long serialVersionUID = -1072938579303987439L;

    @Override
    public HashMap<String, City> getCities() {
	HashMap<String, City> cities = new HashMap<String, City>();
	List<City> retrieved = DAOService.cityDAO.listAll();
	for (City city : retrieved) {
	    cities.put(city.toString(), city);
	}
	return cities;
    }

    @Override
    public City getCity(Branch branch) {
	try {
	    Location retrieved = DAOService.locationDAO.get(branch
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
	    return DAOService.locationDAO.get(branch.getLocationID());
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
}
