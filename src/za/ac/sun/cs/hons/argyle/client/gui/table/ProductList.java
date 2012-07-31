package za.ac.sun.cs.hons.argyle.client.gui.table;

import java.util.HashSet;

import za.ac.sun.cs.hons.argyle.client.gui.WebPage;
import za.ac.sun.cs.hons.argyle.client.gui.popup.GraphTypePopup;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ProductList extends TableView {

    private GraphTypePopup graphType;
    private Button graphButton;

    public ProductList(WebPage webPage) {
	super(webPage);
	graphButton = new Button();
	graphButton.setText("Product Histories");
	graphButton.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		if (available()) {
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
	table.setText(pos, 2, bp.getProduct().getCategory().getType());
	table.setText(pos, 3, bp.getBranch().toString());
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

    private HashSet<BranchProduct> getSame(Object searchO) {
	BranchProduct searchBP = (BranchProduct) searchO;
	HashSet<BranchProduct> holder = new HashSet<BranchProduct>();
	for (Object o : itemSet) {
	    BranchProduct bp = (BranchProduct) o;
	    if (bp.getProductID() == searchBP.getProductID()) {
		holder.add(bp);
	    }
	}
	return holder;
    }

    public boolean getGraph(String type) {
	if (type.equals("all")) {
	    webPage.requestGraph(itemSet, getTableType());
	} else if (selected != null) {
	    if (type.equals("same")) {
		webPage.requestGraph(getSame(selected), getTableType());
	    } else if (type.equals("selected")) {
		HashSet<BranchProduct> holder = new HashSet<BranchProduct>();
		holder.add((BranchProduct) selected);
		webPage.requestGraph(holder, getTableType());
	    }
	} else {
	    return false;
	}
	return true;
    }

}
