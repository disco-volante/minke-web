package za.ac.sun.cs.hons.argyle.client.rpc;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocationServiceAsync {

	void getCities(AsyncCallback<HashMap<String, City>> callback);

}
