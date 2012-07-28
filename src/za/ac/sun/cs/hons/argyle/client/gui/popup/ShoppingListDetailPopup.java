package za.ac.sun.cs.hons.argyle.client.gui.popup;

import za.ac.sun.cs.hons.argyle.client.gui.table.ShoppingListDetail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class ShoppingListDetailPopup extends FocusedPopupPanel {
    interface Binder extends UiBinder<Widget, ShoppingListDetailPopup> {
    }

    @UiField
    ShoppingListDetail	  listDetails;
    private static final Binder binder = GWT.create(Binder.class);

    public ShoppingListDetailPopup() {
	super(true);
	add(binder.createAndBindUi(this));
	setSize("500px", "500px");
	listDetails.setSize("500px", "500px");
    }

    public ShoppingListDetail getShoppingListDetail() {
	return listDetails;
    }
}
