package za.ac.sun.cs.hons.argyle.client.gui.table;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;

public class ProductList extends TableView {
    public ProductList() {
	super(true);
	viewButton.setText("Browse Products");
    }

    @Override
    protected void addItem(Object item, int pos) {
	final BranchProduct bp = (BranchProduct) item;
	table.setText(pos, 0, bp.getProduct().getName());
	table.setText(pos, 1, bp.getProduct().getBrand().toString());
	table.setText(pos, 2, bp.getProduct().getCategory().getType());
	table.setText(pos, 3, bp.getBranch().getStore().getName());
	table.setText(pos, 4, bp.getProduct().getSize()
		+ bp.getProduct().getMeasurement());
	table.setText(pos, 5, Double.toString(bp.getDatePrice().getPrice()));
	table.setWidget(pos, 6, createInfoButton(item));
    }

    @Override
    protected int getTableCols() {
	return 7;
    }

    @Override
    protected TABLE getTableType() {
	return TABLE.BROWSE;
    }

    @Override
    protected String[] getHeadings() {
	return new String[] { "Name", "Brand", "Type", "Store", "Size", "Price" };
    }

}
