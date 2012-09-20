package za.ac.sun.cs.hons.minke.server.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import za.ac.sun.cs.hons.minke.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Country;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Province;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

public class EntityToXMLConverter {

	public static String addTag(String tag, String value) {
		return ("<" + tag + ">" + value + "</" + tag + ">");
	}

	public static String convert(City c) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", c.getName().replaceAll("&", "and")));
		content.append(addTag("id", String.valueOf(c.getID())));
		content.append(addTag("latitude",
				String.valueOf(c.getCoords().getLatitude())));
		content.append(addTag("longitude",
				String.valueOf(c.getCoords().getLongitude())));
		content.append(addTag("provinceName",
				String.valueOf(c.getProvince().getName())));
		content.append(addTag("countryName",
				String.valueOf(c.getProvince().getCountry().getName())));
		String ret = addTag("city", content.toString());
		return ret;
	}

	public static String convertCities(Iterable<City> cities) {
		StringBuilder content = new StringBuilder();
		for (City c : cities) {
			content.append(convert(c));
		}
		String ret = addTag("cities", content.toString());
		return ret;
	}

	public static String convert(Province p) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", p.getName().replaceAll("&", "and")));
		content.append(addTag("id", String.valueOf(p.getID())));
		content.append(addTag("countryName",
				String.valueOf(p.getCountry().getName())));
		String ret = addTag("province", content.toString());
		return ret;
	}

	public static String convertProvinces(Iterable<Province> provinces) {
		StringBuilder content = new StringBuilder();
		for (Province p : provinces) {
			content.append(convert(p));
		}
		String ret = addTag("provinces", content.toString());
		return ret;
	}

	public static String convert(Country c) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", c.getName().replaceAll("&", "and")));
		content.append(addTag("id", String.valueOf(c.getID())));
		String ret = addTag("country", content.toString());
		return ret;
	}

	public static String convertCountries(Iterable<Country> countries) {
		StringBuilder content = new StringBuilder();
		for (Country c : countries) {
			content.append(convert(c));
		}
		String ret = addTag("countries", content.toString());
		return ret;
	}

	public static String convertLocations(Iterable<City> cities,
			List<Province> provinces, List<Country> countries) {
		String ret = convertCities(cities) + convertProvinces(provinces)
				+ convertCountries(countries);
		ret = addTag("locations", ret);
		return ret;
	}

	public static String convert(Product p) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", p.getName().replaceAll("&", "and")));
		content.append(addTag("id", String.valueOf(p.getID())));
		content.append(addTag("brandName", p.getBrand().toString()));
		content.append(addTag("size", String.valueOf(p.getSize())));
		content.append(addTag("measure", p.getMeasurement().toString()));
		String ret = addTag("product", content.toString());
		return ret;
	}

	public static String convertProducts(Iterable<Product> products) {
		StringBuilder content = new StringBuilder();
		for (Product p : products) {
			content.append(convert(p));
		}
		String ret = addTag("products", content.toString());
		return ret;
	}
	

	public static String convert(Category c) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", c.getName().replaceAll("&", "and")));
		content.append(addTag("id", String.valueOf(c.getID())));
		String ret = addTag("category", content.toString());
		return ret;
	}

	public static String convertCategories(Iterable<Category> categories) {
		StringBuilder content = new StringBuilder();
		for (Category c : categories) {
			content.append(convert(c));
		}
		String ret = addTag("categories", content.toString());
		return ret;
	}
	public static String convert(DatePrice dp) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("date", String.valueOf(dp.getDate().getTime())));
		content.append(addTag("id", String.valueOf(dp.getID())));
		content.append( addTag("price", String.valueOf(dp.getActualPrice())));
		String ret = addTag("datePrice", content.toString());
		return ret;
	}
	public static String convert(Iterable<DatePrice> dps) {
		StringBuilder content = new StringBuilder();
		for (DatePrice dp : dps) {
			content.append(convert(dp));
		}
		String ret = addTag("datePrices", content.toString());
		return ret;
	}


	public static String convert(BranchProduct bp, Iterable<DatePrice> dps) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("productName", bp.getProduct().getName()));
		content.append(addTag("id", String.valueOf(bp.getID())));
		content.append(addTag("brandName", bp.getProduct().getBrand()
				.toString()));
		content.append(addTag("size", String.valueOf(bp.getProduct().getSize())));
		content.append(addTag("measure", bp.getProduct().getMeasurement()
				.toString()));
		content.append(addTag("price",
				String.valueOf(bp.getDatePrice().getActualPrice())));
		content.append(addTag("date",
				String.valueOf(bp.getDatePrice().getDate().getTime())));
		content.append(addTag("branchName", bp.getBranch().toString()));
		if(dps != null){
			content.append(convert(dps));
		}
		String ret = addTag("branchProduct", content.toString());
		return ret;
	}
	public static String convertBranchProducts(
			Iterable<BranchProduct> branchProducts) {
		StringBuilder content = new StringBuilder();
		for (BranchProduct bp : branchProducts) {
			content.append(convert(bp, null));
		}
		String ret = addTag("branchProducts", content.toString());
		return ret;
	}


	public static String convertBranchProducts(
			Map<BranchProduct, List<DatePrice>> branchProducts) {
		StringBuilder content = new StringBuilder();
		for (Entry<BranchProduct, List<DatePrice>> bp : branchProducts.entrySet()) {
			content.append(convert(bp.getKey(), bp.getValue()));
		}
		String ret = addTag("branchProducts", content.toString());
		return ret;
	}

	public static String convert(Branch b, Map<BranchProduct, List<DatePrice>> products) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", b.getName()));
		content.append(addTag("id", String.valueOf(b.getID())));
		content.append(addTag("storeName", b.getStore().toString()));
		content.append(addTag("cityName", b.getLocation().getCity().toString()));
		content.append(addTag("latitude",
				String.valueOf(b.getLocation().getCoords().getLatitude())));
		content.append(addTag("longitude",
				String.valueOf(b.getLocation().getCoords().getLongitude())));
		if(products != null){
			content.append(convertBranchProducts(products));
		}
		String ret = addTag("branch", content.toString());
		return ret;
	}

	public static String convertBranches(Iterable<Branch> branches) {
		StringBuilder content = new StringBuilder();
		for (Branch b : branches) {
			content.append(convert(b,null));
		}
		String ret = addTag("branches", content.toString());
		return ret;
	}

	public static String convertBranchShopping(
			Map<Branch, HashMap<BranchProduct, List<DatePrice>>> result) {
		StringBuilder content = new StringBuilder();
		for(Entry<Branch, HashMap<BranchProduct, List<DatePrice>>> e : result.entrySet()){
			content.append(convert(e.getKey(),e.getValue()));
		}
		String ret = addTag("branches", content.toString());
		return ret;
	}

	public static String convertBrands(List<Brand> brands) {
		StringBuilder content = new StringBuilder();
		for(Brand b : brands){
			content.append(convert(b));
		}
		String ret = addTag("brands", content.toString());
		return ret;
	}

	private static String convert(Brand b) {
		StringBuilder content = new StringBuilder();
		content.append(addTag("name", b.getName()));
		content.append(addTag("id", String.valueOf(b.getID())));
		String ret = addTag("brand", content.toString());
		return ret;
	}





}
