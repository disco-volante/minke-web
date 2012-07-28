package za.ac.sun.cs.hons.argyle.client.util;

import za.ac.sun.cs.hons.argyle.client.gui.loader.Loader;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.widgetideas.client.GlassPanel;

public class GuiUtils {
    private static Loader     loader = new Loader();
    private static GlassPanel glass  = new GlassPanel(false);

    public static void showLoader(String message) {
	cover();
	loader.center();
    }

    public static void hideLoader() {
	uncover();
	loader.hide();
    }

    public static void cover() {
	RootPanel.get().add(glass, 0, 0);
    }

    public static void uncover() {
	RootPanel.get().remove(glass);
    }

}
