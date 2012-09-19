package za.ac.sun.cs.hons.minke.client.gui.popup.shoppinglist;

import java.util.HashMap;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.popup.FocusedPopupPanel;
import za.ac.sun.cs.hons.minke.client.gui.removable.DynamicList;
import za.ac.sun.cs.hons.minke.client.gui.removable.Removable;
import za.ac.sun.cs.hons.minke.client.gui.removable.ShopListItem;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class ShoppingList extends FocusedPopupPanel {

	interface Binder extends UiBinder<Widget, ShoppingList> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Button addButton, processButton, cancelButton;
	@UiField(provided = true)
	SuggestBox productBox;
	@UiField
	DynamicList itemList;

	private WebPage webPage;
	private MultiWordSuggestOracle productOracle;

	private EntityNameMap products;
	private HashMap<Long, Integer> addedProducts;
	private boolean loaded;

	public ShoppingList(WebPage webPage) {
		super(false);
		productOracle = new MultiWordSuggestOracle();
		productBox = new SuggestBox(productOracle);
		this.webPage = webPage;
		add(binder.createAndBindUi(this));

	}

	private void addProduct() {
		String productString;
		if (!(productString = productBox.getText()).equals("")) {
			Long productID;
			if (products == null
					|| (productID = products.getID(productString)) == null) {
				return;
			}
			if (!addedProducts.containsKey(productID)) {
				addedProducts.put(productID, 1);
				itemList.addItem(new ShopListItem(itemList, productString, this));
			}
			productBox.setText("");
		}
	}

	@UiHandler("processButton")
	void handleProcessClick(ClickEvent e) {
		if (addedProducts != null) {
			webPage.requestShopping(addedProducts);
		}
		hide();
	}

	@UiHandler("addButton")
	void handleAddClick(ClickEvent e) {
		addProduct();
	}

	@UiHandler("productBox")
	void handleEnter(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			addProduct();
		}
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	@Override
	public void hide() {
		super.hide();
		if (loaded) {
			productBox.setText("");
			itemList.clear();
		}
	}

	public void removeItem(Removable item) {
		addedProducts.remove(products.getID(item.getName()));
	}

	public void addProducts(EntityNameMap products) {
		this.products = products;
		addedProducts = new HashMap<Long, Integer>();
		productOracle.addAll(products.getNames());
		loaded = true;
	}

	@Override
	public void show() {
		super.show();
		if (loaded) {
			addedProducts.clear();
		}
	}

	public void updateQuantity(int newValue, String name) {
		Long id = products.getID(name);
		addedProducts.put(id, newValue);
	}

}
