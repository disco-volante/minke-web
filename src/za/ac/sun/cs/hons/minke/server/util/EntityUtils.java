package za.ac.sun.cs.hons.minke.server.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import za.ac.sun.cs.hons.minke.client.serialization.GPSArea;
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

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;

public class EntityUtils {

	public static HashSet<Branch> getLocationBranches(
			HashMap<EntityID, HashSet<Long>> locations) {
		HashSet<Branch> branches = new HashSet<Branch>();
		for (Entry<EntityID, HashSet<Long>> entry : locations.entrySet()) {
			branches.addAll(getLocationBranches(entry.getKey(),
					entry.getValue()));
		}
		return branches;
	}

	/**
	 * 
	 * @param city
	 * @return
	 */
	public static HashSet<Branch> getLocationBranches(EntityID locType,
			HashSet<Long> locs) {
		HashSet<Branch> branches = new HashSet<Branch>();
		switch (locType) {
		case CITY:
			branches.addAll(getCityBranches(locs));
			break;
		case PROVINCE:
			branches.addAll(getProvinceBranches(locs));
			break;
		case COUNTRY:
			branches.addAll(getCountryBranches(locs));
			break;
		}
		return branches;
	}

	public static HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> getProductBranches(
			HashSet<Long> products) {
		String[] propNames = new String[] { "productID" };
		Object[] propValues;
		HashMap<Branch, HashSet<BranchProduct>> branchProducts = new HashMap<Branch, HashSet<BranchProduct>>();
		for (Long productID : products) {
			propValues = new Object[] { productID };
			BranchProduct bp = DAOService.branchProductDAO.getByProperties(
					propNames, propValues);
			if (bp != null) {
				HashSet<BranchProduct> bpSet = branchProducts.get(bp
						.getBranch());
				if (bpSet == null) {
					Branch branch = bp.getBranch();
					branchProducts.put(branch, new HashSet<BranchProduct>());
					bpSet = branchProducts.get(branch);

				}
				bpSet.add(bp);

			}
		}
		HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> matches = new HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>>();
		for (Entry<Branch, HashSet<BranchProduct>> b : branchProducts
				.entrySet()) {
			if (b.getValue().size() == products.size()) {
				HashMap<BranchProduct, List<DatePrice>> bpMap = new HashMap<BranchProduct, List<DatePrice>>();
				for (BranchProduct bp : b.getValue()) {
					String[] propName = new String[] { "branchProductID" };
					Object[] propValue = new Object[] { bp.getID() };
					List<DatePrice> dps = DAOService.datePriceDAO
							.listByProperties(propName, propValue);
					bpMap.put(bp, dps);
				}
				matches.put(b.getKey(), bpMap);
			}
		}
		return matches;
	}

	public static HashSet<Branch> getCountryBranches(HashSet<Long> locs) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (locs != null) {
			for (long id : locs) {
				String[] propNames = new String[] { "countryID" };
				Object[] propValues = new Object[] { id };
				List<Key<Province>> provinces = DAOService.provinceDAO
						.listKeysByProperties(propNames, propValues);
				branches.addAll(getProvinceBranches(provinces));
			}
		}
		return branches;
	}

	public static HashSet<Branch> getProvinceBranches(List<Key<Province>> locs) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (locs != null) {
			for (Key<Province> key : locs) {
				branches.addAll(getProvinceBranches(key.getId()));
			}
		}
		return branches;
	}

	public static HashSet<Branch> getProvinceBranches(HashSet<Long> locs) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (locs != null) {
			for (Long id : locs) {
				branches.addAll(getProvinceBranches(id));
			}
		}
		return branches;
	}

	public static HashSet<Branch> getProvinceBranches(Long id) {
		HashSet<Branch> branches = new HashSet<Branch>();
		String[] propNames = new String[] { "provinceID" };
		Object[] propValues = new Object[] { id };
		List<Key<City>> cities = DAOService.cityDAO.listKeysByProperties(
				propNames, propValues);
		branches.addAll(getCityBranches(cities));
		return branches;
	}

	public static HashSet<Branch> getCityBranches(List<Key<City>> locs) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (locs != null) {
			for (Key<City> key : locs) {
				branches.addAll(getCityBranches(key.getId()));
			}
		}
		return branches;
	}

	public static HashSet<Branch> getCityBranches(HashSet<Long> locs) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (locs != null) {
			for (Long id : locs) {
				branches.addAll(getCityBranches(id));
			}
		}
		return branches;
	}

	/**
	 * 
	 * @param categories
	 * @return
	 */
	public static HashSet<Product> getProducts(HashSet<Long> categories) {
		List<ProductCategory> productCategories = null;
		HashSet<Product> products = new HashSet<Product>();
		if (categories != null) {
			for (long id : categories) {
				String[] propNames = new String[] { "categoryID" };
				Object[] propValues = new Object[] { id };
				productCategories = DAOService.productCategoryDAO
						.listByProperties(propNames, propValues);
				for (ProductCategory pc : productCategories) {
					try {
						Product p = DAOService.productDAO
								.get(pc.getProductID());
						if (p != null) {
							products.add(p);
						}
					} catch (EntityNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return products;
	}

	public static HashMap<BranchProduct, List<DatePrice>> getBranchProductsByID(
			HashSet<Long> products, HashSet<Branch> branches) {
		Object[] propValues;
		String[] propNames;
		HashMap<BranchProduct, List<DatePrice>> branchProducts = new HashMap<BranchProduct, List<DatePrice>>();
		for (long id : products) {
			for (Branch b : branches) {
				if (b != null) {
					propValues = new Object[] { id, b.getID() };
					propNames = new String[] { "productID", "branchID" };
					BranchProduct bp = DAOService.branchProductDAO
							.getByProperties(propNames, propValues);
					if (bp != null) {
						branchProducts.put(bp, getDatePrices(bp.getID()));
					}
				}
			}
		}
		return branchProducts;
	}

	public static HashMap<BranchProduct, List<DatePrice>> getBranchProducts(
			HashSet<Product> products, HashSet<Branch> branches) {
		Object[] propValues;
		String[] propNames;
		HashMap<BranchProduct, List<DatePrice>> branchProducts = new HashMap<BranchProduct, List<DatePrice>>();
		for (Product p : products) {
			for (Branch b : branches) {
				if (p != null && b != null) {
					propValues = new Object[] { p.getID(), b.getID() };
					propNames = new String[] { "productID", "branchID" };
					BranchProduct bp = DAOService.branchProductDAO
							.getByProperties(propNames, propValues);
					if (bp != null) {
						branchProducts.put(bp, getDatePrices(bp.getID()));
					}
				}
			}
		}
		return branchProducts;
	}

	public static HashSet<Branch> getCityBranches(Long id) {
		HashSet<Branch> branches = new HashSet<Branch>();
		String[] propNames = new String[] { "cityID" };
		Object[] propValues = new Object[] { id };
		List<Key<CityLocation>> locations = DAOService.cityLocationDAO
				.listKeysByProperties(propNames, propValues);
		branches.addAll(getCityLocationBranches(locations));

		return branches;
	}

	public static HashSet<Branch> getCityLocationBranches(
			List<Key<CityLocation>> cityLocations) {
		HashSet<Branch> branches = new HashSet<Branch>();
		String[] propNames = new String[] { "locationID" };
		Object[] propValues;
		for (Key<CityLocation> loc : cityLocations) {
			propValues = new Object[] { loc.getId() };
			Branch branch = DAOService.branchDAO.getByProperties(propNames,
					propValues);
			if (branch != null) {
				branches.add(branch);
			}
		}
		return branches;
	}

	public static Iterable<Branch> getCoordsBranches(double latitude,
			double longitude) {
		List<Branch> all = DAOService.branchDAO.listAll();
		TreeMap<Double, Branch> sorted = new TreeMap<Double, Branch>();
		for (Branch b : all) {
			double dist = Math
					.sqrt(Math.pow(latitude
							- b.getLocation().getCoords().getMinLatitude(), 2)
							+ Math.pow(longitude
									- b.getLocation().getCoords()
											.getMinLongitude(), 2));
			while (sorted.containsKey(dist)) {
				dist += 0.000000001;
			}
			sorted.put(dist, b);
		}
		/*
		 * SortedSet<Map.Entry<Branch, Double>> sortedEntries =
		 * entriesSortedByValues(sorted); ArrayList<Branch> ordered = new
		 * ArrayList<Branch>(sortedEntries.size()); for(Map.Entry<Branch,
		 * Double> b : sortedEntries){ branches.add(b.getKey());
		 * System.out.println(b); } System.out.println(ordered.size());
		 */
		return sorted.values();
	}

	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
				new Comparator<Map.Entry<K, V>>() {
					@Override
					public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
						return e1.getValue().compareTo(e2.getValue());
					}
				});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

	public static HashSet<Branch> getCityLocationBranches(
			HashSet<Long> cityLocations) {
		HashSet<Branch> branches = new HashSet<Branch>();
		String[] propNames = new String[] { "locationID" };
		Object[] propValues;
		for (Long id : cityLocations) {
			propValues = new Object[] { id };
			Branch branch = DAOService.branchDAO.getByProperties(propNames,
					propValues);
			if (branch != null) {
				branches.add(branch);
			}
		}
		return branches;
	}

	public static HashMap<BranchProduct, List<DatePrice>> getBranchProducts(
			HashSet<Long> branches) {
		Object[] propValues;
		String[] propNames;
		HashMap<BranchProduct, List<DatePrice>> branchProducts = new HashMap<BranchProduct, List<DatePrice>>();
		for (Long id : branches) {
			propValues = new Object[] { id };
			propNames = new String[] { "branchID" };
			BranchProduct bp = DAOService.branchProductDAO.getByProperties(
					propNames, propValues);
			if (bp != null) {
				branchProducts.put(bp, getDatePrices(bp.getID()));
			}
		}
		return branchProducts;
	}

	public static List<Brand> getBrands() {
		return DAOService.brandDAO.listAll();
	}

	public static BranchProduct getBranchProduct(Long branchCode, Long barCode) {
		if (barCode.equals(0)) {
			return null;
		}
		Object[] propValues = new Object[] { barCode, branchCode };
		String[] propNames = new String[] { "productID", "branchID" };
		BranchProduct bp = DAOService.branchProductDAO.getByProperties(
				propNames, propValues);
		if (bp == null) {
			Product product;
			try {
				product = DAOService.productDAO.get(barCode);
				Branch branch = DAOService.branchDAO.get(branchCode);
				if (product != null && branch != null) {
					DatePrice dp = new DatePrice(new Date(), 0, 0);
					bp = new BranchProduct(product, branch, null);
					DAOService.branchProductDAO.add(bp);
					dp.setBranchProductID(bp.getID());
					DAOService.datePriceDAO.add(dp);
					bp.setDatePrice(dp);
					DAOService.branchProductDAO.add(bp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return bp;
	}

	public static Map<BranchProduct, List<DatePrice>> addBranchProduct(
			String productName, String brandName, int price, double size,
			String measure, long barCode, long branchCode) {
		Product product;
		Branch branch;
		try {
			product = DAOService.productDAO.get(barCode);
		} catch (Exception e) {
			Brand brand = DAOService.brandDAO.getByProperties(
					new String[] { "name" }, new Object[] { brandName });
			if (brand == null) {
				brand = new Brand(brandName);
				DAOService.brandDAO.add(brand);
			}
			product = new Product(productName, brand, size, measure);
			product.setID(barCode);
			DAOService.productDAO.add(product);
		}
		try {
			branch = DAOService.branchDAO.get(branchCode);
		} catch (Exception e) {
			return null;
		}
		DatePrice dp = new DatePrice(new Date(), price, 0);
		BranchProduct bp = new BranchProduct(product, branch, null);
		DAOService.branchProductDAO.add(bp);
		dp.setBranchProductID(bp.getID());
		DAOService.datePriceDAO.add(dp);
		bp.setDatePrice(dp);
		DAOService.branchProductDAO.add(bp);
		return getBranchProduct(bp.getID());
	}

	public static List<DatePrice> getDatePrices(long bpID) {
		return DAOService.datePriceDAO.listByProperties(
				new String[] { "branchProductID" }, new Object[] { bpID });
	}

	public static boolean updateBranchProduct(long id, int price) {
		BranchProduct bp;
		try {
			bp = DAOService.branchProductDAO.get(id);
			DatePrice dp = new DatePrice(new Date(), price, bp.getID());
			DAOService.datePriceDAO.add(dp);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Map<BranchProduct, List<DatePrice>> getBranchProduct(long id) {
		try {
			BranchProduct bp = DAOService.branchProductDAO.get(id);
			Map<BranchProduct, List<DatePrice>> holder = new HashMap<BranchProduct, List<DatePrice>>();
			holder.put(bp, getDatePrices(bp.getID()));
			return holder;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
     * 
     */
	public static void addData() {
		Category decafTea = new Category("Decaf Tea");
		Category tea = new Category("Tea");
		Category instantCoffee = new Category("Instant Coffee");
		Category coffee = new Category("Coffee");
		Category coffeeBeans = new Category("Coffee Beans");

		DAOService.categoryDAO.add(decafTea, instantCoffee, coffeeBeans, tea,
				coffee);
		EntityNameMap catIDMap = new EntityNameMap(EntityID.CATEGORY);
		catIDMap.add(decafTea, instantCoffee, coffeeBeans, tea, coffee);
		Brand twinings = new Brand("Twinings");
		Brand nestle = new Brand("Nestle");
		Brand illy = new Brand("Illy");
		Brand clipper = new Brand("Clipper");
		Brand caturra = new Brand("Caturra");
		DAOService.brandDAO.add(twinings, nestle, illy, clipper, caturra);
		EntityNameMap brandIDMap = new EntityNameMap(EntityID.BRAND);
		brandIDMap.add(twinings, nestle, illy, clipper, caturra);
		Product earlgray = new Product("Earl Grey", twinings, 25, "ea");
		Product ricoffy = new Product("Ricoffy", nestle, 1.5, "kg");
		Product classic = new Product("Classic", nestle, 1, "kg");
		Product regular = new Product("Regular", illy, 250, "g");
		Product arabica = new Product("Arabica", clipper, 100, "g");
		Product classico = new Product("Classico Connoisseur", caturra, 500,
				"g");

		Map<Key<Product>, Product> productMap = DAOService.productDAO.add(
				earlgray, ricoffy, classic, regular, arabica, classico);
		EntityNameMap productIDMap = new EntityNameMap(EntityID.PRODUCT);
		productIDMap
				.add(earlgray, ricoffy, classic, regular, arabica, classico);
		ProductCategory earlgrayD = new ProductCategory(decafTea, earlgray);
		ProductCategory earlgrayT = new ProductCategory(tea, earlgray);
		ProductCategory classicC = new ProductCategory(coffee, classic);
		ProductCategory classicIC = new ProductCategory(instantCoffee, classic);
		ProductCategory ricoffyIC = new ProductCategory(instantCoffee, ricoffy);
		ProductCategory ricoffyC = new ProductCategory(coffee, ricoffy);
		ProductCategory regularC = new ProductCategory(coffee, regular);
		ProductCategory regularCB = new ProductCategory(coffeeBeans, regular);
		ProductCategory arabicaC = new ProductCategory(coffee, arabica);
		ProductCategory arabicaCB = new ProductCategory(coffeeBeans, arabica);
		ProductCategory classicoC = new ProductCategory(coffee, classico);
		ProductCategory classicoCB = new ProductCategory(coffeeBeans, classico);

		DAOService.productCategoryDAO.add(earlgrayD, earlgrayT, classicC,
				classicIC, ricoffyIC, ricoffyC, regularC, regularCB, arabicaC,
				arabicaCB, classicoC, classicoCB);

		Country sa = new Country("South Africa", new GPSArea(-33.9200, 18.8600));
		DAOService.countryDAO.add(sa);
		EntityNameMap countryIDMap = new EntityNameMap(EntityID.COUNTRY);
		countryIDMap.add(sa);
		Province wc = new Province("Western Cape", sa, new GPSArea(-33.9200,
				18.8600));
		Province gau = new Province("Gauteng", sa, new GPSArea(-33.9200,
				18.8600));
		DAOService.provinceDAO.add(wc, gau);
		EntityNameMap provinceIDMap = new EntityNameMap(EntityID.PROVINCE);
		provinceIDMap.add(wc, gau);
		City stellenbosch = new City("Stellenbosch", wc, new GPSArea(-33.9200,
				18.8600));
		City capeTown = new City("Cape Town", wc,
				new GPSArea(-33.9767, 18.4244));
		City somersetWest = new City("Somerset West", wc, new GPSArea(-34.0833,
				18.8500));
		City paarl = new City("Paarl", wc, new GPSArea(-33.7242, 18.9558));
		City joburg = new City("Johannesburg", gau, new GPSArea(-26.2000,
				28.0667));
		City pretoria = new City("Pretoria", gau,
				new GPSArea(-25.7256, 28.2439));

		DAOService.cityDAO.add(stellenbosch, capeTown, somersetWest, paarl,
				joburg, pretoria);
		EntityNameMap cityIDMap = new EntityNameMap(EntityID.CITY);
		cityIDMap.add(stellenbosch, capeTown, somersetWest, paarl, joburg,
				pretoria);
		CityLocation dieBoord = new CityLocation("Die Boord", stellenbosch,
				new GPSArea(-33.9447319, 18.8500055));
		CityLocation simonsrust = new CityLocation("Simonsrust", stellenbosch,
				new GPSArea(-33.926617, 18.878612));
		CityLocation stellmark = new CityLocation("Stellmark", stellenbosch,
				new GPSArea(-33.9319048, 18.8593215));
		CityLocation eikestad = new CityLocation("Eikestad", stellenbosch,
				new GPSArea(-33.9359105, 18.860566));
		CityLocation millstreet = new CityLocation("Mill Street", stellenbosch,
				new GPSArea(-33.9383672, 18.8592785));
		DAOService.cityLocationDAO.add(dieBoord, simonsrust, stellmark,
				eikestad, millstreet);
		EntityNameMap cityLocIDMap = new EntityNameMap(EntityID.CITYLOCATION);
		cityLocIDMap.add(dieBoord, simonsrust, stellmark, eikestad, millstreet);
		Store spar = new Store("Spar");
		Store picknPay = new Store("Pick 'n Pay");
		Store checkers = new Store("Checkers");
		Store shoprite = new Store("Shoprite");
		Store oK = new Store("OK");
		Store woolies = new Store("Woolworths");
		DAOService.storeDAO
				.add(spar, picknPay, checkers, shoprite, oK, woolies);
		EntityNameMap storeIDMap = new EntityNameMap(EntityID.STORE);
		storeIDMap.add(spar, picknPay, checkers, shoprite, oK, woolies);
		Branch sparDieBoord = new Branch("Die Boord", spar, dieBoord);
		Branch picknPayStellmark = new Branch("Stellmark", picknPay, stellmark);
		Branch sparSimonsrust = new Branch("Simonsrust", spar, dieBoord);
		Branch checkersMillstreet = new Branch("Mill Street", checkers,
				millstreet);
		Branch wooliesEikestad = new Branch("Eikestad", woolies, eikestad);
		Branch shopriteEikestad = new Branch("Eikestad", shoprite, eikestad);
		DAOService.branchDAO.add(sparDieBoord, picknPayStellmark,
				sparSimonsrust, checkersMillstreet, wooliesEikestad,
				shopriteEikestad);
		EntityNameMap branchIDMap = new EntityNameMap(EntityID.BRANCH);
		branchIDMap.add(sparDieBoord, picknPayStellmark, sparSimonsrust,
				checkersMillstreet, wooliesEikestad, shopriteEikestad);
		long year2010 = 1182304000000L;
		long month = 2592000000L;
		int i = 0;
		HashSet<BranchProduct> bps = new HashSet<BranchProduct>();
		EntityNameMap bpIDMap = new EntityNameMap(EntityID.BRANCHPRODUCT);
		for (Entry<Key<Product>, Product> entry : productMap.entrySet()) {
			BranchProduct bp = new BranchProduct(entry.getValue(),
					sparDieBoord, null);
			bp.setID(100000 + i);
			DatePrice dp = new DatePrice(new Date(year2010 + i * month),
					10000 + i, bp.getID());
			DAOService.datePriceDAO.add(dp);
			bp.setDatePrice(dp);
			DAOService.branchProductDAO.add(bp);
			bps.add(bp);
			bpIDMap.add(bp);
			i++;
		}
		long lastdate = year2010 + i * month;
		int lastprice = 10000 + i;

		int j = 100;
		int inc = 5;
		for (i = 0; i < 10; i++) {
			int c = 0;
			for (BranchProduct bp : bps) {
				DatePrice dp = new DatePrice(new Date(lastdate + i * month),
						lastprice, bp.getID());
				DAOService.datePriceDAO.add(dp);
				j += inc * c;
				if (j != 0) {
					inc -= inc / Math.abs(j);
				}
				if (inc <= 0) {
					inc = Math.abs(j) / 2;
					j = -j;
				}
				lastprice = lastprice + j;
				c++;
			}
		}
		DAOService.entityMapDAO.add(catIDMap, bpIDMap, productIDMap,
				storeIDMap, branchIDMap, brandIDMap, cityIDMap, provinceIDMap,
				countryIDMap, cityLocIDMap);

	}

}
