package za.ac.sun.cs.hons.argyle.client.gui.util;

import za.ac.sun.cs.hons.argyle.client.gui.button.DownButton;
import za.ac.sun.cs.hons.argyle.client.gui.button.UpButton;
import za.ac.sun.cs.hons.argyle.client.gui.popup.shoppinglist.ShopListItem;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.Widget;

public class SpinEdit extends ResizeComposite {
    interface Binder extends UiBinder<DockLayoutPanel, SpinEdit> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    private final static int UP = 1;
    private final static int DOWN = -1;
    private int RATE = 1;
    @UiField
    IntegerBox integerBox;
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
	integerBox.setValue(val);
    }

    @UiHandler("upButton")
    void handleUpClick(ClickEvent e) {
	changeValue(UP);
    }

    @UiHandler("downButton")
    void handleDownClick(ClickEvent e) {
	changeValue(DOWN);
    }

    public void changeValue(int change) {

	int total = change * getRATE();
	if (getValue() > 1 || total > 0) {
	    setValue(getValue() + total);
	}
	shopListItem.updateQuantity(getValue());
    }

    /**
     * Returns the value being held.
     * 
     * @return
     */
    public int getValue() {
	return integerBox.getValue() == null ? 0 : integerBox.getValue();
    }

    public int getRATE() {
	return RATE;
    }

    /**
     * Sets the value to the control
     * 
     * @param value
     *            Value to be set
     */
    public void setValue(int value) {
	integerBox.setValue(value);
    }

    /**
     * Sets the rate at which increment or decrement is done.
     * 
     * @param rate
     */
    public void setRate(int rate) {
	this.RATE = rate;
    }

    public void setShopListItem(ShopListItem shopListItem) {
	this.shopListItem = shopListItem;
    }
}