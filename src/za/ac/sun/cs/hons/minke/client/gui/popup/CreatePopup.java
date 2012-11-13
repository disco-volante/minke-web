package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;

import za.ac.sun.cs.hons.minke.client.SystemData;
import za.ac.sun.cs.hons.minke.client.gui.table.DataViewer;
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
import za.ac.sun.cs.hons.minke.client.util.REGEX;

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

public class CreatePopup extends FocusedPopupPanel implements KeyPressHandler {

	interface Binder extends UiBinder<Widget, CreatePopup> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Button createButton;
	@UiField
	FlowPanel holder;
	private DataViewer viewer;
	private String entity;
	private HashMap<String, TextBox> texts;
	private HashMap<String, SuggestBox> autoTexts;
	private DateBox dateBox;
	private StringBuilder errors;

	public CreatePopup(DataViewer _viewer, String entity) {
		super(true);
		add(binder.createAndBindUi(this));
		this.viewer = _viewer;
		this.entity = entity;
		createUi();
	}

	private void createUi() {
		if (entity.equals(Constants.CATEGORY) || entity.equals(Constants.BRAND)
				|| entity.equals(Constants.STORE)
				|| entity.equals(Constants.COUNTRY)) {
			createSimpleUi();
		} else if (entity.equals(Constants.PRODUCT)) {
			createProductUi();
		} else if (entity.equals(Constants.BRANCHPRODUCT)) {
			createBranchProductUi();
		} else if (entity.equals(Constants.BRANCH)) {
			createBranchUi();
		} else if (entity.equals(Constants.CITYLOCATION)) {
			createCityLocationUi();
		} else if (entity.equals(Constants.CITY)) {
			createCityUi();
		} else if (entity.equals(Constants.PROVINCE)) {
			createProvinceUi();
		}
	}

	private void createProvinceUi() {
		addBasicRow(Constants.NAME);
		addSuggestRow(Constants.COUNTRY);
	}

	private void createCityUi() {
		addBasicRow(Constants.NAME);
		addSuggestRow(Constants.PROVINCE);
		addBasicRow(Constants.LAT);
		addBasicRow(Constants.LON);
	}

	private void createCityLocationUi() {
		addBasicRow(Constants.NAME);
		addSuggestRow(Constants.CITY);
		addBasicRow(Constants.LAT);
		addBasicRow(Constants.LON);
	}

	private void createBranchUi() {
		addBasicRow(Constants.NAME);
		addSuggestRow(Constants.STORE);
		addSuggestRow(Constants.CITYLOCATION);
	}

	private void createBranchProductUi() {
		addSuggestRow(Constants.PRODUCT);
		addSuggestRow(Constants.BRANCH);
		addDateRow();
		addBasicRow(Constants.PRICE);

	}

	private void createProductUi() {
		addBasicRow(Constants.NAME);
		addSuggestRow(Constants.BRAND);
		addBasicRow(Constants.SIZE);
		addBasicRow(Constants.MEASURE);
	}

	private void createSimpleUi() {
		addBasicRow(Constants.NAME);
	}

	private void addSuggestRow(String ident) {
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

	private void addBasicRow(String ident) {
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

	private void addDateRow() {
		HorizontalPanel row = new HorizontalPanel();
		row.setWidth("400px");
		row.addStyleName("paddedHorizontalPanel");
		Label lbl = new Label(Constants.DATE);
		dateBox = new DateBox();
		dateBox.setValue(new Date());
		row.add(lbl);
		row.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		row.add(dateBox);
		holder.add(row);
	}

	private void createCountry() {
		String name = texts.get(Constants.NAME).getText();
		if (name != null) {
			viewer.create(new Country(name));
		}
	}

	private void createProvince() {
		String name = texts.get(Constants.NAME).getText();
		Country country = (Country) viewer.countries.get(autoTexts.get(
				Constants.COUNTRY).getText());
		if (name != null && country != null) {
			viewer.create(new Province(name, country));
		}
	}

	private void createCity() {
		String name = texts.get(Constants.NAME).getText();
		double lat = Double.parseDouble(texts.get(Constants.LAT).getText());
		double lon = Double.parseDouble(texts.get(Constants.LON).getText());
		Province province = (Province) viewer.provinces.get(autoTexts.get(
				Constants.PROVINCE).getText());
		if (name != null && province != null) {
			viewer.create(new City(name, province, lat, lon));
		}
	}

	private void createCityLocation() {
		String name = texts.get(Constants.NAME).getText();
		double lat = Double.parseDouble(texts.get(Constants.LAT).getText());
		double lon = Double.parseDouble(texts.get(Constants.LON).getText());
		City city = (City) viewer.cities.get(autoTexts.get(Constants.CITY)
				.getText());
		if (name != null && city != null) {
			viewer.create(new CityLocation(name, city, lat, lon));
		}
	}

	private void createStore() {
		String name = texts.get(Constants.NAME).getText();
		if (name != null) {
			viewer.create(new Store(name));
		}
	}

	private void createBranch() {
		String name = texts.get(Constants.NAME).getText();
		Store store = (Store) viewer.stores.get(autoTexts.get(Constants.STORE)
				.getText());
		CityLocation cl = (CityLocation) viewer.locations.get(autoTexts.get(
				Constants.CITYLOCATION).getText());
		if (name != null && store != null && cl != null) {
			viewer.create(new Branch(name, store, cl));
		}
	}

	private void createBrand() {
		String name = texts.get(Constants.NAME).getText();
		if (name != null) {
			viewer.create(new Brand(name));
		}
	}

	private void createProduct() {
		Brand brand = (Brand) viewer.brands.get(autoTexts.get(Constants.BRAND)
				.getText());
		String measure = texts.get(Constants.MEASURE).getText();
		double size = Double.parseDouble(texts.get(Constants.SIZE).getText());

		String name = texts.get(Constants.NAME).getText();
		if (name != null && measure != null && brand != null) {
			viewer.create(new Product(name, brand, size, measure));
		}
	}

	private void createCategory() {
		String name = texts.get(Constants.NAME).getText();
		if (name != null) {
			viewer.create(new Category(name));
		}
	}

	private void createBranchProduct() {
		Branch b = (Branch) viewer.branches.get(autoTexts.get(Constants.BRANCH)
				.getText());
		Product p = (Product) viewer.products.get(autoTexts.get(
				Constants.PRODUCT).getText());
		Date foundDate = dateBox.getValue();
		int foundPrice = (int) (Double.parseDouble(texts.get(Constants.PRICE)
				.getText()) * 100);
		if (b != null && p != null && foundDate != null) {
			viewer.create(new BranchProduct(p, b, new DatePrice(foundDate,
					foundPrice, -1)));
		}
	}

	@UiHandler("createButton")
	void createClicked(ClickEvent event) {
		hide();
		if (!validate()) {
			GuiUtils.showError("Invalid Input",
					"Some fields contain invalid input: " + errors.toString());
			return;
		}
		if (entity.equals(Constants.CATEGORY)) {
			createCategory();
		} else if (entity.equals(Constants.BRAND)) {
			createBrand();
		} else if (entity.equals(Constants.STORE)) {
			createStore();
		} else if (entity.equals(Constants.COUNTRY)) {
			createCountry();
		} else if (entity.equals(Constants.PRODUCT)) {
			createProduct();
		} else if (entity.equals(Constants.BRANCHPRODUCT)) {
			createBranchProduct();
		} else if (entity.equals(Constants.BRANCH)) {
			createBranch();
		} else if (entity.equals(Constants.CITYLOCATION)) {
			createCityLocation();
		} else if (entity.equals(Constants.CITY)) {
			createCity();
		} else if (entity.equals(Constants.PROVINCE)) {
			createProvince();
		}
	}

	private boolean validate() {
		try {
			errors = new StringBuilder();
			if (dateBox != null && dateBox.getValue() == null) {
				errors.append(Constants.DATE + ", ");
			}
			for (Entry<String, SuggestBox> sb : autoTexts.entrySet()) {
				if (!REGEX.STRING.test(sb.getValue().getText())) {
					if (sb.getKey().equals(Constants.BRANCH)
							&& REGEX.STRING.test(sb.getValue().getText()
									.replace('@', ' '))) {
						continue;
					}
					errors.append(sb.getKey() + ", ");
				}
			}
			for (Entry<String, TextBox> entry : texts.entrySet()) {
				String input = entry.getValue().getText();
				String type = entry.getKey();
				if ((type.equals(Constants.SIZE) || type
						.equals(Constants.PRICE))) {
					if (input == null || !(REGEX.DECIMALS_0.test(input))) {
						errors.append(type + ", ");
					}
				} else if (type.equals(Constants.LAT)
						|| type.equals(Constants.LON)) {
					if (input == null || !(REGEX.DECIMALS_1.test(input))) {
						errors.append(type + ", ");
					}
				} else if (!REGEX.STRING.test(input)) {
					errors.append(type + ", ");
				}
				if (errors.length() > 0) {
					errors.delete(errors.length() - 2, errors.length());
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			SystemData.log.log(Level.SEVERE, e.getMessage());
			if (entity != null) {
				SystemData.log.log(Level.SEVERE, "editing " + entity);
			} else {
				SystemData.log.log(Level.SEVERE, "editing null entity");
			}
			return false;
		}
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	@Override
	public void onKeyPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			createClicked(null);
		} else if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			cancelClicked(null);
		}
	}

}
