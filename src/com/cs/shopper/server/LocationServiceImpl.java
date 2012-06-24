package com.cs.shopper.server;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.rpc.LocationService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class LocationServiceImpl extends RemoteServiceServlet implements
		LocationService{
	private static final long serialVersionUID = -1072938579303987439L;

	
	@Override
	public HashMap<City,String> getCities(){
		Objectify ofy = ObjectifyService.begin();
		addCities(ofy);
		Query<City> q = ofy.query(City.class);
		HashMap<City, String> cities = new HashMap<City, String>();
		for(City c:q) cities.put(c,c.getName());
		return cities;
	}
	private void addCities(Objectify ofy){
		ofy.put(new City("Stellenbosch","Western Cape","South Africa"));
		ofy.put(new City("Cape Town","Western Cape","South Africa"));
		ofy.put(new City("Somerset West","Western Cape","South Africa"));
		ofy.put(new City("Paarl","Western Cape","South Africa"));
		ofy.put(new City("Johannesburg","Gauteng","South Africa"));
		ofy.put(new City("Pretoria","Gauteng","South Africa"));

	}

}
