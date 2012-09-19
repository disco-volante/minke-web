package za.ac.sun.cs.hons.minke.client.gui.graph;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.visualization.client.Selectable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.events.SelectHandler;

class GraphSelectHandler extends SelectHandler {
	private final Selectable viz;

	GraphSelectHandler(Selectable viz) {
		this.viz = viz;
	}

	@Override
	public void onSelect(SelectEvent event) {
		StringBuffer b = new StringBuffer();
		JsArray<Selection> s = getSelections();
		for (int i = 0; i < s.length(); ++i) {
			if (s.get(i).isCell()) {
				b.append("cell");
				b.append(s.get(i).getRow());
				b.append(":");
				b.append(s.get(i).getColumn());
			} else if (s.get(i).isRow()) {
				b.append("row");
				b.append(s.get(i).getRow());
			} else {
				b.append("column");
				b.append(s.get(i).getColumn());
			}
		}
		viz.setSelections(viz.getSelections());
	}

	private JsArray<Selection> getSelections() {
		return viz.getSelections();
	}
}
