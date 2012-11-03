package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.Date;
import java.util.HashMap;

import za.ac.sun.cs.hons.minke.client.gui.table.DataViewer;
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
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class EditPopup extends FocusedPopupPanel {

	interface Binder extends UiBinder<Widget, EditPopup> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Button editButton;
	@UiField
	FlowPanel holder;
	private DataViewer viewer;
	private IsEntity item;
	private HashMap<String, TextBox> texts;
	private HashMap<String, SuggestBox> autoTexts;
	private DateBox db;

	public EditPopup(DataViewer _viewer, IsEntity item) {
		super(true);
		add(binder.createAndBindUi(this));
		this.viewer = _viewer;
		this.item = item;
		createUi();
	}

	private void createUi() {
		if (item instanceof Category) {
			createSimpleUi(((Category) item).getName());
		} else if (item instanceof Product) {
			createProductUi((Product) item);
		} else if (item instanceof Brand) {
			createSimpleUi(((Brand) item).getName());
		} else if (item instanceof BranchProduct) {
			createBranchProductUi((BranchProduct) item);
		} else if (item instanceof Branch) {
			createBranchUi((Branch) item);
		} else if (item instanceof Store) {
			createSimpleUi(((Store) item).getName());
		} else if (item instanceof CityLocation) {
			createCityLocationUi((CityLocation) item);
		} else if (item instanceof City) {
			createCityUi((City) item);
		} else if (item instanceof Province) {
			createProvinceUi((Province) item);
		} else if (item instanceof Country) {
			createSimpleUi(((Country) item).getName());
		} else if (item instanceof DatePrice) {
			createDatePriceUi((DatePrice) item);
		}
	}

	private void createDatePriceUi(DatePrice item) {
		addBasicRow("Date", item.getDate().toString());
		addBasicRow("Price", String.valueOf(item.getActualPrice()));
	}

	private void createProvinceUi(Province item) {
		addBasicRow("Name", item.getName());
		addSuggestRow("Country", item.getCountry().toString());
	}

	private void createCityUi(City item) {
		addBasicRow("Name", item.getName());
		addSuggestRow("Province", item.getProvince().toString());
		addBasicRow("Latitude", String.valueOf(item.getLat()));
		addBasicRow("Longitude", String.valueOf(item.getLon()));
	}

	private void createCityLocationUi(CityLocation item) {
		addBasicRow("Name", item.getName());
		addSuggestRow("City", item.getCity().toString());
		addBasicRow("Latitude", String.valueOf(item.getLat()));
		addBasicRow("Longitude", String.valueOf(item.getLon()));
	}

	private void createBranchUi(Branch item) {
		addBasicRow("Name", item.getName());
		addSuggestRow("Store", item.getStore().toString());
		addSuggestRow("Location", item.getLocation().toString());
	}

	private void createBranchProductUi(BranchProduct item) {
		addSuggestRow("Product", item.getProduct().toString());
		addSuggestRow("Branch", item.getBranch().toString());
		addDateRow(item.getDatePrice().getDate());
		addBasicRow("Price",
				String.valueOf(item.getDatePrice().getActualPrice()));

	}

	private void createProductUi(Product item) {
		addBasicRow("Name", item.getName());
		addSuggestRow("Brand", item.getBrand().toString());
		addBasicRow("Size", String.valueOf(item.getSize()));
		addBasicRow("Measure", item.getMeasurement());
	}

	private void createSimpleUi(String name) {
		addBasicRow("Name", name);
	}

	private void addSuggestRow(String ident, String contents) {
		if (autoTexts == null) {
			autoTexts = new HashMap<String, SuggestBox>();
		}
		HorizontalPanel row = new HorizontalPanel();
		Label lbl = new Label(ident);
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		oracle.addAll(viewer.getSupportEntities(ident));
		SuggestBox suggest = new SuggestBox(oracle);
		suggest.setText(contents);
		autoTexts.put(ident, suggest);
		row.add(suggest);
		holder.add(row);
	}

	private void addBasicRow(String ident, String contents) {
		if (texts == null) {
			texts = new HashMap<String, TextBox>();
		}
		HorizontalPanel row = new HorizontalPanel();
		row.addStyleName("paddedHorizontalPanel");
		Label lbl = new Label(ident);
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		TextBox text = new TextBox();
		text.setText(contents);
		texts.put(ident, text);
		row.add(text);
		holder.add(row);
	}

	private void addDateRow(Date date) {
		HorizontalPanel row = new HorizontalPanel();
		row.addStyleName("paddedHorizontalPanel");
		Label lbl = new Label("Date");
		db = new DateBox();
		db.setValue(date);
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		row.add(db);
		holder.add(row);
	}

	@UiHandler("editButton")
	void editClicked(ClickEvent event) {
		hide();
		if (item instanceof Category) {
			editCategory((Category) item);
		} else if (item instanceof Product) {
			editProduct((Product) item);
		} else if (item instanceof Brand) {
			editBrand((Brand) item);
		} else if (item instanceof BranchProduct) {
			editBranchProduct((BranchProduct) item);
		} else if (item instanceof Branch) {
			editBranch((Branch) item);
		} else if (item instanceof Store) {
			editStore((Store) item);
		} else if (item instanceof CityLocation) {
			editCityLocation((CityLocation) item);
		} else if (item instanceof City) {
			editCity((City) item);
		} else if (item instanceof Province) {
			editProvince((Province) item);
		} else if (item instanceof Country) {
			editCountry((Country) item);
		}
	}

	private void editCountry(Country country) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(country.getName())) {
			country.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.change(country);
		}
	}

	private void editProvince(Province province) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(province.getName())) {
			province.setName(name);
			changed = true;
		}
		Country country = (Country) viewer.countries.get(autoTexts.get(
				"Country").toString());
		if (province.getCountryID() != country.getID()) {
			province.setCountry(country);
			changed = true;
		}
		if (changed) {
			viewer.change(province);
		}
	}

	private void editCity(City city) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(city.getName())) {
			city.setName(name);
			changed = true;
		}
		double lat = Double.parseDouble(texts.get("Latitude").getText());
		if (city.getLat() != lat) {
			city.setLat(lat);
			changed = true;
		}
		double lon = Double.parseDouble(texts.get("Longitude").getText());
		if (city.getLon() != lon) {
			city.setLon(lon);
			changed = true;
		}
		Province province = (Province) viewer.provinces.get(autoTexts.get(
				"Province").toString());
		if (city.getProvinceID() != province.getID()) {
			city.setProvince(province);
			changed = true;
		}
		if (changed) {
			viewer.change(city);
		}
	}

	private void editCityLocation(CityLocation cl) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(cl.getName())) {
			cl.setName(name);
			changed = true;
		}
		double lat = Double.parseDouble(texts.get("Latitude").getText());
		if (cl.getLat() != lat) {
			cl.setLat(lat);
			changed = true;
		}
		double lon = Double.parseDouble(texts.get("Longitude").getText());
		if (cl.getLon() != lon) {
			cl.setLon(lon);
			changed = true;
		}
		City city = (City) viewer.cities.get(autoTexts.get("City").toString());
		if (cl.getCityID() != city.getID()) {
			cl.setCity(city);
			changed = true;
		}
		if (changed) {
			viewer.change(cl);
		}
	}

	private void editStore(Store store) {

		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(store.getName())) {
			store.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.change(store);
		}
	}

	private void editBranch(Branch branch) {

		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(branch.getName())) {
			branch.setName(name);
			changed = true;
		}
		Store store = (Store) viewer.stores.get(autoTexts.get("Store")
				.toString());
		if (branch.getStoreID() != store.getID()) {
			branch.setStore(store);
			changed = true;
		}
		CityLocation cl = (CityLocation) viewer.locations.get(autoTexts.get(
				"CityLocation").toString());
		if (branch.getLocationID() != cl.getID()) {
			branch.setLocation(cl);
			changed = true;
		}
		if (changed) {
			viewer.change(branch);
		}
	}

	private void editBrand(Brand brand) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(brand.getName())) {
			brand.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.change(brand);
		}
	}

	private void editProduct(Product product) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(product.getName())) {
			product.setName(name);
			changed = true;
		}
		String measure = texts.get("Measure").getText();
		if (measure.equals(product.getMeasurement())) {
			product.setMeasurement(measure);
			changed = true;
		}
		double size = Double.parseDouble(texts.get("Size").getText());
		if (product.getSize() != size) {
			product.setSize(size);
			changed = true;
		}
		Brand brand = (Brand) viewer.brands.get(autoTexts.get("Brand")
				.getText());
		if (product.getBrandID() != brand.getID()) {
			product.setBrand(brand);
			changed = true;
		}
		if (changed) {
			viewer.change(product);
		}
	}

	private void editCategory(Category category) {
		boolean changed = false;
		String name = texts.get("Name").getText();
		if (name.equals(category.getName())) {
			category.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.change(category);
		}
	}

	private void editBranchProduct(BranchProduct bp) {
		boolean changed = false;
		Branch b = (Branch) viewer.branches.get(autoTexts.get("Branch")
				.toString());
		if (b.getID() != bp.getBranchID()) {
			bp.setBranch(b);
			changed = true;
		}
		Product p = (Product) viewer.products.get(autoTexts.get("Product")
				.toString());
		if (p.getID() != bp.getProductID()) {
			bp.setProduct(p);
			changed = true;
		}
		double price = Double.parseDouble(texts.get("Price").getText());
		Date d = db.getValue();
		if (!d.equals(bp.getDatePrice().getDate())
				|| price != bp.getDatePrice().getActualPrice()) {
			bp.setDatePrice(new DatePrice(d, (int) (price * 100), bp.getID()));
			changed = true;
		}
		if (changed) {
			viewer.change(bp);
		}
	}

	@UiHandler("deleteButton")
	void deleteClicked(ClickEvent event) {
		hide();
		viewer.delete(item);
	}

}
