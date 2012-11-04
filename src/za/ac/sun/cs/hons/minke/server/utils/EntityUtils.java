package za.ac.sun.cs.hons.minke.server.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;
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
import za.ac.sun.cs.hons.minke.client.util.Constants;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;

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
					bp.setBranch(ensureBranch(bp.getBranch()));
					Branch branch = bp.getBranch();
					branchProducts.put(branch, new HashSet<BranchProduct>());
					bpSet = branchProducts.get(branch);
				}
				bpSet.add(bp);

			}
		}
		return getMatches(products, branchProducts);
	}

	private static HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> getMatches(
			HashSet<Long> products,
			HashMap<Branch, HashSet<BranchProduct>> branchProducts) {
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

	public static HashSet<Branch> getCountryBranches(Iterable<Country> countries) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (countries != null) {
			for (Country c : countries) {
				String[] propNames = new String[] { "countryID" };
				Object[] propValues = new Object[] { c.getID() };
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

	public static HashSet<Branch> getProvinceBranches(
			Iterable<Province> provinces) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (provinces != null) {
			for (Province p : provinces) {
				branches.addAll(getProvinceBranches(p.getID()));
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

	public static HashSet<Branch> getCityBranches(Iterable<City> cities) {
		HashSet<Branch> branches = new HashSet<Branch>();
		if (cities != null) {
			for (City c : cities) {
				branches.addAll(getCityBranches(c.getID()));
			}
		}
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
	public static HashSet<Product> getProductsByID(Iterable<Long> categories) {
		List<ProductCategory> productCategories = null;
		HashSet<Product> products = new HashSet<Product>();
		if (categories != null) {
			for (long id : categories) {
				String[] propNames = new String[] { "categoryID" };
				Object[] propValues = new Object[] { id };
				productCategories = DAOService.productCategoryDAO
						.listByProperties(propNames, propValues);
				for (ProductCategory pc : productCategories) {
					Product p = DAOService.productDAO.get(pc.getProductID());
					if (p != null) {
						products.add(p);
					}
				}
			}
		}
		return products;
	}

	public static HashSet<Product> getProducts(Iterable<Category> categories) {
		List<ProductCategory> productCategories = null;
		HashSet<Product> products = new HashSet<Product>();
		if (categories != null) {
			for (Category c : categories) {
				String[] propNames = new String[] { "categoryID" };
				Object[] propValues = new Object[] { c.getID() };
				productCategories = DAOService.productCategoryDAO
						.listByProperties(propNames, propValues);
				for (ProductCategory pc : productCategories) {
					Product p = DAOService.productDAO.get(pc.getProductID());
					if (p != null) {
						products.add(p);
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
			double dist = Math.sqrt(Math.pow(latitude
					- b.getLocation().getLat(), 2)
					+ Math.pow(longitude - b.getLocation().getLon(), 2));
			while (sorted.containsKey(dist)) {
				dist += 0.000000001;
			}
			sorted.put(dist, b);
		}
		return sorted.values();
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

	public static BranchProduct getBranchProduct(long productId, long branchId) {
		if (productId == 0L || branchId == 0L) {
			return null;
		}
		Object[] propValues = new Object[] { productId, branchId };
		String[] propNames = new String[] { "productID", "branchID" };
		BranchProduct bp = DAOService.branchProductDAO.getByProperties(
				propNames, propValues);
		if (bp == null) {
			Product product;
			try {
				product = DAOService.productDAO.get(productId);
				Branch branch = DAOService.branchDAO.get(branchId);
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
				return null;
			}

		}
		return bp;
	}

	public static Branch addBranch(Branch branch) {
		Branch holder = null;
		if (branch.getStoreID() != 0L && branch.getLocation() != null
				&& branch.getLocation().getCity().getID() != 0L) {
			CityLocation cl = DAOService.cityLocationDAO.getByProperties(
					new String[] { "cityID", "lat", "lon" }, new Object[] {
							branch.getLocation().getCityID(),
							branch.getLocation().getLat(),
							branch.getLocation().getLon() });
			if (cl != null) {
				branch.setLocation(cl);
				holder = DAOService.branchDAO.getByProperties(
						new String[] { "storeID", "locationID" },
						new Object[] { branch.getStoreID(),
								branch.getLocationID() });
			}

		}
		if (holder != null) {
			return holder;
		} else {
			if (branch.getStore().getID() == 0L) {
				branch.setStore(DAOService.storeDAO.get(DAOService.storeDAO
						.add(new Store(branch.getStore().getName()))));
			}
			if (branch.getLocation().getCity().getID() == 0L) {
				branch.getLocation().setCity(
						DAOService.cityDAO.get(DAOService.cityDAO.add(new City(
								branch.getLocation().getCity().getName(),
								branch.getLocation().getCity().getProvince(),
								branch.getLocation().getCity().getLat(), branch
										.getLocation().getCity().getLon()))));
			}
			branch.setLocation(DAOService.cityLocationDAO
					.get(DAOService.cityLocationDAO.add(new CityLocation(branch
							.getLocation().getName(), branch.getLocation()
							.getCity(), branch.getLocation().getLat(), branch
							.getLocation().getLon()))));
		}
		return DAOService.branchDAO.get(DAOService.branchDAO.add(new Branch(
				branch.getName(), branch.getStore(), branch.getLocation())));
	}

	public static Object[] addBranchProduct(BranchProduct bp, Category category) {
		BranchProduct holder = null;
		ProductCategory pc;
		if (bp.getProductID() != 0L && bp.getBranchID() != 0L) {
			holder = DAOService.branchProductDAO.getByProperties(new String[] {
					"productID", "branchID" }, new Object[] {
					bp.getProductID(), bp.getBranchID() });
		}
		if (holder != null) {
			bp = holder;
			pc = DAOService.productCategoryDAO.getByProperties(
					new String[] { "productID" },
					new Object[] { bp.getProductID() });
			category = DAOService.categoryDAO.get(pc.getCategoryID());
		} else {
			if (category.getID() == 0L) {
				category = DAOService.categoryDAO.get(DAOService.categoryDAO
						.add(new Category(category.getName())));
			}
			if (bp.getProduct().getBrand().getID() == 0L) {
				bp.getProduct().setBrand(
						DAOService.brandDAO.get(DAOService.brandDAO
								.add(new Brand(bp.getProduct().getBrand()
										.getName()))));
			}
			bp.setBranch(ensureBranch(bp.getBranch()));
			bp.setProduct(DAOService.productDAO.get(DAOService.productDAO
					.add(bp.getProduct())));
			pc = DAOService.productCategoryDAO
					.get(DAOService.productCategoryDAO.add(new ProductCategory(
							category, bp.getProduct())));
			bp = DAOService.branchProductDAO.get(DAOService.branchProductDAO
					.add(new BranchProduct(bp.getProduct(), bp.getBranch(), bp
							.getDatePrice())));
			DatePrice dp = DAOService.datePriceDAO.get(DAOService.datePriceDAO
					.add(new DatePrice(bp.getDatePrice().getDate(), bp
							.getDatePrice().getPrice(), bp.getID())));
			bp.setDatePrice(dp);
			bp = DAOService.branchProductDAO.get(DAOService.branchProductDAO
					.add(bp));
		}
		return new Object[] { bp, pc };
	}

	private static Branch ensureBranch(Branch branch) {
		if (branch.getStore() == null && branch.getStoreID() != 0L) {
			branch.setStore(DAOService.storeDAO.get(branch.getStoreID()));
			System.out.println(branch.getStore());

		}
		if (branch.getLocation() == null && branch.getLocationID() != 0L) {
			CityLocation cl = DAOService.cityLocationDAO.get(branch
					.getLocationID());
			branch.setLocation(ensureCityLocation(cl));

		} else if (branch.getLocation() == null && branch.getLocationID() == 0L) {
			CityLocation cl = DAOService.cityLocationDAO.getByProperties(
					new String[] { "name" }, new Object[] { branch.getName() });
			if (cl != null) {
				branch.setLocation(ensureCityLocation(cl));
			}

		}
		return DAOService.branchDAO.get(DAOService.branchDAO.add(branch));
	}

	private static CityLocation ensureCityLocation(CityLocation cl) {
		if (cl.getCity() == null && cl.getCityID() != 0L) {
			City city = DAOService.cityDAO.get(cl.getCityID());
			cl.setCity(ensureCity(city));
			cl = DAOService.cityLocationDAO.get(DAOService.cityLocationDAO
					.add(cl));
		}
		return cl;
	}

	private static City ensureCity(City city) {
		if (city.getProvince() == null && city.getProvinceID() != 0L) {
			Province p = DAOService.provinceDAO.get(city.getProvinceID());
			city.setProvince(ensureProvince(p));
			city = DAOService.cityDAO.get(DAOService.cityDAO.add(city));
		}
		return city;
	}

	private static Province ensureProvince(Province province) {
		if (province.getCountry() == null && province.getCountryID() != 0L) {
			province.setCountry(DAOService.countryDAO.get(province
					.getCountryID()));
			province = DAOService.provinceDAO.get(DAOService.provinceDAO
					.add(province));
		}
		return province;
	}

	public static List<DatePrice> getDatePrices(long bpID) {
		return DAOService.datePriceDAO.listByProperties(
				new String[] { "branchProductID" }, new Object[] { bpID });
	}

	public static BranchProduct updateBranchProduct(
			BranchProduct branchProduct, int price) {
		try {
			DatePrice dp = new DatePrice(new Date(), price,
					branchProduct.getID());
			DAOService.datePriceDAO.add(dp);
			if (branchProduct.getID() == 0L) {
				Product p = DAOService.productDAO.get(branchProduct
						.getProductID());
				Branch b = DAOService.branchDAO
						.get(branchProduct.getBranchID());
				branchProduct = DAOService.branchProductDAO
						.get(DAOService.branchProductDAO.add(new BranchProduct(
								p, b, dp)));
			} else {
				branchProduct = DAOService.branchProductDAO.get(branchProduct
						.getID());
			}
			dp.setBranchProductID(branchProduct.getID());
			DAOService.datePriceDAO.add(dp);
			branchProduct.setDatePrice(dp);
			return DAOService.branchProductDAO.get(DAOService.branchProductDAO
					.add(branchProduct));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

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

	public static Product getProduct(long productId) {
		if (productId == 0L) {
			return null;
		}
		return DAOService.productDAO.get(productId);
	}

	public static Category getCategory(String name) {
		return DAOService.categoryDAO.getByProperties(new String[] { "name" },
				new Object[] { name });
	}

	public static ProductCategory getProductCategory(long productID,
			long categoryID) {
		return DAOService.productCategoryDAO.getByProperties(new String[] {
				"productID", "categoryID" }, new Object[] { productID,
				categoryID });
	}

	public static Brand addBrand(String name) {
		Brand brand = DAOService.brandDAO.getByProperties(
				new String[] { "name" }, new Object[] { name });
		if (brand == null) {
			brand = DAOService.brandDAO.get(DAOService.brandDAO.add(new Brand(
					name)));
		}
		return brand;
	}

	public static Store addStore(String name) {
		Store store = DAOService.storeDAO.getByProperties(
				new String[] { "name" }, new Object[] { name });
		if (store == null) {
			store = DAOService.storeDAO.get(DAOService.storeDAO.add(new Store(
					name)));
		}
		return store;
	}

	public static Branch addHolderBranch(Store store) {
		Branch branch = DAOService.branchDAO.getByProperties(new String[] {
				"name", "storeID" }, new Object[] { "holder", store.getID() });
		if (branch == null) {
			branch = DAOService.branchDAO.get(DAOService.branchDAO
					.add(new Branch("holder", store, null)));
		}
		return branch;
	}

	public static void addBranchProducts(
			HashMap<Category, HashSet<BranchProduct>> map) {
		for (Entry<Category, HashSet<BranchProduct>> entry : map.entrySet()) {
			Category cat = DAOService.categoryDAO.getByProperties(
					new String[] { "name" }, new Object[] { entry.getKey()
							.getName() });
			if (cat == null) {
				cat = DAOService.categoryDAO.get(DAOService.categoryDAO
						.add(entry.getKey()));
			}
			for (BranchProduct online : entry.getValue()) {
				if (DAOService.productDAO.get(online.getProductID()) == null) {
					Product product = DAOService.productDAO
							.get(DAOService.productDAO.add(online.getProduct()));
					ProductCategory pc = new ProductCategory(cat, product);
					DAOService.productCategoryDAO.add(pc);
				}
				List<BranchProduct> bps = DAOService.branchProductDAO
						.listByProperties(new String[] { "productID" },
								new Object[] { online.getProductID() });
				for (BranchProduct bp : bps) {
					if (bp.getBranch().getStore()
							.equals(online.getBranch().getStore())) {
						bp.setDatePrice(online.getDatePrice());
						DAOService.branchProductDAO.add(bp);
					}
				}
			}
		}
	}

	public static List<? extends IsEntity> getAll(String entity) {
		if (entity.equals(Constants.CATEGORY)) {
			return DAOService.categoryDAO.listAll();
		} else if (entity.equals(Constants.PRODUCT)) {
			return DAOService.productDAO.listAll();
		}
		if (entity.equals(Constants.BRAND)) {
			return DAOService.brandDAO.listAll();
		}
		if (entity.equals(Constants.BRANCHPRODUCT)) {
			return DAOService.branchProductDAO.listAll();
		}
		if (entity.equals(Constants.BRANCH)) {
			return DAOService.branchDAO.listAll();
		}
		if (entity.equals(Constants.STORE)) {
			return DAOService.storeDAO.listAll();
		}
		if (entity.equals(Constants.CITYLOCATION)) {
			return DAOService.cityLocationDAO.listAll();
		}
		if (entity.equals(Constants.CITY)) {
			return DAOService.cityDAO.listAll();
		}
		if (entity.equals(Constants.PROVINCE)) {
			return DAOService.provinceDAO.listAll();
		}
		if (entity.equals(Constants.COUNTRY)) {
			return DAOService.countryDAO.listAll();
		}
		if (entity.equals(Constants.DATEPRICE)) {
			return DAOService.datePriceDAO.listAll();
		}
		return null;
	}

	public static void delete(IsEntity entity) {
		if (entity instanceof Category) {
			DAOService.categoryDAO.delete((Category) entity);		
		} else if (entity instanceof Product) {
			DAOService.productDAO.delete((Product) entity);
		} else if (entity instanceof BranchProduct) {
			DAOService.branchProductDAO.delete((BranchProduct) entity);
		} else if (entity instanceof Branch) {
			DAOService.branchDAO.delete((Branch) entity);
		} else if (entity instanceof CityLocation) {
			DAOService.cityLocationDAO.delete((CityLocation) entity);
		} else if (entity instanceof City) {
			DAOService.cityDAO.delete((City) entity);
		} else if (entity instanceof Province) {
			DAOService.provinceDAO.delete((Province) entity);
		} else if (entity instanceof Country) {
			DAOService.countryDAO.delete((Country) entity);
		} else if (entity instanceof Store) {
			DAOService.storeDAO.delete((Store) entity);
		} else if (entity instanceof Brand) {
			DAOService.brandDAO.delete((Brand) entity);
		} else if (entity instanceof DatePrice) {
			DAOService.datePriceDAO.delete((DatePrice) entity);
		}
	}

	public static void update(IsEntity entity) {
		if (entity instanceof Category) {
			DAOService.categoryDAO.add((Category) entity);
		} else if (entity instanceof Product) {
			DAOService.productDAO.add((Product) entity);
		} else if (entity instanceof BranchProduct) {
			DAOService.branchProductDAO.add((BranchProduct) entity);
		} else if (entity instanceof Branch) {
			DAOService.branchDAO.add((Branch) entity);
		} else if (entity instanceof CityLocation) {
			DAOService.cityLocationDAO.add((CityLocation) entity);
		} else if (entity instanceof City) {
			DAOService.cityDAO.add((City) entity);
		} else if (entity instanceof Province) {
			DAOService.provinceDAO.add((Province) entity);
		} else if (entity instanceof Country) {
			DAOService.countryDAO.add((Country) entity);
		} else if (entity instanceof Store) {
			DAOService.storeDAO.add((Store) entity);
		} else if (entity instanceof Brand) {
			DAOService.brandDAO.add((Brand) entity);
		} else if (entity instanceof DatePrice) {
			DAOService.datePriceDAO.add((DatePrice) entity);
		}
	}

}
