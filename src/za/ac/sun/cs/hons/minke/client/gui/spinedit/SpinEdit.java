package za.ac.sun.cs.hons.minke.client.gui.spinedit;

import za.ac.sun.cs.hons.minke.client.gui.button.DownButton;
import za.ac.sun.cs.hons.minke.client.gui.button.UpButton;
import za.ac.sun.cs.hons.minke.client.gui.removable.ShopListItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResizeComposite;

public class SpinEdit extends ResizeComposite {
	interface Binder extends UiBinder<DockLayoutPanel, SpinEdit> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Label valLbl;
	@UiField(provided = true)
	UpButton upButton;
	@UiField(provided = true)
	DownButton downButton;
	private ShopListItem shopListItem;

	public SpinEdit() {
		downButton = new DownButton(this);
		upButton = new UpButton(this);
		initWidget(binder.createAndBindUi(this));
	}

	public SpinEdit(int val) {
		this();
		setValue(val);
	}

	public void changeValue(int change) {
		if (getValue() > 1 || change > 0) {
			setValue(getValue() + change);
			shopListItem.updateQuantity(getValue());
		}
	}

	/**
	 * Returns the value being held.
	 * 
	 * @return
	 */
	public int getValue() {
		return Integer.parseInt(valLbl.getText());
	}

	/**
	 * Sets the value to the control
	 * 
	 * @param value
	 *            Value to be set
	 */
	public void setValue(int value) {
		valLbl.setText(Integer.toString(value));
	}

	public void setShopListItem(ShopListItem shopListItem) {
		this.shopListItem = shopListItem;
	}
}