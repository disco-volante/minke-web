package za.ac.sun.cs.hons.minke.client.gui.popup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ErrorPopup extends FocusedPopupPanel {
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
}
