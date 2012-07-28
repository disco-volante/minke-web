package za.ac.sun.cs.hons.argyle.client.gui.graph;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

public class LineGraph extends Composite {
    interface Binder extends UiBinder<Widget, LineGraph> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField(provided = true)
    SimplePanel		 chartPanel;
    @UiField
    Label		       mouseLbl;
    private LineChart	   chart;

    public LineGraph() {
	chartPanel = new SimplePanel();
	initWidget(binder.createAndBindUi(this));

    }

    public void setChartData(DataTable data) {
	if (chart != null) {
	    chartPanel.remove(chart);
	}
	chart = new LineChart(data, createOptions());
	chart.addSelectHandler(new GraphSelectHandler(chart));
	chart.addOnMouseOverHandler(new GraphOnMouseOverHandler(mouseLbl));
	chart.addOnMouseOutHandler(new GraphOnMouseOutHandler(mouseLbl));
	chartPanel.add(chart);
    }

    private Options createOptions() {
	Options options = LineChart.createOptions();
	options.setWidth(700);
	options.setHeight(500);
	options.setTitle("Price History");
	options.setPointSize(3);
	return options;
    }
}