package com.cs.shopper.client.rpc;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocationServiceAsync {

	void getCities(AsyncCallback<HashMap<City, String>> callback);

}
