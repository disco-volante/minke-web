package za.ac.sun.cs.hons.minke.client.util;

import za.ac.sun.cs.hons.minke.client.gui.loader.Loader;
import za.ac.sun.cs.hons.minke.client.gui.popup.ErrorPopup;

public class GuiUtils {
	private static ErrorPopup error;
	private static Loader loader;

	public static void init() {
		loader = new Loader();
		error = new ErrorPopup();
		hideLoader();
	}

	public static void showLoader() {
		loader.center();
	}

	public static void hideLoader() {
		loader.hide();
	}

	public static void showError(String title, String message) {
		error.setTitle(title);
		error.setContent(message);
		error.center();
	}

}
