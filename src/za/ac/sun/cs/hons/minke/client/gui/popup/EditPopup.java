package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

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
import za.ac.sun.cs.hons.minke.client.util.Constants;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;

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

public class EditPopup extends FocusedPopupPanel implements KeyPressHandler {

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
	private DateBox dateBox;
	private StringBuilder errors;

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
		}
	}

	private void createProvinceUi(Province item) {
		addBasicRow(Constants.NAME, item.getName());
		addSuggestRow(Constants.COUNTRY, item.getCountry().toString());
	}

	private void createCityUi(City item) {
		addBasicRow(Constants.NAME, item.getName());
		addSuggestRow(Constants.PROVINCE, item.getProvince().toString());
		addBasicRow(Constants.LAT, String.valueOf(item.getLat()));
		addBasicRow(Constants.LON, String.valueOf(item.getLon()));
	}

	private void createCityLocationUi(CityLocation item) {
		addBasicRow(Constants.NAME, item.getName());
		addSuggestRow(Constants.CITY, item.getCity().toString());
		addBasicRow(Constants.LAT, String.valueOf(item.getLat()));
		addBasicRow(Constants.LON, String.valueOf(item.getLon()));
	}

	private void createBranchUi(Branch item) {
		addBasicRow(Constants.NAME, item.getName());
		addSuggestRow(Constants.STORE, item.getStore().toString());
		addSuggestRow(Constants.CITYLOCATION, item.getLocation().toString());
	}

	private void createBranchProductUi(BranchProduct item) {
		addSuggestRow(Constants.PRODUCT, item.getProduct().toString());
		addSuggestRow(Constants.BRANCH, item.getBranch().toString());
		addDateRow(item.getDatePrice().getDate());
		addBasicRow(Constants.PRICE,
				String.valueOf(item.getDatePrice().getActualPrice()));

	}

	private void createProductUi(Product item) {
		addBasicRow(Constants.NAME, item.getName());
		addSuggestRow(Constants.BRAND, item.getBrand().toString());
		addBasicRow(Constants.SIZE, String.valueOf(item.getSize()));
		addBasicRow(Constants.MEASURE, item.getMeasurement());
	}

	private void createSimpleUi(String name) {
		addBasicRow(Constants.NAME, name);
	}

	private void addSuggestRow(String ident, String contents) {
		if (autoTexts == null) {
			autoTexts = new HashMap<String, SuggestBox>();
		}
		HorizontalPanel row = new HorizontalPanel();
		row.setWidth("400px");
		row.addStyleName("paddedHorizontalPanel");
		Label lbl = new Label(ident);
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		oracle.addAll(viewer.getSupportEntities(ident));
		final SuggestBox suggest = new SuggestBox(oracle);
		suggest.setText(contents);
		autoTexts.put(ident, suggest);
		row.add(suggest);
		holder.add(row);
		if (autoTexts.size() == 1 && texts == null) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				public void execute() {
					suggest.setFocus(true);
				}
			});
		}
	}

	private void addBasicRow(String ident, String contents) {
		if (texts == null) {
			texts = new HashMap<String, TextBox>();
		}
		HorizontalPanel row = new HorizontalPanel();
		row.setWidth("400px");
		row.addStyleName("paddedHorizontalPanel");
		Label lbl = new Label(ident);
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		final TextBox text = new TextBox();
		text.setText(contents);
		texts.put(ident, text);
		row.add(text);
		holder.add(row);
		if (texts.size() == 1 && autoTexts == null) {
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				public void execute() {
					text.setFocus(true);
				}
			});
		}
	}

	private void addDateRow(Date date) {
		HorizontalPanel row = new HorizontalPanel();
		row.setWidth("400px");
		row.addStyleName("paddedHorizontalPanel");
		Label lbl = new Label(Constants.DATE);
		dateBox = new DateBox();
		dateBox.setValue(date);
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		row.add(dateBox);
		holder.add(row);
	}

	private void editCountry(Country country) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(country.getName())) {
			country.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.update(country);
		}
	}

	private void editProvince(Province province) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(province.getName())) {
			province.setName(name);
			changed = true;
		}
		Country country = (Country) viewer.countries.get(autoTexts.get(
				Constants.COUNTRY).getText());
		if (country != null && province.getCountryID() != country.getID()) {
			province.setCountry(country);
			changed = true;
		}
		if (changed) {
			viewer.update(province);
		}
	}

	private void editCity(City city) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(city.getName())) {
			city.setName(name);
			changed = true;
		}
		double lat = Double.parseDouble(texts.get(Constants.LAT).getText());
		if (city.getLat() != lat) {
			city.setLat(lat);
			changed = true;
		}
		double lon = Double.parseDouble(texts.get(Constants.LON).getText());
		if (city.getLon() != lon) {
			city.setLon(lon);
			changed = true;
		}
		Province province = (Province) viewer.provinces.get(autoTexts.get(
				Constants.PROVINCE).getText());
		if (province != null && city.getProvinceID() != province.getID()) {
			city.setProvince(province);
			changed = true;
		}
		if (changed) {
			viewer.update(city);
		}
	}

	private void editCityLocation(CityLocation cl) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(cl.getName())) {
			cl.setName(name);
			changed = true;
		}
		double lat = Double.parseDouble(texts.get(Constants.LAT).getText());
		if (cl.getLat() != lat) {
			cl.setLat(lat);
			changed = true;
		}
		double lon = Double.parseDouble(texts.get(Constants.LON).getText());
		if (cl.getLon() != lon) {
			cl.setLon(lon);
			changed = true;
		}
		City city = (City) viewer.cities.get(autoTexts.get(Constants.CITY)
				.getText());
		if (city != null && cl.getCityID() != city.getID()) {
			cl.setCity(city);
			changed = true;
		}
		if (changed) {
			viewer.update(cl);
		}
	}

	private void editStore(Store store) {

		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(store.getName())) {
			store.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.update(store);
		}
	}

	private void editBranch(Branch branch) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(branch.getName())) {
			branch.setName(name);
			changed = true;
		}
		Store store = (Store) viewer.stores.get(autoTexts.get(Constants.STORE)
				.getText());
		if (store != null && branch.getStoreID() != store.getID()) {
			branch.setStore(store);
			changed = true;
		}
		CityLocation cl = (CityLocation) viewer.locations.get(autoTexts.get(
				Constants.CITYLOCATION).getText());
		if (cl != null && branch.getLocationID() != cl.getID()) {
			branch.setLocation(cl);
			changed = true;
		}
		if (changed) {
			viewer.update(branch);
		}
	}

	private void editBrand(Brand brand) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(brand.getName())) {
			brand.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.update(brand);
		}
	}

	private void editProduct(Product product) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(product.getName())) {
			product.setName(name);
			changed = true;
		}
		String measure = texts.get(Constants.MEASURE).getText();
		if (measure != null && measure.equals(product.getMeasurement())) {
			product.setMeasurement(measure);
			changed = true;
		}
		double size = Double.parseDouble(texts.get(Constants.SIZE).getText());
		if (product.getSize() != size) {
			product.setSize(size);
			changed = true;
		}
		Brand brand = (Brand) viewer.brands.get(autoTexts.get(Constants.BRAND)
				.getText());
		if (brand != null && product.getBrandID() != brand.getID()) {
			product.setBrand(brand);
			changed = true;
		}
		if (changed) {
			viewer.update(product);
		}
	}

	private void editCategory(Category category) {
		boolean changed = false;
		String name = texts.get(Constants.NAME).getText();
		if (name != null && !name.equals(category.getName())) {
			category.setName(name);
			changed = true;
		}
		if (changed) {
			viewer.update(category);
		}
	}

	private void editBranchProduct(BranchProduct bp) {
		boolean changed = false;
		Branch b = (Branch) viewer.branches.get(autoTexts.get(Constants.BRANCH)
				.getText());
		if (b != null && b.getID() != bp.getBranchID()) {
			bp.setBranch(b);
			changed = true;
		}
		Product p = (Product) viewer.products.get(autoTexts.get(
				Constants.PRODUCT).getText());
		if (p != null && p.getID() != bp.getProductID()) {
			bp.setProduct(p);
			changed = true;
		}
		Date currentDate = bp.getDatePrice().getDate();
		Date foundDate = dateBox.getValue();
		int foundPrice = (int) (Double.parseDouble(texts.get(Constants.PRICE)
				.getText()) * 100);
		if (foundDate != null && !foundDate.equals(currentDate)) {
			bp.setDatePrice(new DatePrice(foundDate, foundPrice, bp.getID()));
			changed = true;
		} else if (foundPrice != bp.getDatePrice().getPrice()) {
			bp.setDatePrice(new DatePrice(currentDate, foundPrice, bp.getID()));
			changed = true;
		}
		if (changed) {
			viewer.update(bp);
		}
	}

	private boolean validate() {
		errors = new StringBuilder();
		if (dateBox != null && dateBox.getValue() == null) {
			errors.append(Constants.DATE + ", ");
		}
		for (Entry<String, SuggestBox> sb : autoTexts.entrySet()) {
			if (!sb.getValue().getText().matches(Constants.STRING)) {
				if (sb.getKey().equals(Constants.BRANCH)
						&& sb.getValue().getText().replace('@', ' ')
								.matches(Constants.STRING)) {
					continue;
				}
				errors.append(sb.getKey() + ", ");
			}
		}
		for (Entry<String, TextBox> entry : texts.entrySet()) {
			String input = entry.getValue().getText();
			String type = entry.getKey();
			if ((type.equals(Constants.SIZE) || type.equals(Constants.PRICE)
					|| type.equals(Constants.LAT) || type.equals(Constants.LON))) {
				if (!(input.matches(Constants.DECIMALS_0)
						|| input.matches(Constants.DECIMALS_0) || input
							.matches(Constants.DECIMALS_0))) {
					errors.append(type + ", ");
				}
			} else if (!input.matches(Constants.STRING)) {
				errors.append(type + ", ");
			}
			if (errors.length() > 0) {
				errors.delete(errors.length() - 2, errors.length());
				return false;
			}
		}
		return true;
	}

	@UiHandler("editButton")
	void editClicked(ClickEvent event) {
		hide();
		if (!validate()) {
			GuiUtils.showError("Invalid Input",
					"Some fields contain invalid input: " + errors.toString());
			return;
		}
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

	@UiHandler("deleteButton")
	void deleteClicked(ClickEvent event) {
		hide();
		viewer.delete(item);
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	@Override
	public void onKeyPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			editClicked(null);
		} else if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			cancelClicked(null);
		}
	}

}
