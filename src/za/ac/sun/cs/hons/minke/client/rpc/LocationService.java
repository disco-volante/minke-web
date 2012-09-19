package za.ac.sun.cs.hons.minke.client.rpc;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("location")
public interface LocationService extends RemoteService {

	City getCity(Branch branch);

	Location getLocation(Branch branch);

	City getCity(BranchProduct bp);

	EntityNameMap getCities();

	EntityNameMap getProvinces();

	EntityNameMap getCountries();

	Location getLocation(Long locID);
}
