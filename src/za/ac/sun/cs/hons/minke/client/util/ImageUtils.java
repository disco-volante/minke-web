package za.ac.sun.cs.hons.minke.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class ImageUtils {
	public interface Images extends ClientBundle, Tree.Resources {
		@Source("images/info.png")
		ImageResource info();

		@Source("images/filter.png")
		ImageResource browsing();

		@Source("images/shop.png")
		ImageResource shopping();

		@Source("images/map.png")
		ImageResource map();

		@Source("images/loader.gif")
		ImageResource loader();

		@Source("images/chart.png")
		ImageResource graph();

		@Source("images/ic_launcher.png")
		ImageResource minke();

		@Source("images/remove.png")
		ImageResource remove();

		@Source("images/up.png")
		ImageResource up();

		@Source("images/down.png")
		ImageResource down();

		@Source("images/settings.png")
		ImageResource admin();
	}

	public static Image getImage(ImageResource imageProto) {
		return new Image(imageProto);
	}

	public static Image getImage(ImageResource imageProto, final String title) {
		final PopupPanel p = new PopupPanel(true);
		p.add(new Label(title));
		p.hide();
		Image img = new Image(imageProto);
		img.addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(MouseOverEvent event) {
				Widget source = (Widget) event.getSource();
				int x = source.getAbsoluteLeft() + 10;
				int y = source.getAbsoluteTop() + 10;
				p.setPopupPosition(x, y);
				p.show();
			}
		});
		img.addMouseOutHandler(new MouseOutHandler() {

			@Override
			public void onMouseOut(MouseOutEvent arg0) {
				if (p != null) {
					p.hide();
				}
			}
		});
		return img;
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

	public static String imageHTML(ImageResource imageProto) {
		return AbstractImagePrototype.create(imageProto).getHTML();
	}

}
