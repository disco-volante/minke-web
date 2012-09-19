package za.ac.sun.cs.hons.minke.client.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import za.ac.sun.cs.hons.minke.client.WebSystem;
import za.ac.sun.cs.hons.minke.client.gui.graph.HistoryGraph;
import za.ac.sun.cs.hons.minke.client.gui.map.BranchLocation;
import za.ac.sun.cs.hons.minke.client.gui.popup.LocationPopup;
import za.ac.sun.cs.hons.minke.client.gui.popup.ProductDetail;
import za.ac.sun.cs.hons.minke.client.gui.popup.ProductSearch;
import za.ac.sun.cs.hons.minke.client.gui.popup.ShoppingListDetailPopup;
import za.ac.sun.cs.hons.minke.client.gui.popup.shoppinglist.ShoppingList;
import za.ac.sun.cs.hons.minke.client.gui.table.BranchList;
import za.ac.sun.cs.hons.minke.client.gui.table.ProductList;
import za.ac.sun.cs.hons.minke.client.gui.table.TableView;
import za.ac.sun.cs.hons.minke.client.gui.table.TableView.TABLE;
import za.ac.sun.cs.hons.minke.client.serialization.GPSArea;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.util.CSSUtils;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * The {@link WebSystem}'s GUI front-end.
 * 
 * @author godfried
 * 
 */
public class WebPage {
	/**
	 * {@link UiBinder} interface for this class.
	 * 
	 * @author godfried
	 * 
	 */
	interface Binder extends UiBinder<DockLayoutPanel, WebPage> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided = true)
	ProductList productList;
	@UiField(provided = true)
	BranchList storeList;
	@UiField(provided = true)
	BranchLocation storeLocation;
	@UiField
	HistoryGraph historyGraph;
	@UiField(provided = true)
	ViewTree views;
	@UiField
	DeckPanel deck;
	private DockLayoutPanel outer;
	private ProductDetail details;
	private ShoppingListDetailPopup listDetails;
	private ProductSearch search;
	private LocationPopup locationPopup;
	private ShoppingList shopping;
	public WebSystem system;

	/**
	 * Creates a new {@link WebPage} and initialises its components.
	 * 
	 * @param jericho
	 */
	public WebPage(WebSystem system) {
		setWebSystem(system);
		buildPage();
	}

	/**
	 * Setter for the {@link WebSystem}.
	 * 
	 * @param system
	 *            this class's new {@link WebSystem}.
	 */
	public void setWebSystem(WebSystem system) {
		this.system = system;
	}

	/**
	 * Getter for the {@link WebSystem}.
	 * 
	 * @return this class's {@link WebSystem}.
	 */
	public WebSystem getWebSystem() {
		return system;
	}

	/**
	 * Creates the a new {@link WebPage} and initialises its components.
	 */
	protected void buildPage() {
		CSSUtils.addCSS();
		productList = new ProductList(this);
		storeList = new BranchList(this);
		storeLocation = new BranchLocation(this);
		views = new ViewTree(this);
		outer = binder.createAndBindUi(this);
		details = new ProductDetail();
		listDetails = new ShoppingListDetailPopup(this);
		search = new ProductSearch(this);
		locationPopup = new LocationPopup(this);
		shopping = new ShoppingList(this);
		deck.showWidget(0);
		Window.setMargin("0px"); 
		RootLayoutPanel.get().add(outer);
	}

	/**
	 * Processes the various {@link TableView} objects.
	 * 
	 * @param table
	 *            the type of {@link TableView} being processed.
	 */
	public void process(TABLE table) {
		switch (table) {
		case DEFAULT:
			break;
		case BROWSE:
			search.center();
			break;
		case STORE:
			shopping.center();
			break;
		case SHOPPING:
			break;
		}
		;

	}

	/**
	 * Displays a graph of {@link BranchProduct} price histories.
	 * 
	 * @param itemSet
	 * @param tableType
	 */
	@SuppressWarnings("unchecked")
	public void requestGraph(Set<?> itemSet, TABLE tableType) {
		if (tableType.equals(TABLE.BROWSE) || tableType.equals(TABLE.SHOPPING)) {
			Set<BranchProduct> branchProducts = (Set<BranchProduct>) itemSet;
			system.requestHistories(branchProducts);
			GuiUtils.showLoader();
		}
	}

	/**
	 * Requests {@link Branch}es and shows the {@link BranchList} interface.
	 * 
	 * @param addedProducts
	 *            the {@link Product}s which each {@link Branch} must stock.
	 */
	public void requestShopping(HashMap<Long, Integer> addedProducts) {
		system.requestBranches(addedProducts);
		GuiUtils.showLoader();
	}

	/**
	 * Requests {@link BranchProduct}s and shows the {@link ProductList}
	 * interface.
	 * 
	 * @param cityID
	 *            the {@link City} in which the {@link BranchProduct}s must be
	 *            stocked.
	 * @param productCategoryID
	 *            the {@link ProductCategory} which the {@link BranchProduct}s
	 *            must have.
	 */
	public void requestBrowsingP(HashMap<EntityID, HashSet<Long>> l, HashSet<Long> p) {
		system.requestBranchProductsP(l, p);
		GuiUtils.showLoader();
	}

	public void requestBrowsingC(HashMap<EntityID, HashSet<Long>> l, HashSet<Long> c) {
		system.requestBranchProductsC(l, c);
		GuiUtils.showLoader();
	}
	public void requestMap(Long locID) {
		system.requestLocation(locID);
		GuiUtils.showLoader();		
	}
	public void showBrowsing() {
		views.setSelected("browsing");
		deck.showWidget(0);
	}

	public void showShopping() {
		views.setSelected("shopping");
		deck.showWidget(1);
	}

	/**
	 * Shows the {@link BranchLocation} interface.
	 */
	public void showMap() {
		views.setSelected("map");
		deck.showWidget(2);
	}

	public void showGraph() {
		views.setSelected("graph");
		listDetails.hide();
		deck.showWidget(3);
	}

	/**
	 * Shows the {@link BranchLocation} interface with directions.
	 * 
	 * @param gpsCoords
	 *            the {@link Location} the user wants to get directions to.
	 */
	public void showMap(GPSArea gpsCoords) {
		system.setDestCoords(gpsCoords);
		if (system.getUserCoords() == null) {
			locationPopup.center();
		} else {
			storeLocation.setMapCenter(gpsCoords);
			storeLocation.setDirectionCoords(system.getUserCoords(), gpsCoords);
			storeLocation.draw();
			showMap();
		}
	}

	/**
	 * Shows a popup containing a {@link BranchProduct}'s details.
	 * 
	 * @param item
	 *            The {@link BranchProduct}.
	 */
	public void showProductDetails(BranchProduct item) {
		details.setItem(item);
		details.center();
	}

	/**
	 * Shows a popup containing details about an item on the {@link BranchList}
	 * interface.
	 * 
	 * @param items
	 *            The {@link BranchProduct}s in the shopping list.
	 * @param products
	 *            The {@link Product}s and their quantities in the shopping
	 *            list.
	 */
	public void showListDetails(HashSet<BranchProduct> items,
			HashMap<Long, Integer> products) {
		listDetails.getShoppingListDetail().setProducts(products);
		listDetails.getShoppingListDetail().setItemSet(items);
		listDetails.center();
	}

	/**
	 * Adds {@link ProductCategory} objects to the {@link #search} interface.
	 * 
	 * @param productCategories
	 */
	public void addCategories(EntityNameMap categories) {
		search.addCategories(categories);
	}

	/**
	 * Adds {@link City} objects to the {@link #search} interface.
	 * @param entityNameMap3 
	 * @param entityNameMap2 
	 * 
	 * @param cities
	 */
	public void addCities(EntityNameMap cities) {
		search.addCities(cities);
		locationPopup.addLocations(cities);
	}
	
	/**
	 * Adds {@link City} objects to the {@link #search} interface.
	 * @param entityNameMap3 
	 * @param entityNameMap2 
	 * 
	 * @param cities
	 */
	public void addProvinces(EntityNameMap provinces) {
		search.addProvinces(provinces);
	}
	
	/**
	 * Adds {@link City} objects to the {@link #search} interface.
	 * @param entityNameMap3 
	 * @param entityNameMap2 
	 * 
	 * @param cities
	 */
	public void addCountries(EntityNameMap countries) {
		search.addCountries(countries);
	}

	/**
	 * Adds {@link Product} objects to the {@link #shopping} interface.
	 * 
	 * @param products
	 */
	public void addProducts(EntityNameMap products) {
		search.addProducts(products);
		shopping.addProducts(products);
	}

	/**
	 * Adds {@link BranchProduct} objects to the {@link #productList} interface.
	 * 
	 * @param hashMap
	 */
	public void addBranchProducts(HashMap<BranchProduct, List<DatePrice>> hashMap) {
		TreeSet<BranchProduct> sorted = new TreeSet<BranchProduct>();
		sorted.addAll(hashMap.keySet());
		productList.setItemSet(sorted);
		showBrowsing();
		GuiUtils.hideLoader();
	}

	/**
	 * Adds {@link Branch} and {@link BranchProduct} objects as well as
	 * {@link Product} identifiers to the {@link #storeList} interface.
	 * 
	 * @param hashMap
	 * @param products
	 */
	public void addBranches(HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> hashMap,
			HashMap<Long, Integer> products) {
		storeList.addProducts(products);
		storeList.setItemSet(hashMap.entrySet());
		showShopping();
		GuiUtils.hideLoader();
	}

	public void addHistories(
			HashMap<BranchProduct, List<DatePrice>> priceHistories) {
		try {
			historyGraph.addHistories(priceHistories);
			showGraph();
		} catch (Exception e) {
			e.printStackTrace();
		}
		GuiUtils.hideLoader();
	}



}
