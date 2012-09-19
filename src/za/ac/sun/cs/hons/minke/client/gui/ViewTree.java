package za.ac.sun.cs.hons.minke.client.gui;

import za.ac.sun.cs.hons.minke.client.util.CSSUtils.SelectionStyle;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils.Images;

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
	Tree tree;
	@UiField
	SelectionStyle selectionStyle;
	private WebPage webPage;
	private TreeItem browsing, shopping, map, graph;

	public ViewTree(WebPage webPage) {
		Images images = ImageUtils.getImages();
		buildTree(images);
		this.webPage = webPage;
		initWidget(binder.createAndBindUi(this));
		setSelected("browsing");
	}

	private void buildTree(Images images) {
		tree = new Tree(images);
		browsing = new TreeItem(ImageUtils.imageItemHTML(images.browsing(),
				"Product Browsing"));
		shopping = new TreeItem(ImageUtils.imageItemHTML(images.shopping(),
				"Shopping List")); //$NON-NLS-1$
		map = new TreeItem(ImageUtils.imageItemHTML(images.map(), "Location"));
		graph = new TreeItem(ImageUtils.imageItemHTML(images.graph(),
				"Product Timelines"));
		tree.addItem(browsing);
		tree.addItem(shopping);
		tree.addItem(map);
		tree.addItem(graph);
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				TreeItem selected = event.getSelectedItem();
				displayItem(selected);
			}
		});
	}

	protected void displayItem(TreeItem selected) {
		if (selected.equals(shopping)) {
			webPage.showShopping();
		} else if (selected.equals(browsing)) {
			webPage.showBrowsing();
		} else if (selected.equals(map)) {
			webPage.showMap();
		} else if (selected.equals(graph)) {
			webPage.showGraph();
		}
	}

	public void setSelected(String name) {
		if (name.equals("shopping")) {
			addStyle(shopping);
		} else if (name.equals("browsing")) {
			addStyle(browsing);
		} else if (name.equals("map")) {
			addStyle(map);
		} else if (name.equals("graph")) {
			addStyle(graph);
		}
	}

	private void addStyle(TreeItem selected) {
		String style = selectionStyle.selectedRow();
		shopping.removeStyleName(style);
		browsing.removeStyleName(style);
		map.removeStyleName(style);
		graph.removeStyleName(style);
		selected.addStyleName(style);
	}

}
