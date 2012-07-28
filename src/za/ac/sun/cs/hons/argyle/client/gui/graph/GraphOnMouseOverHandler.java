package za.ac.sun.cs.hons.argyle.client.gui.graph;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.events.OnMouseOverHandler;

public class GraphOnMouseOverHandler extends OnMouseOverHandler {
    private final Label label;

    GraphOnMouseOverHandler(Label label) {
	this.label = label;
    }

    @Override
    public void onMouseOverEvent(OnMouseOverEvent event) {
	int row = event.getRow();
	int column = event.getColumn();
	StringBuffer b = new StringBuffer();
	b.append(" row: ");
	b.append(row);
	b.append(", column: ");
	b.append(column);
	label.setText("Mouse over " + b.toString());
    }

}
