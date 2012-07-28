package za.ac.sun.cs.hons.argyle.client.gui.map;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.util.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * An graphical interface which displays {@link DirectionsMap} on the
 * {@link WebPage}.
 * 
 * @author godfried
 * 
 */
public class BranchLocation extends Composite {
    /**
     * {@link UiBinder} for this class.
     * 
     * @author godfried
     * 
     */
    interface Binder extends UiBinder<Widget, BranchLocation> {
    }

    private static final Binder binder = GWT.create(Binder.class);
    @UiField
    SimplePanel		 mapPanel;
    private boolean	     loaded;
    private Runnable	    mapBuilder;
    private LatLng	      center;
    private GPSCoords	   origin, destination;
    private DirectionsMap       map;

    /**
     * Creates the interface and loads the maps.
     */
    public BranchLocation() {
	initWidget(binder.createAndBindUi(this));
	loadMaps();
    }

    /**
     * Loads the maps and creates {@link Runnable}s used to draw them.
     */
    private void loadMaps() {
	mapBuilder = new Runnable() {
	    @Override
	    public void run() {
		drawMap(center);
		loadDirections(Utils.toDirections(origin, destination));
	    }

	};
	Runnable setLoaded = new Runnable() {
	    @Override
	    public void run() {
		loaded = true;
		initCoords();
		draw();
	    }
	};
	Maps.loadMapsApi("", "2", true, setLoaded);
    }

    /**
     * Draws the map.
     * 
     * @param center
     *            the center of the map.
     */
    protected void drawMap(LatLng center) {
	if (map == null) {
	    map = new DirectionsMap(center);
	    mapPanel.add(map);
	} else {
	    map.setCenter(center);
	}
    }

    /**
     * Loads directions for the map.
     * 
     * @param directions
     *            the directions to be displayed with the map.
     */
    protected void loadDirections(String directions) {
	map.loadDirections(directions);
    }

    /**
     * Initialises coordinates to default values.
     */
    private void initCoords() {
	setMapCenter(Utils.coordsConvert(-33.9200, 18.8600));
	setDirectionCoords(new GPSCoords(-33.9200, 18.8600), new GPSCoords(
		-33.9447319, 18.8500055));
    }

    /**
     * Sets the direction coordinates.
     * 
     * @param origin
     * @param destination
     */
    public void setDirectionCoords(GPSCoords origin, GPSCoords destination) {
	this.origin = origin;
	this.destination = destination;
    }

    /**
     * Draws a map for the current {@link #center}.
     */
    public void draw() {
	if (loaded) {
	    mapBuilder.run();
	}
    }

    /**
     * Sets the map's center.
     * 
     * @param coords
     *            the new map center.
     */
    public void setMapCenter(LatLng coords) {
	this.center = coords;

    }

}
