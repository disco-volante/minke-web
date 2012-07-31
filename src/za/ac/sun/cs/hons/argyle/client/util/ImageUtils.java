package za.ac.sun.cs.hons.argyle.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class ImageUtils {
    public interface Images extends ClientBundle, Tree.Resources {
	@Source("images/info.png")
	ImageResource info();

	@Source("images/banner-small.png")
	@ImageOptions(repeatStyle = RepeatStyle.Both)
	ImageResource banner();

	@Source("images/browse.png")
	ImageResource browsing();

	@Source("images/shop.png")
	ImageResource shopping();

	@Source("images/map.png")
	ImageResource map();

	@Source("images/loader.gif")
	ImageResource loader();

	@Source("images/graph.png")
	ImageResource graph();

	@Source("images/minke.png")
	ImageResource minke();

	@Source("images/remove.png")
	ImageResource remove();

	@Source("images/up.png")
	ImageResource up();

	@Source("images/down.png")
	ImageResource down();
    }

    public static String imageItemHTML(ImageResource imageProto) {
	return AbstractImagePrototype.create(imageProto).getHTML();
    }

    public static String imageItemHTML(ImageResource imageProto, String title) {
	return AbstractImagePrototype.create(imageProto).getHTML() + " "
		+ title;
    }

    public static Images getImages() {
	return GWT.create(Images.class);
    }

    public static void showLoadingBanner(String message, Widget widget) {
	DOM.setInnerHTML(widget.getElement(), message
		+ "&nbsp;<img src=\"images/loader.gif\" height=15>&nbsp;");
    }

    public static void hideLoadingBanner(Widget widget) {
	DOM.setInnerHTML(widget.getElement(), "<br>");
    }

}
