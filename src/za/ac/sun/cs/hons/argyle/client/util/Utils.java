package za.ac.sun.cs.hons.argyle.client.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;

public class Utils {

    private static int loaded = 0;

    public static String toDirections(GPSCoords origin, GPSCoords destination) {
	return "from: " + origin.getLatitude() + ", " + origin.getLongitude()
		+ " to: " + destination.getLatitude() + ", "
		+ destination.getLongitude();
    }

    public static double calcTotal(HashSet<BranchProduct> branchProducts,
	    HashMap<Long, Integer> products) {
	double total = 0;
	for (BranchProduct bp : branchProducts) {
	    total += bp.getDatePrice().getPrice()
		    * products.get(bp.getProductID());
	}
	return total;
    }

    public static LatLng coordsConvert(GPSCoords coords) {
	return coordsConvert(coords.getLatitude(), coords.getLongitude());
    }

    public static LatLng coordsConvert(double latitude, double longitude) {
	return LatLng.newInstance(latitude, longitude);
    }

    public static GPSCoords coordsConvert(LatLng coords) {
	return new GPSCoords(coords.getLatitude(), coords.getLongitude());
    }

    public static DataTable toDataTable(
	    HashMap<BranchProduct, HashSet<DatePrice>> priceHistories) {
	DataTable data = DataTable.create();
	HashMap<Date, Integer> dates = new HashMap<Date, Integer>();
	data.addColumn(ColumnType.DATETIME, "Date");
	for (Entry<BranchProduct, HashSet<DatePrice>> productHistory : priceHistories
		.entrySet()) {
	    int col = data.addColumn(ColumnType.NUMBER, productHistory.getKey()
		    .toString());
	    for (DatePrice price : productHistory.getValue()) {
		Integer row = dates.get(price.getDate());
		if (row == null) {
		    row = data.addRow();
		    dates.put(price.getDate(), row);
		    data.setValue(row, 0, price.getDate());
		}
		data.setValue(row, col, price.getPrice());
	    }
	}
	return data;
    }

    public static void addLoaderPanel() {
	SimplePanel loader = new SimplePanel();
	loader.ensureDebugId("loadingMessage");
	RootPanel.get().add(loader);
    }

    public static void incLoaded() {
	loaded++;
	if (loaded == 3) {
	    GuiUtils.hideLoader();
	}
    }

    public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
	    Map<K, V> map) {
	SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
		new Comparator<Map.Entry<K, V>>() {
		    @Override
		    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
			return -e1.getValue().compareTo(e2.getValue());
		    }
		});
	sortedEntries.addAll(map.entrySet());
	return sortedEntries;
    }

}
