package za.ac.sun.cs.hons.minke.client.gui.popup;

import za.ac.sun.cs.hons.minke.client.gui.table.ProductList;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class GraphTypePopup extends FocusedPopupPanel {
	interface Binder extends UiBinder<Widget, GraphTypePopup> {
	}

	@UiField
	Button cancelButton, graphButton;
	@UiField
	ListBox graphList;

	private static final Binder binder = GWT.create(Binder.class);
	private ProductList productList;

	public GraphTypePopup(boolean autohide, ProductList productList) {
		super(autohide);
		add(binder.createAndBindUi(this));
		this.productList = productList;
	}

	@UiHandler("graphButton")
	void sameClicked(ClickEvent event) {
		int i;
		if ((i = graphList.getSelectedIndex()) != -1) {
			getGraph(graphList.getValue(i));
		}
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	private void getGraph(String type) {
		GuiUtils.showLoader();
		if (!productList.getGraph(type)) {
			GuiUtils.showError("Selection Error", "No Product Selected");
			GuiUtils.hideLoader();
		}
		hide();
	}

}
