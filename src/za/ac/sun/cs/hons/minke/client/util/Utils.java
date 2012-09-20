package za.ac.sun.cs.hons.minke.client.util;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import za.ac.sun.cs.hons.minke.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;

public class Utils {

	private static int loaded = 0;

	/**
	 * Converts {@link GPSCoords} to a directions {@link String} which can be
	 * parsed by the Maps API.
	 * 
	 * @param origin
	 *            the origin coordinates.
	 * @param destination
	 *            the destination coordinates.
	 * @return a String containing the coordinates in a format the Maps API can
	 *         use.
	 */
	public static String toDirections(GPSCoords origin, GPSCoords destination) {
		return "from: " + origin.getLatitude() + ", "
				+ origin.getLongitude() + " to: "
				+ destination.getLatitude() + ", "
				+ destination.getLongitude();
	}

	/**
	 * Calculates the total cost of a shopping list.
	 * 
	 * @param branchProducts
	 *            The set of {@link BranchProduct}s found in the shopping list.
	 * @param products
	 *            The quantities of each {@link Product}.
	 * @return the total cost of the shopping list.
	 */
	public static double calcTotal(HashSet<BranchProduct> branchProducts,
			HashMap<Long, Integer> products) {
		double total = 0;
		for (BranchProduct bp : branchProducts) {
			total += bp.getDatePrice().getActualPrice()
					* products.get(bp.getProductID());
		}
		return total;
	}

	/**
	 * Converts {@link GPSCoords} to a {@link LatLng}.
	 * 
	 * @param coords
	 *            th
	 * @return
	 */
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
			HashMap<BranchProduct, List<DatePrice>> priceHistories) {
		DataTable data = DataTable.create();
		HashMap<Date, Integer> dates = new HashMap<Date, Integer>();
		data.addColumn(ColumnType.DATETIME, "Date");
		for (Entry<BranchProduct, List<DatePrice>> productHistory : priceHistories
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
				data.setValue(row, col, price.getActualPrice());
			}
		}
		return data;
	}

	public static void incLoaded() {
		loaded++;
		if (loaded >= 3) {
			GuiUtils.hideLoader();
		}
	}

	public static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
				new Comparator<Map.Entry<K, V>>() {
					@Override
					public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
						return e2.getValue().compareTo(e1.getValue());
					}
				});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}

}
