package za.ac.sun.cs.hons.argyle.client.util;

import za.ac.sun.cs.hons.argyle.client.gui.loader.Loader;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.widgetideas.client.GlassPanel;

public class GuiUtils {
    private static Loader loader;
    private static GlassPanel glass;
    private static DialogBox error;
    private static HTML errorMessage;

    public static void init() {
	loader = new Loader();
	glass = new GlassPanel(false);
	error = new DialogBox();
	VerticalPanel contents = new VerticalPanel();
	errorMessage = new HTML();
	Button closeBtn = new Button("Close");
	closeBtn.addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		error.hide();
	    }
	});
	SimplePanel holder = new SimplePanel();
	holder.add(closeBtn);
	contents.add(errorMessage);
	contents.add(holder);
	error.setWidget(contents);
	error.hide();
    }

    public static void showLoader() {
	loader.center();
    }

    public static void hideLoader() {
	loader.hide();
    }

    public static void cover() {
	RootPanel.get().add(glass, 0, 0);
    }

    public static void uncover() {
	RootPanel.get().remove(glass);
    }

    public static void showErrorDialog(String string) {
	errorMessage.setHTML(string);
	error.center();

    }

}
