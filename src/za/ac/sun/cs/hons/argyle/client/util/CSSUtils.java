package za.ac.sun.cs.hons.argyle.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;

/**
 * A Utility class used to apply css rules to the system.
 * 
 * @author godfried
 * 
 */
public class CSSUtils {
    /**
     * An interface used to specify the css which will be used in the system
     * 
     * @author godfried
     * 
     */
    interface GlobalResources extends ClientBundle {
	@NotStrict
	@Source("global.css")
	CssResource css();
    }

    /**
     * An interface providing a selection rule for a table row.
     * 
     * @author godfried
     * 
     */
    public interface SelectionStyle extends CssResource {
	String selectedRow();
    }

    /**
     * Injects the css rules for the system.
     */
    public static void addCSS() {
	GWT.<GlobalResources> create(GlobalResources.class).css()
		.ensureInjected();
    }
}
