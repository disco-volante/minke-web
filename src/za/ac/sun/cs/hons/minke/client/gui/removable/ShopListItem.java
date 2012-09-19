package za.ac.sun.cs.hons.minke.client.gui.removable;

import za.ac.sun.cs.hons.minke.client.gui.popup.shoppinglist.ShoppingList;
import za.ac.sun.cs.hons.minke.client.gui.spinedit.SpinEdit;

public class ShopListItem extends Removable {

	private SpinEdit quantitySpinner;
	private ShoppingList shop;

	public ShopListItem(DynamicList list, String name, ShoppingList shop) {
		super(list, name, new SpinEdit());
		quantitySpinner = (SpinEdit) getExtraWidget();
		quantitySpinner.setShopListItem(this);
		this.shop = shop;
	}

	public void updateQuantity(int newValue) {
		shop.updateQuantity(newValue, getName());
	}
	@Override
	public void remove() {
		super.remove();
		shop.removeItem(this);
	}

}
