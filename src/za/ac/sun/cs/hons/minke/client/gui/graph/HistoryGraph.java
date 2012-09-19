package za.ac.sun.cs.hons.minke.client.gui.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.util.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;

public class HistoryGraph extends ResizeComposite  {
	interface Binder extends UiBinder<DockLayoutPanel, HistoryGraph> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	private Runnable chartBuilder;
	@UiField
	LayoutPanel panel;
	protected ScatterGraph graph;

	public HistoryGraph() {
		initWidget(binder.createAndBindUi(this));
		chartBuilder = new Runnable() {
			@Override
			public void run() {
				graph = new ScatterGraph();
				panel.add(graph);
			}
		};
		VisualizationUtils
				.loadVisualizationApi(chartBuilder, CoreChart.PACKAGE);

	}

	public void addHistories(
			HashMap<BranchProduct, List<DatePrice>> priceHistories) {
		if (graph != null) {
			graph.setChartData(Utils.toDataTable(priceHistories));
		}
	}

}
