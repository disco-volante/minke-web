package za.ac.sun.cs.hons.minke.client.gui.map;

import za.ac.sun.cs.hons.minke.client.gui.WebPage;
import za.ac.sun.cs.hons.minke.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.minke.client.util.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ResizeComposite;

/**
 * An graphical interface which displays {@link DirectionsMap} on the
 * {@link WebPage}.
 * 
 * @author godfried
 * 
 */
public class BranchLocation extends ResizeComposite  {
	/**
	 * {@link UiBinder} for this class.
	 * 
	 * @author godfried
	 * 
	 */
	interface Binder extends UiBinder<DockLayoutPanel, BranchLocation> {
	}

	private static final Binder binder = GWT.create(Binder.class);
	@UiField
	DockLayoutPanel mapPanel;
	@UiField
	Button correctionButton;
	private boolean loaded;

	protected LatLng center;
	protected GPSCoords origin, destination;
	private DirectionsMap map;
	private WebPage webPage;
	private Runnable mapBuilder = new Runnable() {
		@Override
		public void run() {
			drawMap();
			loadDirections(Utils.toDirections(origin, destination));
			centerMap(center);
		}

	};

	/**
	 * Creates the interface and loads the maps.
	 */
	public BranchLocation(WebPage webPage) {
		initWidget(binder.createAndBindUi(this));
		this.webPage = webPage;
		loadMaps();
	}

	/**
	 * Loads the maps and creates {@link Runnable}s used to draw them.
	 */
	private void loadMaps() {
		Runnable setLoaded = new Runnable() {
			@Override
			public void run() {
				setLoaded(true);
				initCoords();
				draw();
			}
		};
		Maps.loadMapsApi("AIzaSyAT02R_mlbQvA9h-IsF2ScozfognBagLu8", "2", true,
				setLoaded);
	}

	/**
	 * Draws the map.
	 * 
	 * @param center
	 *            the center of the map.
	 */
	protected void drawMap() {
		if (map != null) {
			mapPanel.remove(map);
		}
		map = new DirectionsMap();
		mapPanel.add(map);

	}

	protected void centerMap(LatLng center) {
		map.setCenter(center);

	}

	/**
	 * Loads directions for the map.
	 * 
	 * @param directions
	 *            the directions to be displayed with the map.
	 */
	protected void loadDirections(String directions) {
		map.loadDirections(directions);
		map.setCenter(center);
	}

	/**
	 * Initialises coordinates to default values.
	 */
	protected void initCoords() {
		setMapCenter(new GPSCoords(-33.9200, 18.8600));
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
		if (isLoaded()) {
			mapBuilder.run();
		}
	}

	/**
	 * Sets the map's center.
	 * 
	 * @param gpsCoords
	 *            the new map center.
	 */
	public void setMapCenter(GPSCoords gpsCoords) {
		if (isLoaded()) {
			this.center = Utils.coordsConvert(gpsCoords);
		}

	}

	@UiHandler("correctionButton")
	void correction(ClickEvent event) {
		webPage.system.setUserCoords(null);
		webPage.showMap(destination);
	}

	public boolean isLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

}
