package za.ac.sun.cs.hons.argyle.client.gui.popup.shoppinglist;

import za.ac.sun.cs.hons.argyle.client.gui.util.SpinEdit;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class ShopListItem extends Composite {

	interface Binder extends UiBinder<Widget, ShopListItem> {
	}
	private static final Binder binder = GWT.create(Binder.class);

	@UiField(provided=true)
	Label lbl;
	@UiField
	SpinEdit quantitySpinner;
	@UiField
	PushButton removeBtn;
	private ShoppingList list;
	private String name;
	public ShopListItem(ShoppingList list,String name) {
		setList(list);
		setName(name);
		lbl = new Label(name);
		initWidget(binder.createAndBindUi(this));
		quantitySpinner.setShopListItem(this);
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	private void setList(ShoppingList list) {
		this.list = list;
		
	}
	private ShoppingList getList() {
		return list;
	}
	
	@UiHandler("removeBtn")
	void handleClick(ClickEvent e) {
		remove();
	}

	protected void remove() {
		getList().removeItem(this);
	}



	public String getText() {
		return lbl.getText();
	}


	public void updateQuantity(int newValue) {
		list.updateQuantity(newValue, name);	
	}

}
