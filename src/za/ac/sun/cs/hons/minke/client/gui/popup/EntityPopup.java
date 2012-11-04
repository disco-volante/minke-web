package za.ac.sun.cs.hons.minke.client.gui.popup;

import za.ac.sun.cs.hons.minke.client.gui.table.DataViewer;
import za.ac.sun.cs.hons.minke.client.util.Constants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class EntityPopup extends FocusedPopupPanel {
	interface Binder extends UiBinder<Widget, EntityPopup> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField(provided = true)
	SuggestBox entityBox;
	@UiField
	Button confirmButton;
	private DataViewer viewer;
	private MultiWordSuggestOracle entityOracle;

	public EntityPopup(DataViewer viewer) {
		super(true);
		entityOracle = new MultiWordSuggestOracle();
		entityOracle.addAll(Constants.entities);
		entityBox = new SuggestBox(entityOracle);
		add(binder.createAndBindUi(this));
		this.viewer = viewer;
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				entityBox.setFocus(true);
			}
		});

	}
	
	@UiHandler("entityBox")
	void entityPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			confirmClicked(null);
		}
	}


	@UiHandler("confirmButton")
	void confirmClicked(ClickEvent event) {
		if (Constants.entities.contains(entityBox.getText().toString())) {
			viewer.getEntities(entityBox.getText().toString());
			hide();
		}
	}

}
