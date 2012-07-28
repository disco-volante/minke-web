package za.ac.sun.cs.hons.argyle.client.gui.popup;

import za.ac.sun.cs.hons.argyle.client.util.GuiUtils;

import com.google.gwt.user.client.ui.PopupPanel;

public class FocusedPopupPanel extends PopupPanel {
    public FocusedPopupPanel(boolean autohide) {
	super(autohide);
	hide();
	setAnimationEnabled(true);

    }

    @Override
    public void hide() {
	super.hide();
	GuiUtils.uncover();
    }

    @Override
    public void center() {
	GuiUtils.cover();
	super.center();
    }

    @Override
    public void show() {
	GuiUtils.cover();
	super.show();
    }
}
