package za.ac.sun.cs.hons.argyle.client.gui.popup.shoppinglist;

import za.ac.sun.cs.hons.argyle.client.gui.button.RemoveButton;
import za.ac.sun.cs.hons.argyle.client.gui.util.SpinEdit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;

public class ShopListItem extends ResizeComposite {

    interface Binder extends UiBinder<DockLayoutPanel, ShopListItem> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField(provided = true)
    Label lbl;
    @UiField
    SpinEdit quantitySpinner;
    @UiField(provided = true)
    RemoveButton removeBtn;
    private ShoppingList list;
    private String name;

    public ShopListItem(ShoppingList list, String name) {
	setName(name);
	this.list = list;
	lbl = new Label(name);
	removeBtn = new RemoveButton(this);
	initWidget(binder.createAndBindUi(this));
	quantitySpinner.setShopListItem(this);
    }

    public void remove() {
	list.remove(this);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void updateQuantity(int newValue) {
	list.updateQuantity(newValue, name);
    }

}
