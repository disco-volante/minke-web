package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.rpc.LocationService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class LocationServiceImpl extends RemoteServiceServlet implements
		LocationService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public HashMap<String, City> getCities() {
		Objectify ofy = ObjectifyService.begin();
		Query<City> q = ofy.query(City.class);
		HashMap<String, City> cities = new HashMap<String, City>();
		for (City c : q) {
			cities.put(c.toString(), c);
		}
		return cities;
	}

}
