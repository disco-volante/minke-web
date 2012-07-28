package za.ac.sun.cs.hons.argyle.client.gui.popup;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProductDetail extends FocusedPopupPanel {

    interface Binder extends UiBinder<Widget, ProductDetail> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    Label		       name;
    @UiField
    Label		       store;
    @UiField
    Label		       location;
    @UiField
    Label		       date;
    @UiField
    Label		       price;
    @UiField
    Label		       size;

    public ProductDetail() {
	super(true);
	add(binder.createAndBindUi(this));
    }

    public void setItem(BranchProduct item) {
	name.setText(item.getProduct().getName());
	store.setText(item.getBranch().getStore().getName());
	location.setText(item.getBranch().getLocation().getCity().toString());
	String dateString = item.getDatePrice().getDate().toString();
	date.setText(dateString.substring(0, dateString.indexOf(':')));
	price.setText(Double.toString(item.getDatePrice().getPrice()));
	size.setText(item.getProduct().getSize() + " "
		+ item.getProduct().getMeasurement());
    }

}
