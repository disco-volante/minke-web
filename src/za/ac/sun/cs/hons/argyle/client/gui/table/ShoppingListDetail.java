package za.ac.sun.cs.hons.argyle.client.gui.table;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;

public class ShoppingListDetail extends TableView {

    private HashMap<Long, Integer> products;

    public ShoppingListDetail() {
	super(false);
	viewButton.setEnabled(false);
	viewButton.setVisible(false);
    }

    private double getPrice(BranchProduct item) {
	return item.getDatePrice().getPrice()
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
	table.setText(pos, 2, bp.getProduct().getCategory().getType());
	table.setText(pos, 3, Double.toString(getPrice(bp)));
	table.setText(pos, 4, bp.getDatePrice().getDate().toString());
	table.setWidget(pos, 5, createInfoButton(item));
    }

    @Override
    protected int getTableCols() {
	return 6;
    }

    @Override
    protected TABLE getTableType() {
	return TABLE.SHOPPING;
    }

    @Override
    protected String[] getHeadings() {
	return new String[] { "Name", "Brand", "Category", "Cost", "Date" };
    }
}
