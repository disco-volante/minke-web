package za.ac.sun.cs.hons.argyle.client.gui.table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.argyle.client.util.ImageUtils;
import za.ac.sun.cs.hons.argyle.client.util.Utils;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class BranchList extends TableView {

    public BranchList() {
	super(false);
	viewButton.setText("Add Shopping List");
    }

    private HashMap<Long, Integer> products;

    public void addProducts(HashMap<Long, Integer> products) {
	this.products = products;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addItem(Object item, int pos) {
	Entry<Branch, HashSet<BranchProduct>> entry = (Entry<Branch, HashSet<BranchProduct>>) item;
	final HashSet<BranchProduct> items = entry.getValue();
	final Branch branch = entry.getKey();
	InfoButton infoButton = createInfoButton(items);
	infoButton.setProducts(products);
	Button directionsButton = new Button();
	directionsButton.setHTML(ImageUtils.imageItemHTML(ImageUtils
		.getImages().map()));
	directionsButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		getWebPage().showMap(branch.getLocation());
	    }
	});
	table.setText(pos, 0, branch.getName());
	table.setText(pos, 1, branch.getStore().toString());
	table.setText(pos, 2, branch.getLocation().getCity().toString());
	table.setText(pos, 3, Double.toString(Utils.calcTotal(items, products)));
	table.setWidget(pos, 4, infoButton);
	table.setWidget(pos, 5, directionsButton);
    }

    @Override
    protected int getTableCols() {
	return 6;
    }

    @Override
    protected TABLE getTableType() {
	return TABLE.STORE;
    }

    @Override
    protected String[] getHeadings() {
	return new String[] { "Branch", "Store", "Location", "Total" };
    }

}
