package za.ac.sun.cs.hons.argyle.client.gui.graph;

import java.util.HashMap;
import java.util.HashSet;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.util.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;

public class HistoryGraph extends ResizeComposite {
    interface Binder extends UiBinder<DockLayoutPanel, HistoryGraph> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    private Runnable	    chartBuilder;
    @UiField
    LayoutPanel		 panel;
    private LineGraph	   graph;

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
		.loadVisualizationApi(chartBuilder, LineChart.PACKAGE);

    }

    public void addHistories(
	    HashMap<BranchProduct, HashSet<DatePrice>> priceHistories) {
	if (graph != null) {
	    graph.setChartData(Utils.toDataTable(priceHistories));
	}
    }

}
