package za.ac.sun.cs.hons.minke.client;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

/**
 * This class is used to store data obtained from RPC calls.
 * 
 * @author godfried
 * 
 */
public class SystemData {
	private EntityNameMap categories, cities, provinces, countries, products;
	private HashMap<BranchProduct, List<DatePrice>> branchProducts;
	private HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> branches;
	public static Logger log = Logger.getLogger("SERVER");

	private boolean loaded;
	private HashMap<Long, Integer> addedProducts;

	public EntityNameMap getCategories() {
		return categories;
	}

	public void setCategories(EntityNameMap categories) {
		this.categories = categories;
	}

	public EntityNameMap getCities() {
		return cities;
	}

	public EntityNameMap getCountries() {
		return countries;
	}

	public EntityNameMap getProvinces() {
		return provinces;
	}

	public void setCities(EntityNameMap result) {
		this.cities = result;
	}

	public void setProvinces(EntityNameMap result) {
		this.provinces = result;
	}

	public void setCountries(EntityNameMap result) {
		this.countries = result;
	}

	public EntityNameMap getProducts() {
		return products;
	}

	public void setProducts(EntityNameMap products) {
		this.products = products;
	}

	public boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public HashMap<BranchProduct, List<DatePrice>> getBranchProducts() {
		return branchProducts;
	}

	public void setBranchProducts(HashMap<BranchProduct, List<DatePrice>> result) {
		this.branchProducts = result;
	}

	public HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> getBranches() {
		return branches;
	}

	public void setBranches(HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> result) {
		this.branches = result;

	}

	public HashMap<Long, Integer> getAddedProducts() {
		return addedProducts;
	}

	public void setAddedProducts(HashMap<Long, Integer> addedProducts) {
		this.addedProducts = addedProducts;
	}
}