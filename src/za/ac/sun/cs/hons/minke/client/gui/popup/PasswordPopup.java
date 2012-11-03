package za.ac.sun.cs.hons.minke.client.gui.popup;

import za.ac.sun.cs.hons.minke.client.gui.ViewTree;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;
import za.ac.sun.cs.hons.minke.client.util.Utils;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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
		super(true);
		add(binder.createAndBindUi(this));
		this.tree = _tree;
	}

	@UiHandler("password")
	void locationPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			confirmClicked(null);
		}
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
