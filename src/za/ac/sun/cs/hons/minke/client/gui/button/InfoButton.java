package za.ac.sun.cs.hons.minke.client.gui.button;

import java.util.HashMap;
import java.util.HashSet;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.table.TableView.TABLE;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

public class InfoButton extends ImageButton {
    private WebPage webPage;
    private Object item;
    private TABLE type;
    private HashMap<Long, Integer> products;

    public InfoButton(TABLE type, final Object item, WebPage webPage) {
	super(ImageUtils.getImages().info());
	this.type = type;
	this.item = item;
	this.webPage = webPage;

    }

    public void setProducts(HashMap<Long, Integer> products) {
	this.products = products;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void clickAction() {
	switch (type) {
	    case BROWSE:
		webPage.showProductDetails((BranchProduct) item);
		break;
	    case STORE:
		webPage.showListDetails((HashSet<BranchProduct>) item, products);
		break;
	    case DEFAULT:
		break;
	}
    }

}
