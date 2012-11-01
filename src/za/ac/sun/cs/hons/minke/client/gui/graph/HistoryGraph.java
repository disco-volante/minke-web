package za.ac.sun.cs.hons.minke.client.gui.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import za.ac.sun.cs.hons.minke.client.gui.popup.ChangeGraphPopup;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;
import za.ac.sun.cs.hons.minke.client.util.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;

public class HistoryGraph extends ResizeComposite {
	interface Binder extends UiBinder<DockLayoutPanel, HistoryGraph> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	private Runnable chartBuilder;
	@UiField
	LayoutPanel panel;
	@UiField
	Button redrawButton;
	protected LineGraph graph;
	private ChangeGraphPopup popup;
	private HashMap<BranchProduct, List<DatePrice>> items, allItems;

	public HistoryGraph() {
		initWidget(binder.createAndBindUi(this));
		chartBuilder = new Runnable() {
			@Override
			public void run() {
				graph = new LineGraph();
				panel.add(graph);
			}
		};
		VisualizationUtils
				.loadVisualizationApi(chartBuilder, CoreChart.PACKAGE);
		popup = new ChangeGraphPopup(true, this);

	}

	@UiHandler("redrawButton")
	void redrawClicked(ClickEvent event) {
		popup.setItems();
		popup.center();
	}

	public void addHistories(
			HashMap<BranchProduct, List<DatePrice>> priceHistories) {
		if (graph != null) {
			items = priceHistories;
			allItems = priceHistories;
			graph.setChartData(Utils.toDataTable(items));
		}
	}

	public HashMap<BranchProduct, List<DatePrice>> getItems() {
		return allItems;
	}
	
	public boolean showing(BranchProduct bp){
		return items.containsKey(bp);
	}

	public void changeHistories(HashSet<BranchProduct> modified) {
		GuiUtils.showLoader();
		items = new HashMap<BranchProduct, List<DatePrice>>();
		for (BranchProduct bp : modified) {
			items.put(bp, allItems.get(bp));
		}
		graph.setChartData(Utils.toDataTable(items));
		GuiUtils.hideLoader();
	}

}
