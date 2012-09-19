package za.ac.sun.cs.hons.minke.client.gui.removable;

import za.ac.sun.cs.hons.minke.client.gui.popup.ProductSearch;

import com.google.gwt.user.client.ui.Label;

public class SearchItem extends Removable {

	private ProductSearch search;

	public SearchItem(DynamicList list, String name, ProductSearch search) {
		super(list, name, new Label(""));
		this.search = search;
	}

	@Override
	public void remove() {
		super.remove();
		search.removeItem(this);
	}

}
