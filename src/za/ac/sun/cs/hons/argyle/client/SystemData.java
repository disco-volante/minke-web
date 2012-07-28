package za.ac.sun.cs.hons.argyle.client;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Store;

/**
 * This class is used to store data obtained from RPC calls.
 * 
 * @author godfried
 * 
 */
public class SystemData {
    private HashMap<String, ProductCategory>	productCategories;
    private HashMap<String, City>		   cities;
    private HashMap<String, Product>		products;
    private HashMap<String, BranchProduct>	  branchProducts;
    private HashMap<Branch, HashSet<BranchProduct>> branches;

    private boolean				 loaded;
    private HashMap<Long, Integer>		  addedProducts;

    public HashMap<String, ProductCategory> getProductCategories() {
	return productCategories;
    }

    public void setProductCategories(
	    HashMap<String, ProductCategory> productCategories) {
	this.productCategories = productCategories;
    }

    public HashMap<String, City> getCities() {
	return cities;
    }

    public void setCities(HashMap<String, City> cities) {
	this.cities = cities;
    }

    public HashMap<String, Product> getProducts() {
	return products;
    }

    public void setProducts(HashMap<String, Product> products) {
	this.products = products;
    }

    public boolean getLoaded() {
	return loaded;
    }

    public void setLoaded(boolean loaded) {
	this.loaded = loaded;
    }

    public HashMap<String, BranchProduct> getBranchProducts() {
	return branchProducts;
    }

    public void setBranchProducts(HashMap<String, BranchProduct> branchProducts) {
	this.branchProducts = branchProducts;
    }

    public HashMap<Branch, HashSet<BranchProduct>> getBranches() {
	return branches;
    }

    public void setBranches(HashMap<Branch, HashSet<BranchProduct>> result) {
	this.branches = result;

    }

    public IsEntity[] getDBData() {
	IsEntity[] entities = new IsEntity[109];
	ProductCategory decafTea = new ProductCategory(1234, "Decaf Tea",
		"Tea", "Consumables");
	ProductCategory instantCoffee = new ProductCategory(1235,
		"Instant Coffee", "Coffee", "Consumables");
	ProductCategory coffeeBeans = new ProductCategory(1236, "Coffee Beans",
		"Coffee", "Consumables");

	entities[0] = decafTea;
	entities[1] = instantCoffee;
	entities[2] = coffeeBeans;

	Brand twinings = new Brand(1212, "Twinings");
	Brand nestle = new Brand(1213, "Nestle");
	Brand illy = new Brand(1214, "Illy");
	Brand clipper = new Brand(1215, "Clipper");
	Brand caturra = new Brand(1216, "Caturra");
	entities[30] = twinings;
	entities[31] = nestle;
	entities[32] = illy;
	entities[33] = clipper;
	entities[34] = caturra;
	Product earlgray = new Product(1237, "Earl Grey", twinings, decafTea,
		25, "ea");
	Product ricoffy = new Product(1238, "Ricoffy", nestle, instantCoffee,
		1.5, "kg");
	Product classic = new Product(1239, "Classic", nestle, instantCoffee,
		1, "kg");
	Product regular = new Product(1244, "Regular", illy, coffeeBeans, 250,
		"g");
	Product arabica = new Product(1254, "Arabica", clipper, coffeeBeans,
		100, "g");
	Product classico = new Product(1264, "Classico Connoisseur", caturra,
		coffeeBeans, 500, "g");
	entities[3] = twinings;
	entities[4] = ricoffy;
	entities[5] = classic;
	entities[6] = illy;
	entities[7] = clipper;
	entities[8] = caturra;

	City stellenbosch = new City(1274, new GPSCoords(-33.9200, 18.8600),
		"Stellenbosch", "Western Cape", "South Africa");
	City capeTown = new City(1284, new GPSCoords(-33.9767, 18.4244),
		"Cape Town", "Western Cape", "South Africa");
	City somersetWest = new City(1294, new GPSCoords(-34.0833, 18.8500),
		"Somerset West", "Western Cape", "South Africa");
	City paarl = new City(1334, new GPSCoords(-33.7242, 18.9558), "Paarl",
		"Western Cape", "South Africa");
	City joburg = new City(1434, new GPSCoords(-26.2000, 28.0667),
		"Johannesburg", "Gauteng", "South Africa");
	City pretoria = new City(1534, new GPSCoords(-25.7256, 28.2439),
		"Pretoria", "Gauteng", "South Africa");
	entities[9] = stellenbosch;
	entities[10] = capeTown;
	entities[11] = somersetWest;
	entities[12] = paarl;
	entities[13] = joburg;
	entities[14] = pretoria;
	Location dieBoord = new Location(1634, new GPSCoords(-33.9447319,
		18.8500055), stellenbosch);
	entities[15] = dieBoord;
	Store spar = new Store(1734, "Spar");
	entities[16] = spar;
	Branch sparDieBoord = new Branch(1834, "Spar Die Boord", spar, dieBoord);
	entities[17] = sparDieBoord;
	BranchProduct classicSpar = new BranchProduct(7234, classic,
		sparDieBoord, null);
	BranchProduct ricoffySpar = new BranchProduct(8234, ricoffy,
		sparDieBoord, null);
	BranchProduct earlgraySpar = new BranchProduct(9234, earlgray,
		sparDieBoord, null);
	BranchProduct regularSpar = new BranchProduct(10234, regular,
		sparDieBoord, null);
	BranchProduct arabicaSpar = new BranchProduct(11234, arabica,
		sparDieBoord, null);
	BranchProduct classicoSpar = new BranchProduct(12234, classico,
		sparDieBoord, null);
	long year2010 = 1102304000000L;
	long month = 2592000000L;
	for (int i = 35; i < 102; i += 6) {
	    entities[24] = entities[i] = new DatePrice(930 + i, new Date(
		    year2010 + i * month), 100 + i, classicSpar.getID());
	    entities[25] = entities[i + 1] = new DatePrice(93 + i, new Date(
		    year2010 + i * month), 90 + i, ricoffySpar.getID());
	    entities[26] = entities[i + 2] = new DatePrice(9830 + i, new Date(
		    year2010 + (i + 6) * month), 80 + i, earlgraySpar.getID());
	    entities[27] = entities[i + 3] = new DatePrice(99930 + i, new Date(
		    year2010 + (i + 12) * month), 120 + i, regularSpar.getID());
	    entities[28] = entities[i + 4] = new DatePrice(9630 + i, new Date(
		    year2010 + (i + 20) * month), 70 + i, arabicaSpar.getID());
	    entities[29] = entities[i + 5] = new DatePrice(9430 + i, new Date(
		    year2010 + (i + 26) * month), 75 + i, classicoSpar.getID());
	}
	classicSpar.setDatePrice((DatePrice) entities[24]);
	ricoffySpar.setDatePrice((DatePrice) entities[25]);
	earlgraySpar.setDatePrice((DatePrice) entities[26]);
	regularSpar.setDatePrice((DatePrice) entities[27]);
	arabicaSpar.setDatePrice((DatePrice) entities[28]);
	classicoSpar.setDatePrice((DatePrice) entities[29]);

	entities[18] = classicSpar;
	entities[19] = ricoffySpar;
	entities[20] = earlgraySpar;
	entities[21] = regularSpar;
	entities[22] = arabicaSpar;
	entities[23] = classicoSpar;
	return entities;
    }

    public HashMap<Long, Integer> getAddedProducts() {
	return addedProducts;
    }

    public void setAddedProducts(HashMap<Long, Integer> addedProducts) {
	this.addedProducts = addedProducts;
    }
}