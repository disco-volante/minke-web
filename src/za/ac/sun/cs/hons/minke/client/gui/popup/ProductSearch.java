package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.HashMap;
import java.util.HashSet;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.removable.DynamicList;
import za.ac.sun.cs.hons.minke.client.gui.removable.SearchItem;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class ProductSearch extends FocusedPopupPanel implements KeyPressHandler{

	interface Binder extends UiBinder<Widget, ProductSearch> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided = true)
	SuggestBox locationBox, searchBox;
	@UiField
	RadioButton productRB, categoryRB;
	@UiField
	DynamicList locationList, searchList;
	private MultiWordSuggestOracle locationOracle, searchOracle;
	private WebPage webPage;
	private EntityNameMap categories, products, cities, provinces, countries;
	private HashMap<String, Long> addedProducts, addedCategories, addedCities,
			addedCountries, addedProvinces;

	public ProductSearch(WebPage webPage) {
		super(false);
		initSuggestBoxes();
		this.webPage = webPage;
		add(binder.createAndBindUi(this));
		productRB.setValue(true);
		categoryRB.setValue(false);
		productRBClick(null);
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				locationBox.setFocus(true);
			}
		});
		
	}

	private void initSuggestBoxes() {
		searchOracle = new MultiWordSuggestOracle();
		searchBox = new SuggestBox(searchOracle);
		locationOracle = new MultiWordSuggestOracle();
		locationBox = new SuggestBox(locationOracle);
		locationBox.setText("");
		searchBox.setText("");
		categories = new EntityNameMap(EntityID.CATEGORY);
		products = new EntityNameMap(EntityID.CATEGORY);
		cities = new EntityNameMap(EntityID.CATEGORY);
		provinces = new EntityNameMap(EntityID.CATEGORY);
		countries = new EntityNameMap(EntityID.CATEGORY);
	}

	@Override
	public void hide() {
		super.hide();
		if (locationBox != null) {
			locationBox.setText("");
			searchBox.setText("");
			locationList.clear();
			searchList.clear();
		}
	}

	@Override
	public void show() {
		super.show();
		addedCities = new HashMap<String, Long>();
		addedProvinces = new HashMap<String, Long>();
		addedCountries = new HashMap<String, Long>();
		addedProducts = new HashMap<String, Long>();
		addedCategories = new HashMap<String, Long>();
	}

	public void addCities(EntityNameMap cities) {
		this.cities = cities;
		locationOracle.addAll(cities.getNames());
	}

	public void addProvinces(EntityNameMap provinces) {
		this.provinces = provinces;
		locationOracle.addAll(provinces.getNames());
	}

	public void addCountries(EntityNameMap countries) {
		this.countries = countries;
		locationOracle.addAll(countries.getNames());
	}

	public void addCategories(EntityNameMap categories) {
		this.categories = categories;
		if (categoryRB.getValue()) {
			searchOracle.addAll(categories.getNames());
		}
	}

	public void addProducts(EntityNameMap products) {
		this.products = products;
		if (productRB.getValue()) {
			searchOracle.addAll(products.getNames());
		}
	}

	@UiHandler("searchBox")
	void searchPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			addSearch();
		}
	}

	@UiHandler("addSearchButton")
	void searchClick(ClickEvent e) {
		addSearch();
	}

	private void addSearch() {
		String search;
		if (!(search = searchBox.getText()).equals("")) {
			if (products.contains(search) && !addedProducts.containsKey(search)) {
				addedProducts.put(search, products.getID(search));
			} else if (categories.contains(search)
					&& !addedCategories.containsKey(search)) {
				addedCategories.put(search, categories.getID(search));
			} else {
				searchBox.setText("");
				return;
			}
			searchList.addItem(new SearchItem(searchList, searchBox.getText(),
					this));
			searchBox.setText("");
		}

	}

	@UiHandler("locationBox")
	void locationPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			addLocation();
		}
	}

	@UiHandler("addLocationButton")
	void locationClick(ClickEvent e) {
		addLocation();
	}

	private void addLocation() {
		String loc;
		if (!(loc = locationBox.getText()).equals("")) {
			if (!addedCities.containsKey(loc) && cities.contains(loc)) {
				addedCities.put(loc, cities.getID(loc));
			} else if (!addedProvinces.containsKey(loc)
					&& provinces.contains(loc)) {
				addedProvinces.put(loc, provinces.getID(loc));
			} else if (!addedCountries.containsKey(loc)
					&& countries.contains(loc)) {
				addedCountries.put(loc, countries.getID(loc));
			} else {
				locationBox.setText("");
				return;
			}
			locationList.addItem(new SearchItem(locationList, locationBox
					.getText(), this));
			locationBox.setText("");
		}
	}

	@UiHandler("productRB")
	void productRBClick(ClickEvent event) {
		addedProducts = new HashMap<String, Long>();
		searchList.clear();
		searchOracle.clear();
		if (products != null) {
			searchOracle.addAll(products.getNames());
		}
		searchBox.setText("");
	}

	@UiHandler("categoryRB")
	void categoryRBClick(ClickEvent event) {
		addedCategories = new HashMap<String, Long>();
		searchList.clear();
		searchOracle.clear();
		if (categories != null) {
			searchOracle.addAll(categories.getNames());
		}
		searchBox.setText("");
	}

	@UiHandler("productButton")
	void handleClick(ClickEvent e) {
		HashMap<EntityID, HashSet<Long>> l = new HashMap<EntityID, HashSet<Long>>();
		l.put(EntityID.CITY, new HashSet<Long>(addedCities.values()));
		l.put(EntityID.PROVINCE, new HashSet<Long>(addedProvinces.values()));
		l.put(EntityID.COUNTRY, new HashSet<Long>(addedCountries.values()));
		if (productRB.getValue()) {
			HashSet<Long> p = new HashSet<Long>();
			p.addAll(addedProducts.values());
			webPage.requestBrowsingP(l, p);
		} else if (categoryRB.getValue()) {
			HashSet<Long> c = new HashSet<Long>();
			c.addAll(addedCategories.values());
			webPage.requestBrowsingC(l, c);
		}
		hide();
	}


	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	public void removeItem(SearchItem searchItem) {
		String name = searchItem.getName();
		if (products.contains(name)) {
			addedProducts.remove(name);
		} else if (categories.contains(name)) {
			addedCategories.remove(name);
		} else if (cities.contains(name)) {
			addedCities.remove(name);
		} else if (provinces.contains(name)) {
			addedProvinces.remove(name);
		} else if (countries.contains(name)) {
			addedCountries.remove(name);

		}
	}

	@Override
	public void onKeyPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			handleClick(null);
		}	else if(kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE){
			cancelClicked(null);
		}	
	}

}
