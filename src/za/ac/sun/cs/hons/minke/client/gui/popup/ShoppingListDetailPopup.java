package za.ac.sun.cs.hons.minke.client.gui.popup;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.table.ShoppingListDetail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class ShoppingListDetailPopup extends FocusedPopupPanel {
	interface Binder extends UiBinder<Widget, ShoppingListDetailPopup> {
	}

	@UiField(provided = true)
	ShoppingListDetail listDetails;
	@UiField
	Button closeButton;
	private static final Binder binder = GWT.create(Binder.class);

	public ShoppingListDetailPopup(WebPage webPage) {
		super(true);
		listDetails = new ShoppingListDetail(webPage);
		add(binder.createAndBindUi(this));
	}

	public ShoppingListDetail getShoppingListDetail() {
		return listDetails;
	}

	@UiHandler("closeButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}
}
