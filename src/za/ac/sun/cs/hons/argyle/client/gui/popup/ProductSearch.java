package za.ac.sun.cs.hons.argyle.client.gui.popup;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.gui.WebPage;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class ProductSearch extends FocusedPopupPanel {

    interface Binder extends UiBinder<Widget, ProductSearch> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField
    Button productButton, cancelButton;
    @UiField(provided = true)
    SuggestBox productCategoryBox, cityBox;

    private MultiWordSuggestOracle productCategoryOracle;
    private MultiWordSuggestOracle cityOracle;
    private WebPage webPage;
    private HashMap<String, ProductCategory> productCategories;
    private HashMap<String, City> cities;

    public ProductSearch(WebPage webPage) {
	super(false);
	initSuggestBoxes();
	this.webPage = webPage;
	add(binder.createAndBindUi(this));
    }

    private void initSuggestBoxes() {
	productCategoryOracle = new MultiWordSuggestOracle();
	productCategoryBox = new SuggestBox(productCategoryOracle);
	cityOracle = new MultiWordSuggestOracle();
	cityBox = new SuggestBox(cityOracle);
    }

    @Override
    public void hide() {
	super.hide();
	if (cityBox != null && productCategoryBox != null) {
	    cityBox.setText("");
	    productCategoryBox.setText("");
	}
    }

    public void addCities(HashMap<String, City> cities) {
	this.cities = cities;
	cityOracle.addAll(cities.keySet());
    }

    public void addProductCategories(
	    HashMap<String, ProductCategory> productCategories) {
	this.productCategories = productCategories;
	productCategoryOracle.addAll(productCategories.keySet());
    }

    @UiHandler("productButton")
    void handleClick(ClickEvent e) {
	City city;
	ProductCategory productCategory;
	if ((city = cities.get(cityBox.getText())) != null
		&& (productCategory = productCategories.get(productCategoryBox
			.getText())) != null) {
	    hide();
	    webPage.requestBrowsing(city.getID(), productCategory.getID());
	}
    }

    @UiHandler("cancelButton")
    void cancelClicked(ClickEvent event) {
	hide();
    }

}
