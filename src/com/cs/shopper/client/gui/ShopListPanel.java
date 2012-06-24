package com.cs.shopper.client.gui;

import java.util.HashMap;
import java.util.Set;

import com.cs.shopper.client.Shopper;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.utils.ProductDisplay;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;


public class ShopListPanel extends VerticalPanel implements ProductDisplay {
	private SuggestBox productBox;
	private HashMap<String,ShopListItem> shopping;
	private HashMap<ProductCategory,String>  products;
	private Shopper shopper;
	private MultiWordSuggestOracle oracle;
	public ShopListPanel(Shopper shopper) {
		super();
		setShopper(shopper);
		setLayoutOnChange(true);
		shopping = new HashMap<String,ShopListItem>();
		products = new HashMap<ProductCategory,String>();
		oracle = new MultiWordSuggestOracle();
		productBox = new SuggestBox(oracle);
		productBox.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getNativeEvent().getKeyCode()==KeyCodes.KEY_ENTER) addProduct();
				
			}
		});
		add(productBox);
		getShopper().getData().setPd(this);
		getShopper().addProductCategories(true);
	}

	protected void addProduct() {
		if(!shopping.containsKey(productBox.getText())&&products.containsKey(ProductCategory.toProductCategory(productBox.getText()))){
			ShopListItem item = new ShopListItem(this,productBox.getText());
			add(item);
			shopping.put(productBox.getText(), item);
			productBox.setText("");
		}
	}
	public boolean remove(ShopListItem w){
		boolean rm = super.remove(w);
		if(rm) shopping.remove( w.getText());
		return rm;
		
	}

	@Override
	public void showProductCategories(Set<ProductCategory> productCategories) {
		for(ProductCategory pt:productCategories){
			oracle.add(pt.toString());
			products.put(pt, pt.getType());
		}
	}

	@Override
	public void showProducts(Set<ProductData> set) {
		
	}

	public Shopper getShopper() {
		return shopper;
	}

	public void setShopper(Shopper shopper) {
		this.shopper = shopper;
	}





}
