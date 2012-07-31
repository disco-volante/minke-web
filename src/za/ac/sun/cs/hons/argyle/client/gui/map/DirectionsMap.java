package za.ac.sun.cs.hons.argyle.client.gui.map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.DirectionResults;
import com.google.gwt.maps.client.geocode.Directions;
import com.google.gwt.maps.client.geocode.DirectionsCallback;
import com.google.gwt.maps.client.geocode.DirectionsPanel;
import com.google.gwt.maps.client.geocode.StatusCodes;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Provides a map centered on a given location and directions between an origin
 * and a destination location.
 * 
 * @author godfried
 * 
 */
public class DirectionsMap extends ResizeComposite {
    /**
     * UiBinder for the map interface.
     * 
     * @author godfried
     * 
     */
    interface Binder extends UiBinder<DockLayoutPanel, DirectionsMap> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField
    ScrollPanel holderPanel;
    @UiField
    MapWidget map;
    @UiField
    DirectionsPanel directionsPanel;

    /**
     * Constructs a new map with the given center point.
     * 
     * @param center
     *            the center of the map.
     */
    public DirectionsMap() {
	initWidget(binder.createAndBindUi(this));
    }

    /**
     * Sets center point.
     * 
     * @param center
     */
    public void setCenter(LatLng center) {
	// map.setZoomLevel(15);
	map.setCenter(center);
    }

    /**
     * Loads directions from the {@link String} query.
     * 
     * @param directionsQuery
     *            A query providing an origin and a destination location in the
     *            format: from: *origin info* to: *destination info*.
     */
    public void loadDirections(String directionsQuery) {
	holderPanel.remove(directionsPanel);
	directionsPanel = new DirectionsPanel();
	DirectionQueryOptions opts = new DirectionQueryOptions(map,
		directionsPanel);
	Directions.load(directionsQuery, opts, new DirectionsCallback() {
	    public void onFailure(int statusCode) {
		Window.alert("Failed to load directions: Status "
			+ StatusCodes.getName(statusCode) + " " + statusCode);
	    }

	    public void onSuccess(DirectionResults result) {
		GWT.log("Successfully loaded directions.", null);
	    }
	});
	holderPanel.add(directionsPanel);
    }
}
