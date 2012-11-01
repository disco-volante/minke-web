package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.ArrayList;
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

	public EditPopup(DataViewer _viewer, IsEntity item) {
		super(true);
		add(binder.createAndBindUi(this));
		this.viewer = _viewer;
		this.item  = item;
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
		addBasicRow("Country", item.getCountry().toString());
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
		addBasicRow("DatePrice",item.getDatePrice().toString());
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
		if(autoTexts == null){
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
		if(texts == null){
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

	@UiHandler("editButton")
	void editClicked(ClickEvent event) {
		hide();
		if (item instanceof Category) {
			Category c = (Category) item;
			c.setName(texts.get("Name").getText());
		} else if (item instanceof Product) {
			Product p = (Product) item;
			p.setName(texts.get("Name").getText());
			p.setSize(Double.parseDouble(texts.get("Size").getText()));
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
	
	@UiHandler("deleteButton")
	void deleteClicked(ClickEvent event) {
		hide();
		viewer.delete(item);
	}


}
