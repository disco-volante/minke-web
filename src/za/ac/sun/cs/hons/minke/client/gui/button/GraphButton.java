package za.ac.sun.cs.hons.minke.client.gui.button;

import java.util.HashSet;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.table.TableView.TABLE;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

public class GraphButton extends ImageButton {
    private WebPage webPage;
    private Object item;
    private TABLE type;
    public GraphButton(TABLE type, final Object item, WebPage webPage) {
	super(ImageUtils.getImages().graph());
	this.type = type;
	this.item = item;
	this.webPage = webPage;
    }

    @Override
    protected void clickAction() {
	HashSet<BranchProduct> holder = new HashSet<BranchProduct>();
	holder.add((BranchProduct) item);
	webPage.requestGraph(holder, type);
    }

}
