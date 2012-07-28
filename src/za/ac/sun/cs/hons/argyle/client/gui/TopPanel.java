package za.ac.sun.cs.hons.argyle.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * The top banner of the {@link WebPage} class.
 * 
 * @author godfried
 * 
 */
public class TopPanel extends Composite {
    /**
     * {@link UiBinder} for this class.
     * 
     * @author godfried
     * 
     */
    interface Binder extends UiBinder<Widget, TopPanel> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField
    Image		       image;

    /**
     * Creates an instance of this class.
     */
    public TopPanel() {
	initWidget(binder.createAndBindUi(this));
    }

    /**
     * Getter for the image.
     * 
     * @return this class's {@link Image} object
     */
    public Image getImage() {
	return image;
    }

    /**
     * Sets the image's width.
     * 
     * @param width
     *            the image's new width.
     */
    public void setImageWidth(String width) {
	image.setWidth(width);
    }

    /**
     * Sets the image's height.
     * 
     * @param height
     *            the image's new height.
     */
    public void setImageHeight(String height) {
	image.setHeight(height);
    }
}
