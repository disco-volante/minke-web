package za.ac.sun.cs.hons.argyle.client.gui.graph;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.visualization.client.events.OnMouseOutHandler;

public class GraphOnMouseOutHandler extends OnMouseOutHandler {

    private final Label label;

    GraphOnMouseOutHandler(Label label) {
	this.label = label;
    }

    @Override
    public void onMouseOutEvent(OnMouseOutEvent event) {
	StringBuffer b = new StringBuffer();
	b.append(" row: ");
	b.append(event.getRow());
	b.append(", column: ");
	b.append(event.getColumn());
	label.setText("Mouse out of " + b.toString());
    }

}
