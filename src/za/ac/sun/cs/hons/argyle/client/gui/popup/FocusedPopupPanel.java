package za.ac.sun.cs.hons.argyle.client.gui.popup;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widgetideas.client.GlassPanel;

public class FocusedPopupPanel extends PopupPanel {
    private GlassPanel glass;

    public FocusedPopupPanel(boolean autohide) {
	super(autohide);
	setAnimationEnabled(true);
	glass = new GlassPanel(autohide);
	DOM.setStyleAttribute(glass.getElement(), "width", "100%");
	DOM.setStyleAttribute(glass.getElement(), "height", "100%");
	RootPanel.get().add(glass);
	hide();
    }

    @Override
    public void hide() {
	super.hide();
	RootPanel.get().remove(glass);
    }

    @Override
    public void center() {
	RootPanel.get().add(glass);
	super.center();
    }

    @Override
    public void show() {
	RootPanel.get().add(glass);
	super.show();
    }
}
