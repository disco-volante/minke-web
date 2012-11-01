package za.ac.sun.cs.hons.minke.client.gui.graph;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

public class ScatterGraph extends ResizeComposite {
	interface Binder extends UiBinder<DockLayoutPanel, ScatterGraph> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided = true)
	LayoutPanel chartPanel;
	private LineChart chart;

	public ScatterGraph() {
		chartPanel = new LayoutPanel();
		initWidget(binder.createAndBindUi(this));

	}

	public void setChartData(DataTable data) {
		if (chart != null) {
			chartPanel.remove(chart);
		}
		chart = new LineChart(data, createOptions());
		chart.addSelectHandler(new GraphSelectHandler(chart));
		chartPanel.add(chart);
	}

	private Options createOptions() {
		Options options = CoreChart.createOptions();
		int width = Window.getClientWidth() - 300;
		int height = Window.getClientHeight() - 200;
		options.setWidth(width);
		options.setHeight(height);
		options.setPointSize(5);
		return options;
	}
}