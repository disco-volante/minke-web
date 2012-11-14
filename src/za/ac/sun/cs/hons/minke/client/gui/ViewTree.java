package za.ac.sun.cs.hons.minke.client.gui;

import za.ac.sun.cs.hons.minke.client.gui.popup.PasswordPopup;
import za.ac.sun.cs.hons.minke.client.util.CSSUtils.SelectionStyle;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils.Images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
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
	private TreeItem browsing, shopping, map, graph, admin;

	public ViewTree(WebPage webPage) {
		Images images = ImageUtils.getImages();
		buildTree(images);
		this.webPage = webPage;
		initWidget(binder.createAndBindUi(this));
		setSelected("browsing");
	}

	private void buildTree(Images images) {
		tree = new Tree(images);
		browsing = new TreeItem(ImageUtils.getImage(images.browsing(),
				"Product Browsing"));
		shopping = new TreeItem(ImageUtils.getImage(images.shopping(),
				"Shopping List"));
		map = new TreeItem(ImageUtils.getImage(images.map(), "Location"));
		graph = new TreeItem(ImageUtils.getImage(images.graph(),
				"Product Timelines"));
		admin = new TreeItem(ImageUtils.getImage(images.admin(), "Admin"));
		tree.addItem(browsing);
		tree.addItem(shopping);
		tree.addItem(map);
		tree.addItem(graph);
		tree.addItem(admin);
		tree.addItem(new TreeItem(new Anchor(  "minke for Android",  "http://pieterjordaan.github.com/minke-android")));
		tree.addItem(new TreeItem(new Anchor( "About", "http://pieterjordaan.github.com/minke-web")));
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
		} else if (selected.equals(admin)) {
			verify();
		}
	}

	private void verify() {
		PasswordPopup password = new PasswordPopup(this);
		password.center();
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

	public void showAdmin() {
		webPage.showAdmin();
	}

	public void setHome() {
		tree.setSelectedItem(browsing);
	}

}
