package za.ac.sun.cs.hons.minke.client.gui.table;

import java.util.HashMap;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.button.GraphButton;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;

public class ShoppingListDetail extends TableView {

	private HashMap<Long, Integer> products;

	public ShoppingListDetail(WebPage webPage) {
		super(webPage);
		viewButton.setEnabled(false);
		viewButton.setVisible(false);
	}

	private double getPrice(BranchProduct item) {
		return item.getDatePrice().getActualPrice()
				* products.get(item.getProduct().getID());
	}

	public void setProducts(HashMap<Long, Integer> products) {
		this.products = products;
	}

	@Override
	protected void addItem(Object item, int pos) {
		BranchProduct bp = (BranchProduct) item;
		table.setText(pos, 0, bp.getProduct().getName());
		table.setText(pos, 1, bp.getProduct().getBrand().toString());
		table.setText(pos, 2, "R" + bp.getDatePrice().getActualPrice()); 
		table.setText(pos, 3, Integer.toString(products.get(bp.getProductID())));
		table.setText(pos, 4, "R" + Double.toString(getPrice(bp))); 
		table.setText(pos, 5, bp.getDatePrice().getDate().toString());
		table.setWidget(pos, 6, new GraphButton(getTableType(), item, webPage));
	}

	@Override
	protected int getTableCols() {
		return 7;
	}

	@Override
	protected TABLE getTableType() {
		return TABLE.SHOPPING;
	}

	@Override
	protected String[] getHeadings() {
		return new String[] { "Name", "Brand", "Price", "Quantity",
				"Product Total", "Date", "Product Chart" };
	}
}
