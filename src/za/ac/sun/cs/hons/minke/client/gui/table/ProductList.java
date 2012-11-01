package za.ac.sun.cs.hons.minke.client.gui.table;

import java.util.HashSet;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.popup.GraphTypePopup;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ProductList extends TableView {

	protected GraphTypePopup graphType;
	private Button graphButton;

	public ProductList(WebPage webPage) {
		super(webPage);
		graphButton = new Button();
		graphButton.setText("Product Timelines");
		graphButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (available()) {
					graphType.setItems();
					graphType.center();
				}
			}

		});
		super.addToFooter(graphButton);
		graphType = new GraphTypePopup(false, this);
		viewButton.setText("Browse Products");
	}

	@Override
	protected void addItem(Object item, int pos) {
		final BranchProduct bp = (BranchProduct) item;
		table.setText(pos, 0, bp.getProduct().getName());
		table.setText(pos, 1, bp.getProduct().getBrand().toString());
		table.setText(pos, 2, bp.getBranch().toString());
		table.setText(pos, 3, bp.getProduct().getSize()
				+ bp.getProduct().getMeasurement().toString());
		table.setText(pos, 4,
				"R" + Double.toString(bp.getDatePrice().getActualPrice()));
		table.setWidget(pos, 5, createInfoButton(item));
	}

	@Override
	protected int getTableCols() {
		return 6;
	}

	@Override
	protected TABLE getTableType() {
		return TABLE.BROWSE;
	}

	@Override
	protected String[] getHeadings() {
		return new String[] { "Name", "Brand", "Store", "Size", "Price", "Info" };
	}

	public void getGraph(HashSet<BranchProduct> items) {
		webPage.requestGraph(items, getTableType());
	}

	public void setHeader(String name) {
		tableHeader.setHTML("<h2>Product Browser</h2>");

	}

	public Set<?> getItemSet() {
		return itemSet;
	}

}
