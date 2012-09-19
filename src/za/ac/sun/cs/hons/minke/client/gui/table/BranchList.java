package za.ac.sun.cs.hons.minke.client.gui.table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.gui.button.InfoButton;
import za.ac.sun.cs.hons.minke.client.gui.button.MapButton;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.util.Utils;

import com.google.gwt.user.client.ui.HorizontalPanel;

public class BranchList extends TableView {

	public BranchList(WebPage webPage) {
		super(webPage);
		viewButton.setText("Add Shopping List");
	}

	private HashMap<Long, Integer> products;

	private HashMap<Branch, HashSet<BranchProduct>> branches;

	public void addProducts(HashMap<Long, Integer> products) {
		this.products = products;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addItem(Object item, int pos) {
		Entry<Branch, Double> entry = (Entry<Branch, Double>) item;
		final HashSet<BranchProduct> items = branches.get(entry.getKey());
		final Branch branch = entry.getKey();
		InfoButton infoButton = createInfoButton(items);
		infoButton.setProducts(products);
		MapButton mapButton = new MapButton(branch, webPage);
		HorizontalPanel holder = new HorizontalPanel();
		holder.add(infoButton);
		holder.add(mapButton);
		table.setText(pos, 0, branch.getName());
		table.setText(pos, 1, branch.getStore().toString());
		table.setText(pos, 2, branch.getLocation().getCity().toString());
		table.setText(pos, 3, "R" + Double.toString(entry.getValue())); 
		table.setWidget(pos, 4, holder);
	}

	@Override
	protected int getTableCols() {
		return 5;
	}

	@Override
	protected TABLE getTableType() {
		return TABLE.STORE;
	}

	@Override
	protected String[] getHeadings() {
		return new String[] { "Branch", "Store", "Location", "Total", "Info" };
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setItemSet(Set<?> itemSet) {
		branches = new HashMap<Branch, HashSet<BranchProduct>>();
		HashMap<Branch, Double> pricesMap = new HashMap<Branch, Double>();
		for (Object item : itemSet) {
			Entry<Branch, HashSet<BranchProduct>> entry = (Entry<Branch, HashSet<BranchProduct>>) item;
			pricesMap.put(entry.getKey(),
					Utils.calcTotal(entry.getValue(), products));
			branches.put(entry.getKey(), entry.getValue());
		}
		super.setItemSet(Utils.entriesSortedByValues(pricesMap));
	}
}
