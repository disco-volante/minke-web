package za.ac.sun.cs.hons.minke.server.utils.json;

import java.util.HashMap;
import java.util.List;

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

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class JSONBuilder {
	public static JSONObject toJSON(
			HashMap<BranchProduct, List<DatePrice>> branchProducts)
			throws JSONException {
		JSONObject bps = BranchProductstoJSON(branchProducts.keySet());
		JSONObject dps = new JSONObject();
		dps.put("type", "datePrices");
		JSONArray array = new JSONArray();
		for (List<DatePrice> list : branchProducts.values()) {
			for (DatePrice dp : list) {
				array.put(toJSON(dp));
			}
		}
		dps.put("datePrices", array);
		return toJSON(bps, dps);

	}

	public static JSONObject ProductstoJSON(Iterable<Product> products)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "products");
		JSONArray array = new JSONArray();
		for (Product p : products) {
			array.put(toJSON(p));
		}
		obj.put("products", array);
		return obj;

	}

	public static JSONObject CountriestoJSON(Iterable<Country> countries)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "countries");
		JSONArray array = new JSONArray();
		for (Country c : countries) {
			array.put(toJSON(c));
		}
		obj.put("countries", array);
		return obj;

	}

	public static JSONObject ProvincestoJSON(Iterable<Province> provinces)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "provinces");
		JSONArray array = new JSONArray();
		for (Province p : provinces) {
			array.put(toJSON(p));
		}
		obj.put("provinces", array);
		return obj;

	}

	public static JSONObject CitiestoJSON(Iterable<City> cities)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "cities");
		JSONArray array = new JSONArray();
		for (City c : cities) {
			array.put(toJSON(c));
		}
		obj.put("cities", array);
		return obj;

	}

	public static JSONObject CategoriestoJSON(Iterable<Category> categories)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "categories");
		JSONArray array = new JSONArray();
		for (Category c : categories) {
			array.put(toJSON(c));
		}
		obj.put("categories", array);
		return obj;
	}

	public static JSONObject BrandstoJSON(Iterable<Brand> brands)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "brands");
		JSONArray array = new JSONArray();
		for (Brand b : brands) {
			array.put(toJSON(b));
		}
		obj.put("brands", array);
		return obj;
	}

	public static JSONObject BranchestoJSON(Iterable<Branch> branches)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "branches");
		JSONArray array = new JSONArray();
		for (Branch b : branches) {
			array.put(toJSON(b));
		}
		obj.put("branches", array);
		return obj;
	}

	public static JSONObject BranchProductstoJSON(
			Iterable<BranchProduct> branchProducts) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "branchProducts");
		JSONArray array = new JSONArray();
		for (BranchProduct b : branchProducts) {
			array.put(toJSON(b));
		}
		obj.put("branchProducts", array);
		return obj;
	}

	public static JSONObject DatePricestoJSON(Iterable<DatePrice> datePrices)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "datePrices");
		JSONArray array = new JSONArray();
		for (DatePrice dp : datePrices) {
			array.put(toJSON(dp));
		}
		obj.put("datePrices", array);
		return obj;
	}

	public static JSONObject CityLocationstoJSON(
			Iterable<CityLocation> cityLocations) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "cityLocations");
		JSONArray array = new JSONArray();
		for (CityLocation cl : cityLocations) {
			array.put(toJSON(cl));
		}
		obj.put("cityLocations", array);
		return obj;
	}

	public static JSONObject StorestoJSON(Iterable<Store> stores)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "stores");
		JSONArray array = new JSONArray();
		for (Store s : stores) {
			array.put(toJSON(s));
		}
		obj.put("stores", array);
		return obj;
	}

	public static JSONObject ProductCategoriestoJSON(
			List<ProductCategory> productCategories) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "productCategories");
		JSONArray array = new JSONArray();
		for (ProductCategory p : productCategories) {
			array.put(toJSON(p));
		}
		obj.put("productCategories", array);
		return obj;
	}

	public static JSONObject toJSON(JSONObject... objs) throws JSONException {
		JSONObject all = new JSONObject();
		for (JSONObject obj : objs) {
			all.put(obj.getString("type"), obj);
		}
		return all;
	}

	public static JSONObject toJSON(Country c) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "country");
		obj.put("id", c.getID());
		obj.put("name", c.getName());
		return obj;
	}

	public static JSONObject toJSON(Province p) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "province");
		obj.put("id", p.getID());
		obj.put("name", p.getName());
		obj.put("countryId", p.getCountryID());
		return obj;
	}

	public static JSONObject toJSON(City c) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "city");
		obj.put("id", c.getID());
		obj.put("name", c.getName());
		obj.put("provinceId", c.getProvinceID());
		obj.put("lon", c.getLon());
		obj.put("lat", c.getLat());
		return obj;
	}

	public static JSONObject toJSON(Category c) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "category");
		obj.put("id", c.getID());
		obj.put("name", c.getName());
		return obj;
	}

	public static JSONObject toJSON(String name, Object arg)
			throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", name);
		obj.put(name, arg);
		return obj;
	}

	public static JSONObject toJSON(Store store) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "store");
		obj.put("id", store.getID());
		obj.put("name", store.getName());
		return obj;
	}

	public static JSONObject toJSON(Brand brand) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "brand");
		obj.put("id", brand.getID());
		obj.put("name", brand.getName());
		return obj;
	}

	public static JSONObject toJSON(BranchProduct bp) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "branchProduct");
		obj.put("id", bp.getID());
		obj.put("productId", bp.getProductID());
		obj.put("branchId", bp.getBranchID());
		obj.put("datePriceId", bp.getDatePriceID());
		return obj;
	}

	public static JSONObject toJSON(CityLocation cl) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "cityLocation");
		obj.put("id", cl.getID());
		obj.put("name", cl.getName());
		obj.put("cityId", cl.getCityID());
		obj.put("lon", cl.getLon());
		obj.put("lat", cl.getLat());
		return obj;
	}

	public static JSONObject toJSON(DatePrice dp) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "datePrice");
		obj.put("id", dp.getID());
		obj.put("date", dp.getDate().getTime());
		obj.put("price", dp.getPrice());
		obj.put("branchProductID", dp.getBranchProductID());
		return obj;
	}

	public static JSONObject toJSON(Branch branch) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "branch");
		obj.put("id", branch.getID());
		obj.put("name", branch.getName());
		obj.put("storeId", branch.getStoreID());
		obj.put("cityLocationId", branch.getLocationID());
		return obj;
	}

	public static JSONObject toJSON(Product p) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "product");
		obj.put("id", p.getID());
		obj.put("name", p.getName());
		obj.put("brandId", p.getBrandID());
		obj.put("measure", p.getMeasurement());
		obj.put("size", p.getSize());
		return obj;
	}

	public static JSONObject toJSON(ProductCategory p) throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("type", "productCategory");
		obj.put("id", p.getID());
		obj.put("productId", p.getProductID());
		obj.put("categoryId", p.getCategoryID());
		return obj;
	}

}
