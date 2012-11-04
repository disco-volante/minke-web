package za.ac.sun.cs.hons.minke.client.gui.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ErrorPopup extends FocusedPopupPanel implements KeyPressHandler {
	interface Binder extends UiBinder<Widget, ErrorPopup> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Label titleLbl, contentLbl;
	@UiField
	Button closeButton;

	public ErrorPopup() {
		super(true);
		add(binder.createAndBindUi(this));
	}

	@UiHandler("closeButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	@Override
	public void setTitle(String title) {
		titleLbl.setText(title);
	}

	public void setContent(String content) {
		contentLbl.setText(content);
	}

	@Override
	public void onKeyPress(KeyPressEvent kpe) {
		if (kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER
				|| kpe.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			cancelClicked(null);
		}
	}
}
