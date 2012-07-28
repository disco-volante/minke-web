package za.ac.sun.cs.hons.argyle.client.gui.table;

import java.util.HashMap;
import java.util.HashSet;

import za.ac.sun.cs.hons.argyle.client.gui.WebPage;
import za.ac.sun.cs.hons.argyle.client.gui.table.TableView.TABLE;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.util.ImageUtils;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class InfoButton extends Button {
    private WebPage		webPage;
    private Object		 item;
    private TABLE		  type;
    private HashMap<Long, Integer> products;

    public InfoButton(TABLE type, final Object item, WebPage webPage) {
	super();
	this.type = type;
	this.item = item;
	this.webPage = webPage;
	setHTML(ImageUtils.imageItemHTML(ImageUtils.getImages().info()));
	addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		showInfo();
	    }

	});
    }

    public void setProducts(HashMap<Long, Integer> products) {
	this.products = products;
    }

    @SuppressWarnings("unchecked")
    protected void showInfo() {
	switch (type) {
	    case BROWSE:
		webPage.showProductDetails((BranchProduct) item);
		break;
	    case SHOPPING:
		HashSet<BranchProduct> holder = new HashSet<BranchProduct>();
		holder.add((BranchProduct) item);
		webPage.requestGraph(holder, type);
		break;
	    case STORE:
		webPage.showListDetails((HashSet<BranchProduct>) item, products);
		break;
	    case DEFAULT:
		break;
	}
    }

}
