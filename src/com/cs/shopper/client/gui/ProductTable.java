package com.cs.shopper.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cs.shopper.client.Shopper;
import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.utils.LocationDisplay;
import com.cs.shopper.client.utils.Product;
import com.cs.shopper.client.utils.ProductDisplay;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

public class ProductTable extends ContentPanel implements LocationDisplay,
		ProductDisplay {
	private SuggestBox city, type;
	private MultiWordSuggestOracle cityOracle, typeOracle;
	private Shopper shopper;
	private Button productBtn;
	private ListStore<Product> productList;
	private Grid<Product> grid;
	private ColumnModel cm;

	public ProductTable(Shopper shopper) {
		super();
		setLayoutOnChange(true);
		setLayout(new FlowLayout());
		FlexTable ft = new FlexTable();
		ft.setCellPadding(10);
		ft.setCellSpacing(10);
		setShopper(shopper);
		cityOracle = new MultiWordSuggestOracle();
		city = new SuggestBox(cityOracle);
		ft.setWidget(0, 0, city);
		requestCities();

		typeOracle = new MultiWordSuggestOracle();
		type = new SuggestBox(typeOracle);
		ft.setWidget(1, 0, type);
		requestProductCategories();

		productBtn = new Button();
		productBtn.setText("Get Product Info");
		productBtn.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				requestProducts();
			}
		});
		ft.setWidget(2, 0, productBtn);
		add(ft);
		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
		ColumnConfig column = new ColumnConfig("name", "Name", 200);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		column = new ColumnConfig("type", "Type", 150);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		column = new ColumnConfig("store", "Store", 200);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		column = new ColumnConfig("size", "Size", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		configs.add(column);
		column = new ColumnConfig("measurement", "Measurement", 100);
		column.setAlignment(HorizontalAlignment.LEFT);
		configs.add(column);
		column = new ColumnConfig("price", "Price", 100);
		column.setAlignment(HorizontalAlignment.RIGHT);
		configs.add(column);
		productList = new ListStore<Product>();
		cm = new ColumnModel(configs);
		grid = new Grid<Product>(productList, cm);
		grid.setStyleAttribute("borderTop", "none");
		grid.setBorders(true);
		grid.setStripeRows(true);
		grid.setHeight(Window.getClientHeight());
		setScrollMode(Scroll.ALWAYS);
		add(grid);
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}

	protected void requestCities() {
		getShopper().getData().setLd(this);
		getShopper().addCities(true);

	}

	@Override
	public void showCities(Set<City> cities) {
		for (City c : cities) {
			cityOracle.add(c.toString());
		}
	}

	protected void requestProductCategories() {
		getShopper().getData().setPd(this);
		getShopper().addProductCategories(true);
	}

	@Override
	public void showProductCategories(Set<ProductCategory> productCategories) {
		for (ProductCategory p : productCategories) {
			typeOracle.add(p.toString());
		}
	}

	protected void requestProducts() {
			getShopper().addProducts(ProductCategory.toProductCategory(type.getText()),
					City.toCity(city.getText()));
	}

	@Override
	public void showProducts(Set<ProductData> products) {
		productList.removeAll();
		for (ProductData p : products) {
			productList.add(new Product(p));
		}
	}

}
