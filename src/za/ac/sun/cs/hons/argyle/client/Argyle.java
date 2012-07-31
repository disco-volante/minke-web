package za.ac.sun.cs.hons.argyle.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.gui.WebPage;
import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.argyle.client.util.GuiUtils;
import za.ac.sun.cs.hons.argyle.client.util.Utils;

import com.google.gwt.core.client.EntryPoint;

/**
 * {@link EntryPoint} class for the project. Binds the different facets
 * together.
 * 
 * @author godfried
 * 
 */
public class Argyle implements EntryPoint {

    public SystemData data;
    private RPC rpcs;
    private WebPage webPage;
    private GPSCoords coords;

    /**
     * Loads and initialises project.
     */
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
	getCities();
	getProductCategories();
	getProducts();
    }

    /**
     * Gets {@link City} objects to add to the interface.
     */
    public void getCities() {
	if (data.getCities() != null && !data.getCities().isEmpty()) {
	    displayCities(null);
	} else {
	    rpcs.getCities();
	}
    }

    /**
     * Gets {@link ProductCategory} objects to add to the interface.
     */
    public void getProductCategories() {
	if (data.getProductCategories() != null
		&& !data.getProductCategories().isEmpty()) {
	    displayProductCategories(null);
	} else {
	    rpcs.getProductCategories();
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
    public void displayProductCategories(HashMap<String, ProductCategory> result) {
	if (result != null) {
	    data.setProductCategories(result);
	}
	getWebPage().addProductCategories(data.getProductCategories());
	Utils.incLoaded();
    }

    /**
     * Displays {@link Product} objects on the {@link WebPage}.
     * 
     * @param result
     *            the objects to be displayed
     */
    public void displayProducts(HashMap<String, Product> result) {
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
    public void displayBranchProducts(HashSet<BranchProduct> result) {
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
    public void displayBranches(HashMap<Branch, HashSet<BranchProduct>> result) {
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
    public void displayCities(HashMap<String, City> result) {
	if (result != null) {
	    data.setCities(result);
	}
	getWebPage().addCities(data.getCities());
	Utils.incLoaded();
    }

    public void displayHistories(
	    HashMap<BranchProduct, HashSet<DatePrice>> priceHiistories) {
	webPage.addHistories(priceHiistories);
    }

    /**
     * Requests {@link BranchProduct}'s from the db.
     * 
     * @param cityID
     *            the ID of the {@link City} wherein the {@link BranchProduct}s
     *            are located.
     * @param productCategoryID
     *            the ID of the {@link ProductCategory} of the
     *            {@link BranchProduct}s.
     */
    public void requestBranchProducts(long cityID, long productCategoryID) {
	rpcs.getBranchProducts(productCategoryID, cityID);
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

    public void setLocation(GPSCoords gpsCoords) {
	coords = gpsCoords; 
    }

    public GPSCoords getCoords() {
	return coords;
    }

}
