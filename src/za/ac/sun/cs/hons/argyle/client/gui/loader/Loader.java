package za.ac.sun.cs.hons.argyle.client.gui.loader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class Loader extends PopupPanel {
    interface Binder extends UiBinder<Widget, Loader> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    public Loader() {
	super(false);
	hide();
	add(binder.createAndBindUi(this));
	setAnimationEnabled(true);
	setStyleName(".transparent");
    }
}