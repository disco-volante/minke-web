package za.ac.sun.cs.hons.argyle.client.gui.popup.shoppinglist;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.gui.WebPage;
import za.ac.sun.cs.hons.argyle.client.gui.popup.FocusedPopupPanel;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;

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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ShoppingList extends FocusedPopupPanel {

    interface Binder extends UiBinder<Widget, ShoppingList> {
    }

    private static final Binder      binder = GWT.create(Binder.class);
    @UiField
    Button			   addButton, processButton;
    @UiField
    VerticalPanel		    itemList;
    @UiField(provided = true)
    SuggestBox		       productBox;

    private WebPage		  webPage;
    private MultiWordSuggestOracle   productOracle;

    private HashMap<String, Product> products;
    private HashMap<Long, Integer>   addedProducts;

    public ShoppingList() {
	super(false);
	productOracle = new MultiWordSuggestOracle();
	productBox = new SuggestBox(productOracle);
	add(binder.createAndBindUi(this));
    }

    public void setWebPage(WebPage webPage) {
	this.webPage = webPage;
    }

    private void addProduct() {
	String productString = productBox.getText();
	Product product;
	if (products == null || (product = products.get(productString)) == null) {
	    return;
	}
	if (!addedProducts.containsKey(product.getID())) {
	    addedProducts.put(product.getID(), 1);
	    itemList.add(new ShopListItem(this, productString));
	}
	productBox.setText("");
    }

    @UiHandler("processButton")
    void handleProcessClick(ClickEvent e) {
	if (addedProducts != null) {
	    hide();
	    webPage.requestShopping(addedProducts);
	}
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

    @Override
    public void hide() {
	super.hide();
	if (productBox != null) {
	    productBox.setText("");
	}
    }

    public void removeItem(ShopListItem item) {
	addedProducts.remove(item.getText());
	itemList.remove(item);

    }

    public void addProducts(HashMap<String, Product> products) {
	this.products = products;
	addedProducts = new HashMap<Long, Integer>();
	productOracle.addAll(products.keySet());
    }

    public void updateQuantity(int newValue, String name) {
	Long id = products.get(name).getID();
	addedProducts.put(id, newValue);
    }

}
