package za.ac.sun.cs.hons.minke.server;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;
import za.ac.sun.cs.hons.minke.server.servlets.rpc.LocationServiceImpl;
import za.ac.sun.cs.hons.minke.server.utils.EntityUtils;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class LocationTests {
	private List<Country> countries;
	private List<Province> provinces;
	private List<City> cities;
	private List<CityLocation> locs;
	private List<Store> stores;
	private List<Branch> branches;
	private final String MOCK = "MOCK";
	private final double LAT = 0;
	private final double LON = 0;
	private static LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
		DAOService.init();
		countries = getMockCountries();
		DAOService.countryDAO.add(countries.toArray(new Country[countries
				.size()]));
		provinces = getMockProvinces();
		DAOService.provinceDAO.add(provinces.toArray(new Province[provinces
				.size()]));
		cities = getMockCities();
		DAOService.cityDAO.add(cities.toArray(new City[cities.size()]));
		locs = getMockLocations();
		DAOService.cityLocationDAO.add(locs.toArray(new CityLocation[locs
				.size()]));
		stores = getMockStores();
		DAOService.storeDAO.add(stores.toArray(new Store[stores.size()]));
		branches = getMockBranches();
		DAOService.branchDAO.add(branches.toArray(new Branch[branches.size()]));
	}

	@After
	public void tearDown() {
		for (Branch b : branches) {
			DAOService.branchDAO.delete(b);
		}
		for (Store s : stores) {
			DAOService.storeDAO.delete(s);
		}
		for (CityLocation cl : locs) {
			DAOService.cityLocationDAO.delete(cl);
		}
		for (City city : cities) {
			DAOService.cityDAO.delete(city);
		}
		for (Province p : provinces) {
			DAOService.provinceDAO.delete(p);
		}
		for (Country c : countries) {
			DAOService.countryDAO.delete(c);
		}
		helper.tearDown();
	}

	@Test
	public void branchRetrievalTest() {
		HashSet<Branch> found = EntityUtils.getLocationBranches(null);
		assertEquals(branches.size(), found.size());
		for (Branch b : branches) {
			assertTrue(found.remove(b));
		}
		assertEquals(found.size(), 0);
		found = EntityUtils
				.getLocationBranches(new HashMap<EntityID, HashSet<Long>>());
		assertEquals(branches.size(), found.size());
		for (Branch b : branches) {
			assertTrue(found.remove(b));
		}
		assertEquals(found.size(), 0);
		found = EntityUtils.getLocationBranches(getEmptyLocationsMap());
		assertEquals(0, found.size());
		found = EntityUtils.getLocationBranches(getFullLocationsMap());
		assertEquals(branches.size(), found.size());
		for (Branch b : branches) {
			assertTrue(found.remove(b));
		}
		assertEquals(found.size(), 0);	
		found = EntityUtils.getLocationBranches(getFullCitiesMap());
		assertEquals(branches.size(), found.size());
		for (Branch b : branches) {
			assertTrue(found.remove(b));
		}
		assertEquals(found.size(), 0);
		found = EntityUtils.getLocationBranches(getFullProvincesMap());
		assertEquals(branches.size(), found.size());
		for (Branch b : branches) {
			assertTrue(found.remove(b));
		}
		assertEquals(found.size(), 0);
		found = EntityUtils.getLocationBranches(getFullCountriesMap());
		assertEquals(branches.size(), found.size());
		for (Branch b : branches) {
			assertTrue(found.remove(b));
		}
		assertEquals(found.size(), 0);
	}
	
	@Test
	public void cityRetrievalTest(){
		LocationServiceImpl impl = new LocationServiceImpl();
		EntityNameMap found = impl.getCities();
		for(City c : cities){
			assertTrue(found.contains(c.toString()));
		}
	}
	
	@Test
	public void provinceRetrievalTest(){
		LocationServiceImpl impl = new LocationServiceImpl();
		EntityNameMap found = impl.getProvinces();
		for(Province p : provinces){
			assertTrue(found.contains(p.toString()));
		}
	}
	
	@Test
	public void countryRetrievalTest(){
		LocationServiceImpl impl = new LocationServiceImpl();
		EntityNameMap found = impl.getCountries();
		for(Country c : countries){
			assertTrue(found.contains(c.toString()));
		}
	}
	
	@Test
	public void locationRetrievalTest(){
		LocationServiceImpl impl = new LocationServiceImpl();
		Location loc = impl.getLocation(new Long(-1));
		assertNull(loc);
		loc = impl.getLocation(cities.get(0).getID());
		assertEquals(cities.get(0),loc);
	}

	private HashMap<EntityID, HashSet<Long>> getEmptyLocationsMap() {
		HashMap<EntityID, HashSet<Long>> map = new HashMap<EntityID, HashSet<Long>>();
		map.put(EntityID.CITY, new HashSet<Long>());
		map.put(EntityID.PROVINCE, new HashSet<Long>());
		map.put(EntityID.COUNTRY, new HashSet<Long>());
		map.get(EntityID.CITY).add(cities.get(1).getID());
		map.get(EntityID.CITY).add(cities.get(2).getID());
		map.get(EntityID.PROVINCE).add(provinces.get(1).getID());
		map.get(EntityID.PROVINCE).add(provinces.get(2).getID());
		map.get(EntityID.COUNTRY).add(countries.get(1).getID());
		map.get(EntityID.COUNTRY).add(countries.get(2).getID());
		return map;
	}

	private HashMap<EntityID, HashSet<Long>> getFullCitiesMap() {
		HashMap<EntityID, HashSet<Long>> map = new HashMap<EntityID, HashSet<Long>>();
		map.put(EntityID.CITY, new HashSet<Long>());
		map.get(EntityID.CITY).add(cities.get(0).getID());
		map.get(EntityID.CITY).add(cities.get(4).getID());
		return map;
	}
	
	private HashMap<EntityID, HashSet<Long>> getFullProvincesMap() {
		HashMap<EntityID, HashSet<Long>> map = new HashMap<EntityID, HashSet<Long>>();
		map.put(EntityID.PROVINCE, new HashSet<Long>());
		map.get(EntityID.PROVINCE).add(provinces.get(0).getID());
		map.get(EntityID.PROVINCE).add(provinces.get(4).getID());
		return map;
	}
	
	private HashMap<EntityID, HashSet<Long>> getFullCountriesMap() {
		HashMap<EntityID, HashSet<Long>> map = new HashMap<EntityID, HashSet<Long>>();
		map.put(EntityID.COUNTRY, new HashSet<Long>());
		map.get(EntityID.COUNTRY).add(countries.get(0).getID());
		map.get(EntityID.COUNTRY).add(countries.get(4).getID());
		return map;
	}
	
	private HashMap<EntityID, HashSet<Long>> getFullLocationsMap() {
		HashMap<EntityID, HashSet<Long>> map = new HashMap<EntityID, HashSet<Long>>();
		map.put(EntityID.CITY, new HashSet<Long>());
		map.put(EntityID.PROVINCE, new HashSet<Long>());
		map.put(EntityID.COUNTRY, new HashSet<Long>());
		map.get(EntityID.CITY).add(cities.get(0).getID());
		map.get(EntityID.CITY).add(cities.get(4).getID());
		map.get(EntityID.PROVINCE).add(provinces.get(0).getID());
		map.get(EntityID.PROVINCE).add(provinces.get(4).getID());
		map.get(EntityID.COUNTRY).add(countries.get(0).getID());
		map.get(EntityID.COUNTRY).add(countries.get(4).getID());
		return map;
	}

	private ArrayList<Country> getMockCountries() {
		return new ArrayList<Country>(Arrays.asList(new Country(MOCK),
				new Country(MOCK), new Country(MOCK), new Country(MOCK),
				new Country(MOCK)));
	}

	private ArrayList<Province> getMockProvinces() {
		return new ArrayList<Province>(
				Arrays.asList(new Province(MOCK, countries.get(0)),
						new Province(MOCK, countries.get(0)), new Province(
								MOCK, countries.get(0)), new Province(MOCK,
								countries.get(countries.size()-1)),
						new Province(MOCK, countries.get(countries.size()-1))));
	}

	private ArrayList<City> getMockCities() {
		return new ArrayList<City>(Arrays.asList(
				new City(MOCK, provinces.get(0), LAT, LON), new City(MOCK,
						provinces.get(0), LAT, LON),
				new City(MOCK, provinces.get(0), LAT, LON), new City(MOCK,
						provinces.get(provinces.size() - 1), LAT, LON),
				new City(MOCK, provinces.get(provinces.size() - 1), LAT, LON)));
	}

	private ArrayList<CityLocation> getMockLocations() {
		return new ArrayList<CityLocation>(
				Arrays.asList(new CityLocation(MOCK, cities.get(0), LAT, LON),
						new CityLocation(MOCK, cities.get(0), LAT, LON),
						new CityLocation(MOCK, cities.get(0), LAT, LON),
						new CityLocation(MOCK, cities.get(cities.size() - 1),
								LAT, LON),
						new CityLocation(MOCK, cities.get(cities.size() - 1),
								LAT, LON)));
	}

	private ArrayList<Store> getMockStores() {
		return new ArrayList<Store>(Arrays.asList(new Store(MOCK), new Store(
				MOCK), new Store(MOCK), new Store(MOCK), new Store(MOCK)));
	}

	private ArrayList<Branch> getMockBranches() {
		return new ArrayList<Branch>(Arrays.asList(
				new Branch(MOCK, stores.get(0), locs.get(0)),
				new Branch(MOCK, stores.get(0), locs.get(0)),
				new Branch(MOCK, stores.get(0), locs.get(0)),
				new Branch(MOCK, stores.get(stores.size() - 1), locs.get(locs
						.size() - 1)),
				new Branch(MOCK, stores.get(stores.size() - 1), locs.get(locs
						.size() - 1))));
	}

}
