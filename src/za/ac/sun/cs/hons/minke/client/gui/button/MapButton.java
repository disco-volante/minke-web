package za.ac.sun.cs.hons.minke.client.gui.button;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.util.ImageUtils;

public class MapButton extends ImageButton {
    private WebPage webPage;
    private Object item;
    public MapButton(final Object item, WebPage webPage) {
	super(ImageUtils.getImages().map());
	this.item = item;
	this.webPage = webPage;
    }

    @Override
    protected void clickAction() {
	Branch branch = (Branch) item;
	webPage.showMap(branch.getLocation().getCoords());
    }

}
