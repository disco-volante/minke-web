package za.ac.sun.cs.hons.minke.client.rpc;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocationServiceAsync {


	void getCity(Branch branch, AsyncCallback<City> locationCallBack);
	void getCities(AsyncCallback<EntityNameMap> callback);
	void getCountries(AsyncCallback<EntityNameMap> callback);
	void getProvinces(AsyncCallback<EntityNameMap> callback);
	void getLocation(Long locID, AsyncCallback<Location> callback);


}
