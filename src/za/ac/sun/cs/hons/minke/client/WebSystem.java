package za.ac.sun.cs.hons.minke.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;
import za.ac.sun.cs.hons.minke.client.util.Utils;

import com.google.gwt.core.client.EntryPoint;

/**
 * {@link EntryPoint} class for the project. Binds the different facets
 * together.
 * 
 * @author godfried
 * 
 */
public class WebSystem implements EntryPoint {

	public SystemData data;
	private RPC rpcs;
	private WebPage webPage;
	private double lat_u, lon_u, lat_d, lon_d;

	/**
	 * Loads and initialises project.
	 */
	@Override
	public void onModuleLoad() {
		GuiUtils.init();
		GuiUtils.showLoader();
		data = new SystemData();
		initRPCS();
		setWebPage(new WebPage(this));
	}

	/**
	 * Initialises rpc objects and starts first rpcs.
	 */
	public void initRPCS() {
		rpcs = new RPC(this);
		rpcs.registerClasses();
		rpcs.getUserLocation();
	}

	/**
	 * Gets data always used to display various aspects of the interface.
	 */
	protected void getDisplayData() {
		getLocations();
		getProductCategories();
		getProducts();
	}

	/**
	 * Gets {@link City} objects to add to the interface.
	 */
	public void getLocations() {
		rpcs.getLocations();
	}

	/**
	 * Gets {@link ProductCategory} objects to add to the interface.
	 */
	public void getProductCategories() {
		if (data.getCategories() != null && !data.getCategories().isEmpty()) {
			displayCategories(null);
		} else {
			rpcs.getCategories();
		}
	}

	/**
	 * Gets {@link Product} objects to add to the interface.
	 */
	public void getProducts() {
		if (data.getProducts() != null && !data.getProducts().isEmpty()) {
			displayProducts(null);
		} else {
			rpcs.getProducts();
		}
	}

	/**
	 * Displays {@link ProductCategory} objects on the {@link WebPage}.
	 * 
	 * @param result
	 *            the objects to be displayed
	 */
	public void displayCategories(EntityNameMap result) {
		if (result != null) {
			data.setCategories(result);
		}
		getWebPage().addCategories(data.getCategories());
		Utils.incLoaded();
	}

	/**
	 * Displays {@link Product} objects on the {@link WebPage}.
	 * 
	 * @param result
	 *            the objects to be displayed
	 */
	public void displayProducts(EntityNameMap result) {
		if (result != null) {
			data.setProducts(result);
		}
		getWebPage().addProducts(data.getProducts());
		Utils.incLoaded();
	}

	/**
	 * Displays {@link BranchProduct} objects on the {@link WebPage}.
	 * 
	 * @param result
	 *            the objects to be displayed
	 */
	public void displayBranchProducts(
			HashMap<BranchProduct, List<DatePrice>> result) {
		if (result != null) {
			data.setBranchProducts(result);
		}
		getWebPage().addBranchProducts(data.getBranchProducts());

	}

	/**
	 * Displays {@link Branch} objects on the {@link WebPage}.
	 * 
	 * @param result
	 *            the objects to be displayed
	 */
	public void displayBranches(
			HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> result) {
		if (result != null) {
			data.setBranches(result);
		}
		getWebPage().addBranches(data.getBranches(), data.getAddedProducts());
	}

	/**
	 * Displays {@link City} objects on the {@link WebPage}.
	 * 
	 * @param result
	 *            the objects to be displayed
	 */
	public void displayLocations(EntityNameMap result) {
		if (result != null) {
			switch (result.getEntity()) {
			case CITY:
				data.setCities(result);
				getWebPage().addCities(data.getCities());
				break;
			case PROVINCE:
				data.setProvinces(result);
				getWebPage().addProvinces(data.getProvinces());
				break;
			case COUNTRY:
				data.setCountries(result);
				getWebPage().addCountries(data.getCountries());
				break;
			}

		}

		Utils.incLoaded();
	}

	public void displayLocation(Location result) {
		lat_u = result.getLat();
		lon_u = result.getLon();
		webPage.showMap(lat_d, lon_d);
	}

	public void displayHistories(HashMap<BranchProduct, List<DatePrice>> result) {
		webPage.addHistories(result);
	}

	/**
	 * Requests {@link BranchProduct}'s from the db.
	 * 
	 * @param l
	 *            the ID of the {@link City} wherein the {@link BranchProduct}s
	 *            are located.
	 * @param p
	 *            the ID of the {@link ProductCategory} of the
	 *            {@link BranchProduct}s.
	 */
	public void requestBranchProductsP(HashMap<EntityID, HashSet<Long>> l,
			HashSet<Long> p) {
		rpcs.getBranchProductsP(l, p);
	}

	public void requestBranchProductsC(HashMap<EntityID, HashSet<Long>> l,
			HashSet<Long> c) {
		rpcs.getBranchProductsC(l, c);
	}

	/**
	 * Requests {@link Branch}es from the db.
	 * 
	 * @param addedProducts
	 *            the {@link Product}s which must availible at the
	 *            {@link Branch}es.
	 */
	public void requestBranches(HashMap<Long, Integer> addedProducts) {
		rpcs.getBranches(addedProducts);
		data.setAddedProducts(addedProducts);
	}

	public void requestHistories(Set<BranchProduct> branchProducts) {
		rpcs.getHistories(branchProducts);
	}

	public void requestLocation(Long locID) {
		rpcs.getLocation(locID);

	}

	/**
	 * Getter for this {@link Argyle's} {@link WebPage}.
	 * 
	 * @return the {@link WebPage} associated with this class.
	 */
	public WebPage getWebPage() {
		return webPage;
	}

	/**
	 * Setter for this {@link Argyle's} {@link WebPage}.
	 * 
	 * @param webPage
	 *            the new {@link WebPage} for this class.
	 */
	public void setWebPage(WebPage webPage) {
		this.webPage = webPage;
	}

	public double getUserLat() {
		return lat_u;
	}

	public double getUserLon() {
		return lon_u;
	}

	public void setDestCoords(double lat, double lon) {
		lat_d = lat;
		lon_d = lon;
	}

	public void setUserCoords(double lat, double lon) {
		lat_u = lat;
		lon_u = lon;

	}

}
