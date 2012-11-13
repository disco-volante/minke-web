package za.ac.sun.cs.hons.minke.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.CityLocation;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;
import za.ac.sun.cs.hons.minke.server.servlets.rpc.BranchProductServiceImpl;
import za.ac.sun.cs.hons.minke.server.servlets.rpc.CategoryServiceImpl;
import za.ac.sun.cs.hons.minke.server.servlets.rpc.ProductServiceImpl;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ProductTests {
	private static final double SIZE = 0;
	private static final int PRICE = 0;
	private List<Country> countries;
	private List<Province> provinces;
	private List<City> cities;
	private List<Product> products;
	private List<Category> categories;
	private List<Brand> brands;
	private List<CityLocation> locs;
	private List<Store> stores;
	private List<Branch> branches;
	private HashMap<BranchProduct, List<DatePrice>> bps;
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
		brands = getMockBrands();
		DAOService.brandDAO.add(brands.toArray(new Brand[brands.size()]));
		categories = getMockCategories();
		DAOService.categoryDAO.add(categories.toArray(new Category[categories
				.size()]));
		products = getMockProducts();
		DAOService.productDAO
				.add(products.toArray(new Product[products.size()]));
		locs = getMockLocations();
		DAOService.cityLocationDAO.add(locs.toArray(new CityLocation[locs
				.size()]));
		stores = getMockStores();
		DAOService.storeDAO.add(stores.toArray(new Store[stores.size()]));
		branches = getMockBranches();
		DAOService.branchDAO.add(branches.toArray(new Branch[branches.size()]));
		bps = getMockBranchProducts();
	}

	@After
	public void tearDown() {
		for(BranchProduct bp : bps.keySet()){
			DAOService.branchProductDAO.delete(bp);
		}
		for (Branch b : branches) {
			DAOService.branchDAO.delete(b);
		}
		for (Store s : stores) {
			DAOService.storeDAO.delete(s);
		}
		for (CityLocation cl : locs) {
			DAOService.cityLocationDAO.delete(cl);
		}
		for (Product p : products) {
			DAOService.productDAO.delete(p);
		}
		for (Category c : categories) {
			DAOService.categoryDAO.delete(c);
		}
		for (Brand b : brands) {
			DAOService.brandDAO.delete(b);
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
	public void productRetrievalTest() {
		//Test that a product is retrieved properly
		ProductServiceImpl impl = new ProductServiceImpl();
		for(BranchProduct bp : bps.keySet()){
			assertEquals(bp.getProduct(),impl.getProduct(bp));
		}
	}

	@Test
	public void productsRetrievalTest() {
		//Test that product names are retrieved properly
		ProductServiceImpl impl = new ProductServiceImpl();
		EntityNameMap found = impl.getProducts();
		for(Product p : products){
			assertTrue(found.contains(p.toString()));
		}
	}
	
	@Test
	public void categoriesRetrievalTest() {
		//Test that category names are retrieved properly
		CategoryServiceImpl impl = new CategoryServiceImpl();
		EntityNameMap found = impl.getCategories();
		for(Category c : categories){
			assertTrue(found.contains(c.toString()));
		}
	}
	
	@Test
	public void branchProductsRetrievalTest() {
		//Test that branchproducts are retrieved properly
		BranchProductServiceImpl impl = new BranchProductServiceImpl();
		HashMap<BranchProduct, List<DatePrice>> found = impl.getBranchProducts(getFullLocationsMap(), getAllCategoryIDS(), getAllProductIDS());
		assertEquals(bps.size(), found.size());
		found = impl.getBranchProducts(getFullLocationsMap(), null, null);
		assertEquals(bps.size(), found.size());
		found = impl.getBranchProducts(getEmptyLocationsMap(), null, null);
		assertEquals(0, found.size());
		found = impl.getBranchProducts(getEmptyLocationsMap(), getAllCategoryIDS(), getAllProductIDS());
		assertEquals(0, found.size());
		Product p = products.get(0);
		found = impl.getBranchProducts(getFullLocationsMap(), null, new HashSet<Long>(Arrays.asList(p.getID())));
		for(Entry<BranchProduct, List<DatePrice>> entry : found.entrySet()){
			assertEquals(p, entry.getKey().getProduct());
		}
		Category c = categories.get(0);
		found = impl.getBranchProducts(getFullLocationsMap(), new HashSet<Long>(Arrays.asList(c.getID())),null);
		List<ProductCategory> pcs = DAOService.productCategoryDAO.listByProperties(new String[]{"categoryID"}, new Object[]{c.getID()});
		ArrayList<Product> matches = new ArrayList<Product>();
		for(ProductCategory pc : pcs){
			matches.add(DAOService.productDAO.get(pc.getProductID()));
		}
		for(Entry<BranchProduct, List<DatePrice>> entry : found.entrySet()){
			assertTrue(matches.contains(entry.getKey().getProduct()));
		}	
		p = products.get(4);
		found = impl.getBranchProducts(getFullLocationsMap(), new HashSet<Long>(Arrays.asList(c.getID())),new HashSet<Long>(Arrays.asList(p.getID())));

		for(Entry<BranchProduct, List<DatePrice>> entry : found.entrySet()){
			boolean cat = matches.contains(entry.getKey().getProduct());
			if(!cat){
				assertEquals(p, entry.getKey().getProduct());
			}
		}			

	}

	private HashSet<Long> getAllProductIDS() {
		HashSet<Long> ids = new HashSet<Long>();
		for(Product p : products){
			ids.add(p.getID());
		}
		return ids;
	}

	private HashSet<Long> getAllCategoryIDS() {
		HashSet<Long> ids = new HashSet<Long>();
		for(Category c : categories){
			ids.add(c.getID());
		}
		return ids;
	}

	private ArrayList<Brand> getMockBrands() {
		return new ArrayList<Brand>(Arrays.asList(new Brand(MOCK), new Brand(
				MOCK), new Brand(MOCK), new Brand(MOCK), new Brand(MOCK)));
	}

	private ArrayList<Category> getMockCategories() {
		return new ArrayList<Category>(Arrays.asList(new Category(MOCK),
				new Category(MOCK), new Category(MOCK), new Category(MOCK),
				new Category(MOCK)));
	}

	private ArrayList<Product> getMockProducts() {
		return new ArrayList<Product>(Arrays.asList(
				new Product(MOCK, brands.get(0), SIZE, MOCK), new Product(MOCK,
						brands.get(0), SIZE, MOCK),
				new Product(MOCK, brands.get(0), SIZE, MOCK), new Product(MOCK,
						brands.get(brands.size() - 1), SIZE, MOCK),
				new Product(MOCK, brands.get(brands.size() - 1), SIZE, MOCK)));
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

	private HashMap<BranchProduct, List<DatePrice>> getMockBranchProducts() {
		HashMap<BranchProduct, List<DatePrice>> bps = new HashMap<BranchProduct, List<DatePrice>>();
		DatePrice dp = new DatePrice(new Date(), PRICE, -1);
		BranchProduct bp = new BranchProduct(products.get(0), branches.get(0),
				dp);
		DAOService.branchProductDAO.add(bp);
		List<DatePrice> dps = new ArrayList<DatePrice>();
		dps.add(dp);
		for(int i = 0; i < 10; i ++){
			dp = new DatePrice(new Date(), PRICE, -1);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			dps.add(dp);
		}
		bps.put(bp, dps);
		dps = new ArrayList<DatePrice>();
		dp = new DatePrice(new Date(), PRICE, -1);
		bp = new BranchProduct(products.get(products.size()-1), branches.get(0), dp);
		DAOService.branchProductDAO.add(bp);
		dps.add(dp);
		for(int i = 0; i < 10; i ++){
			dp = new DatePrice(new Date(), PRICE, -1);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			dps.add(dp);
		}
		bps.put(bp, dps);
		dps = new ArrayList<DatePrice>();
		dp = new DatePrice(new Date(), PRICE, -1);
		bp = new BranchProduct(products.get(products.size()-1), branches.get(branches.size()-1), dp);
		DAOService.branchProductDAO.add(bp);
		dps.add(dp);
		for(int i = 0; i < 10; i ++){
			dp = new DatePrice(new Date(), PRICE, -1);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			dps.add(dp);
		}
		bps.put(bp, dps);
		dps = new ArrayList<DatePrice>();
		dp = new DatePrice(new Date(), PRICE, -1);
		bp = new BranchProduct(products.get(0), branches.get(branches.size()-1), dp);
		DAOService.branchProductDAO.add(bp);
		dps.add(dp);
		for(int i = 0; i < 10; i ++){
			dp = new DatePrice(new Date(), PRICE, -1);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			dps.add(dp);
		}
		bps.put(bp, dps);
		dps = new ArrayList<DatePrice>();
		dp = new DatePrice(new Date(), PRICE, -1);
		bp = new BranchProduct(products.get(0), branches.get(2), dp);
		DAOService.branchProductDAO.add(bp);
		dps.add(dp);
		for(int i = 0; i < 10; i ++){
			dp = new DatePrice(new Date(), PRICE, -1);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			dps.add(dp);
		}
		bps.put(bp, dps);
		return bps;
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
}
