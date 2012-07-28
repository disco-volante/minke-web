package com.cs.shopper.client.rpc;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("location")
public interface LocationService extends RemoteService {
	HashMap<City, String> getCities();
}
