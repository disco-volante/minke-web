package za.ac.sun.cs.hons.minke.client.gui.popup;

import java.util.HashSet;

import za.ac.sun.cs.hons.minke.client.gui.graph.HistoryGraph;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ChangeGraphPopup extends FocusedPopupPanel implements KeyPressHandler{
	interface Binder extends UiBinder<Widget, ChangeGraphPopup> {
	}

	@UiField
	Button cancelButton, graphButton;
	@UiField
	VerticalPanel checkboxList;

	private static final Binder binder = GWT.create(Binder.class);
	private HistoryGraph graph;
	private HashSet<BranchProduct> items;

	public ChangeGraphPopup(boolean autohide, HistoryGraph graph) {
		super(autohide);
		add(binder.createAndBindUi(this));
		this.graph = graph;
	}

	public void setItems() {
		checkboxList.clear();
		items = new HashSet<BranchProduct>();
		for (final BranchProduct item : graph.getItems().keySet()) {
			if (item != null) {
				final CheckBox checkBox = new CheckBox(item.toString());
				if(graph.showing(item)){
					items.add(item);
					checkBox.setValue(true);
				}else{
					checkBox.setValue(false);
				}
				checkboxList.add(checkBox);
				checkBox.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent arg0) {
						if (checkBox.getValue()) {
							items.add(item);
						}
						else{
							items.remove(item);
						}
					}
				});
			}

		}
	}

	@UiHandler("graphButton")
	void itemClicked(ClickEvent event) {
		if(items.size() == 0){
			GuiUtils.showError("Selection Error", "No Product Selected");
		}
		else{
			graph.changeHistories(items);
		}
		hide();
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}
	

	@Override
	public void onKeyPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			itemClicked(null);
		}	else if(kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE){
			cancelClicked(null);
		}
	}

}
