package za.ac.sun.cs.hons.minke.client.gui.popup;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class LocationPopup extends FocusedPopupPanel {

	interface Binder extends UiBinder<Widget, LocationPopup> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	Button mapButton, cancelButton;
	@UiField(provided = true)
	SuggestBox locationBox;

	private MultiWordSuggestOracle locationOracle;
	private WebPage webPage;
	private EntityNameMap locations;

	public LocationPopup(WebPage webPage) {
		super(false);
		initSuggestBoxes();
		setWebPage(webPage);
		add(binder.createAndBindUi(this));
	}

	private void initSuggestBoxes() {
		locationOracle = new MultiWordSuggestOracle();
		locationBox = new SuggestBox(locationOracle);
	}

	@Override
	public void hide() {
		super.hide();
		if (locationBox != null) {
			locationBox.setText("");
		}
	}

	public void addLocations(EntityNameMap cities) {
		this.locations = cities;
		locationOracle.addAll(cities.getNames());
	}

	@UiHandler("mapButton")
	void handleClick(ClickEvent e) {
		Long locID;
		if ((locID = locations.getID(locationBox.getText())) != null) {
			webPage.requestMap(locID);
			hide();
		}

	}

	@UiHandler("cancelButton")
	void cancelClicked(ClickEvent event) {
		hide();
	}

	public void setWebPage(WebPage webPage) {
		this.webPage = webPage;
	}

}
