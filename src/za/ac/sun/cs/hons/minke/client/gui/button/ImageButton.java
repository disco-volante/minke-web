package za.ac.sun.cs.hons.minke.client.gui.button;

import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Button;

public abstract class ImageButton extends Button {
	public ImageButton(ImageResource img) {
		super();
		setStyleName("ImageButton");
		setHTML(ImageUtils.imageHTML(img));
		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clickAction();
			}

		});

	}

	protected abstract void clickAction();
}
