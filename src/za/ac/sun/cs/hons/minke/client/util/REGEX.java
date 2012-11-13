package za.ac.sun.cs.hons.minke.client.util;

import com.google.gwt.regexp.shared.RegExp;

public class REGEX {
	public static final RegExp DECIMALS_0 = RegExp.compile("^\\d+(\\.\\d{0,2})?$");
	public static final RegExp INTS = RegExp.compile("([1-9][0-9]*)");
	public static final RegExp STRING = RegExp.compile("^[a-zA-ZäöüßÄÖÜ\\s'\\-,]+$");
	public static final RegExp DECIMALS_1 = RegExp.compile("^\\d+(\\.\\d{0,10})?$");
}
