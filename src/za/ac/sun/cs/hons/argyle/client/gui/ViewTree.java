package za.ac.sun.cs.hons.argyle.client.gui;

import za.ac.sun.cs.hons.argyle.client.util.ImageUtils;
import za.ac.sun.cs.hons.argyle.client.util.ImageUtils.Images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class ViewTree extends ResizeComposite {
    interface Binder extends UiBinder<StackLayoutPanel, ViewTree> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField(provided = true)
    Tree			tree;
    private WebPage	     webPage;

    public ViewTree() {
	Images images = ImageUtils.getImages();
	tree = new Tree(images);
	final TreeItem browsing = new TreeItem(ImageUtils.imageItemHTML(
		images.browsing(), "Product Browsing"));
	final TreeItem shopping = new TreeItem(ImageUtils.imageItemHTML(
		images.shopping(), "Shopping List"));
	final TreeItem map = new TreeItem(ImageUtils.imageItemHTML(
		images.map(), "Location"));
	tree.addItem(browsing);
	tree.addItem(shopping);
	tree.addItem(map);
	tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
	    @Override
	    public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem selected = event.getSelectedItem();
		if (selected.equals(shopping)) {
		    displayShopping();
		} else if (selected.equals(browsing)) {
		    displayBrowsing();
		} else if (selected.equals(map)) {
		    displayMap();
		}
	    }
	});
	initWidget(binder.createAndBindUi(this));
    }

    public void setWebPage(WebPage webPage) {
	this.webPage = webPage;
    }

    public WebPage getWebPage() {
	return webPage;
    }

    private void displayBrowsing() {
	getWebPage().showBrowsing();
    }

    private void displayShopping() {
	getWebPage().showShopping();
    }

    protected void displayMap() {
	getWebPage().showMap();
    }
}
