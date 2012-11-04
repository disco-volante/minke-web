package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.HashSet;

import za.ac.sun.cs.hons.minke.client.gui.table.ProductList;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class GraphTypePopup extends FocusedPopupPanel implements
		KeyPressHandler {
	interface Binder extends UiBinder<Widget, GraphTypePopup> {
	}

	@UiField
	Button cancelButton, graphButton;
	@UiField
	VerticalPanel checkboxList;

	private static final Binder binder = GWT.create(Binder.class);
	private ProductList productList;
	private HashSet<BranchProduct> items;

	public GraphTypePopup(boolean autohide, ProductList productList) {
		super(autohide);
		add(binder.createAndBindUi(this));
		this.productList = productList;
		items = new HashSet<BranchProduct>();
	}

	public void setItems() {
		checkboxList.clear();
		for (Object item : productList.getItemSet()) {
			if (item != null) {
				final BranchProduct bp = (BranchProduct) item;
				items.add(bp);
				final CheckBox checkBox = new CheckBox(bp.toString());
				checkBox.setValue(true);
				checkboxList.add(checkBox);
				checkBox.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						if (checkBox.getValue()) {
							items.add(bp);
						} else {
							items.remove(bp);
						}
					}
				});
			}

		}
	}

	@UiHandler("graphButton")
	void itemClicked(ClickEvent event) {
		if (items.size() == 0) {
			GuiUtils.showError("Selection Error", "No Product Selected");
		} else {
			GuiUtils.showLoader();
			productList.getGraph(items);
		}
		hide();
	}

	@Override
	public void onKeyPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			itemClicked(null);
		} else if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			cancelClicked(null);
		}
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

}
