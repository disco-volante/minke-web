package za.ac.sun.cs.hons.minke.client.util;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.regexp.shared.RegExp;

public class Constants {
	public static final List<String> entities = Arrays.asList(new String[] {
			"Category", "Product", "Brand", "BranchProduct", "Branch", "Store",
			"CityLocation", "City", "Province", "Country" });
	public static final String CATEGORY = "Category";
	public static final String PRODUCT = "Product";
	public static final String BRAND = "Brand";
	public static final String BRANCHPRODUCT = "BranchProduct";
	public static final String BRANCH = "Branch";
	public static final String STORE = "Store";
	public static final String CITYLOCATION = "CityLocation";
	public static final String CITY = "City";
	public static final String PROVINCE = "Province";
	public static final String COUNTRY = "Country";
	public static final String DATEPRICE = "DatePrice";
	public static final String LOCATION = "Location";
	public static final String DATE = "Date";
	public static final String PRICE = "Price";
	public static final String NAME = "Name";
	public static final String LAT = "Latitude";
	public static final String LON = "Longitude";
	public static final String SIZE = "Size";
	public static final String MEASURE = "Measure";
	public static final RegExp DECIMALS_0 = RegExp.compile("^\\d+(\\.\\d{0,2})?$");
	public static final RegExp INTS = RegExp.compile("([1-9][0-9]*)");
	public static final RegExp STRING = RegExp.compile("[a-zA-ZäöüßÄÖÜ\\s'-,]+");
	public static final RegExp DECIMALS_1 = RegExp.compile("^-?\\d+(\\.\\d{0,10})?$");

}
