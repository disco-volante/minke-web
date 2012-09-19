package za.ac.sun.cs.hons.minke.client.gui.popup;

import com.google.gwt.user.client.ui.PopupPanel;

public class FocusedPopupPanel extends PopupPanel {

	public FocusedPopupPanel(boolean autohide) {
		super(autohide);
		setModal(!autohide);
		setAnimationEnabled(true);
		setGlassEnabled(true);
		hide();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void center() {
		super.center();
	}

	@Override
	public void show() {
		super.show();
	}
}
