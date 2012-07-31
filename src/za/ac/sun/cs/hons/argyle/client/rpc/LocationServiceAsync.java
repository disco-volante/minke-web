package za.ac.sun.cs.hons.argyle.client.rpc;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocationServiceAsync {

	void getCities(AsyncCallback<HashMap<String, City>> callback);

	void getCity(Branch branch, AsyncCallback<City> locationCallBack);
	void getLocation(Branch branch, AsyncCallback<Location> locationCallBack);

	void getCity(BranchProduct bp, AsyncCallback<City> cityCallBack);

}
