package za.ac.sun.cs.hons.argyle.server;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;

import za.ac.sun.cs.hons.argyle.client.rpc.ClassService;
import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Store;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

public class ClassServiceImpl extends RemoteServiceServlet implements
	ClassService {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Override
    public boolean registerClasses() {
	DAOService.init();
	addData();
	return true;
    }

    public void addData() {
	ProductCategory decafTea = new ProductCategory("Decaf Tea", "Tea",
		"Consumables");
	ProductCategory instantCoffee = new ProductCategory("Instant Coffee",
		"Coffee", "Consumables");
	ProductCategory coffeeBeans = new ProductCategory("Coffee Beans",
		"Coffee", "Consumables");
	DAOService.productCategoryDAO.add(decafTea, instantCoffee, coffeeBeans);
	Brand twinings = new Brand("Twinings");
	Brand nestle = new Brand("Nestle");
	Brand illy = new Brand("Illy");
	Brand clipper = new Brand("Clipper");
	Brand caturra = new Brand("Caturra");
	DAOService.brandDAO.add(twinings, nestle, illy, clipper, caturra);

	Product earlgray = new Product("Earl Grey", twinings, decafTea, 25,
		"ea");
	Product ricoffy = new Product("Ricoffy", nestle, instantCoffee, 1.5,
		"kg");
	Product classic = new Product("Classic", nestle, instantCoffee, 1, "kg");
	Product regular = new Product("Regular", illy, coffeeBeans, 250, "g");
	Product arabica = new Product("Arabica", clipper, coffeeBeans, 100, "g");
	Product classico = new Product("Classico Connoisseur", caturra,
		coffeeBeans, 500, "g");

	Map<Key<Product>, Product> productMap = DAOService.productDAO.add(
		earlgray, ricoffy, classic, regular, arabica, classico);

	City stellenbosch = new City("Stellenbosch", "Western Cape",
		"South Africa", new GPSCoords(-33.9200, 18.8600));
	City capeTown = new City("Cape Town", "Western Cape", "South Africa",
		new GPSCoords(-33.9767, 18.4244));
	City somersetWest = new City("Somerset West", "Western Cape",
		"South Africa", new GPSCoords(-34.0833, 18.8500));
	City paarl = new City("Paarl", "Western Cape", "South Africa",
		new GPSCoords(-33.7242, 18.9558));
	City joburg = new City("Johannesburg", "Gauteng", "South Africa",
		new GPSCoords(-26.2000, 28.0667));
	City pretoria = new City("Pretoria", "Gauteng", "South Africa",
		new GPSCoords(-25.7256, 28.2439));

	DAOService.cityDAO.add(stellenbosch, capeTown, somersetWest, paarl,
		joburg, pretoria);

	Location dieBoord = new Location(stellenbosch, new GPSCoords(
		-33.9447319, 18.8500055));
	Location simonsrust = new Location(stellenbosch, new GPSCoords(
		-33.926617, 18.878612));
	Location stellmark = new Location(stellenbosch, new GPSCoords(
		-33.9319048, 18.8593215));
	Location eikestad = new Location(stellenbosch, new GPSCoords(
		-33.9359105, 18.860566));
	Location millstreet = new Location(stellenbosch, new GPSCoords(
		-33.9383672, 18.8592785));
	DAOService.locationDAO.add(dieBoord, simonsrust, stellmark, eikestad,
		millstreet);
	Store spar = new Store("Spar");
	Store picknPay = new Store("Pick 'n Pay");
	Store checkers = new Store("Checkers");
	Store shoprite = new Store("Shoprite");
	Store oK = new Store("OK");
	Store woolies = new Store("Woolworths");
	DAOService.storeDAO
		.add(spar, picknPay, checkers, shoprite, oK, woolies);

	Branch sparDieBoord = new Branch("Die Boord", spar, dieBoord);
	Branch picknPayStellmark = new Branch("Stellmark", picknPay, stellmark);
	Branch sparSimonsrust = new Branch("Simonsrust", spar, dieBoord);
	Branch checkersMillstreet = new Branch("Mill Street", checkers,
		millstreet);
	Branch wooliesEikestad = new Branch("Eikestad", woolies, eikestad);
	Branch shopriteEikestad = new Branch("Eikestad", shoprite, eikestad);
	DAOService.branchDAO.add(sparDieBoord, picknPayStellmark,
		sparSimonsrust, checkersMillstreet, wooliesEikestad,
		shopriteEikestad);
	long year2010 = 1182304000000L;
	long month = 2592000000L;
	int i = 0;
	HashSet<BranchProduct> bps = new HashSet<BranchProduct>();
	for (Key<Product> prodKey : productMap.keySet()) {
	    BranchProduct bp = new BranchProduct(productMap.get(prodKey),
		    sparDieBoord, null);
	    bp.setID(100000 + i);
	    DatePrice dp = new DatePrice(new Date(year2010 + i * month),
		    100 + i, bp.getID());
	    DAOService.datePriceDAO.add(dp);
	    bp.setDatePrice(dp);
	    DAOService.branchProductDAO.add(bp);
	    bps.add(bp);
	    i++;
	}
	long lastdate = year2010 + i * month;
	double lastprice = 100 + i;

	double j = 1;
	double inc = 0.05;
	for (i = 0; i < 10; i++) {
	    int c = 0;
	    for (BranchProduct bp : bps) {
		DatePrice dp = new DatePrice(new Date(lastdate + i * month),
			lastprice, bp.getID());
		DAOService.datePriceDAO.add(dp);
		j += inc * c;
		if (j != 0) {
		    inc -= inc / Math.abs(j);
		}
		if (inc <= 0) {
		    inc = Math.abs(j) / 15;
		    j = -j;
		}
		lastprice = lastprice + j;
		c++;
	    }
	}
    }
}
