package za.ac.sun.cs.hons.minke.client.gui.popup;

import za.ac.sun.cs.hons.minke.client.gui.ViewTree;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;
import za.ac.sun.cs.hons.minke.client.util.Utils;

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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

public class PasswordPopup extends FocusedPopupPanel {
	interface Binder extends UiBinder<Widget, PasswordPopup> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	PasswordTextBox password;
	@UiField
	Button confirmButton;
	private ViewTree tree;

	public PasswordPopup(ViewTree _tree) {
		super(false);
		add(binder.createAndBindUi(this));
		this.tree = _tree;
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				password.setFocus(true);
			}
		});
	}

	@UiHandler("password")
	void passwordPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			confirmClicked(null);
		} else if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			cancelClicked(null);
		}
	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		tree.setHome();
		hide();
	}

	@UiHandler("confirmButton")
	void confirmClicked(ClickEvent event) {
		hide();
		if (!password.getText().equals(Utils.PASSWORD)) {
			GuiUtils.showError("Incorrect Password",
					"The password you enterred is not valid.");
			tree.setHome();
		} else {
			tree.showAdmin();
		}
	}

}
