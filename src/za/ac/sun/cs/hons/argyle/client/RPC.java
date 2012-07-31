package za.ac.sun.cs.hons.argyle.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.rpc.BranchProductService;
import za.ac.sun.cs.hons.argyle.client.rpc.BranchProductServiceAsync;
import za.ac.sun.cs.hons.argyle.client.rpc.ClassService;
import za.ac.sun.cs.hons.argyle.client.rpc.ClassServiceAsync;
import za.ac.sun.cs.hons.argyle.client.rpc.LocationService;
import za.ac.sun.cs.hons.argyle.client.rpc.LocationServiceAsync;
import za.ac.sun.cs.hons.argyle.client.rpc.ProductCategoryService;
import za.ac.sun.cs.hons.argyle.client.rpc.ProductCategoryServiceAsync;
import za.ac.sun.cs.hons.argyle.client.rpc.ProductService;
import za.ac.sun.cs.hons.argyle.client.rpc.ProductServiceAsync;
import za.ac.sun.cs.hons.argyle.client.serialization.GPSCoords;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.geolocation.client.Geolocation;
import com.google.gwt.geolocation.client.Position;
import com.google.gwt.geolocation.client.PositionError;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Class which provides remote procedure call functionality.
 * 
 * @author godfried
 * 
 */
public class RPC {
    private Argyle argyle;
    public ClassServiceAsync classSvc;
    public LocationServiceAsync locSvc;
    public ProductServiceAsync prodSvc;
    public BranchProductServiceAsync brProdSvc;
    public ProductCategoryServiceAsync prodCatSvc;

    /**
     * Creates an {@link RPC} object and instantiates its rpcs.
     * 
     * @param argyle
     *            the parent {@link Argyle} object of this class.
     */
    public RPC(Argyle argyle) {
	setArgyle(argyle);
	initRPC();
    }

    /**
     * Getter for the {@link Argyle} object.
     * 
     * @return this class's {@link Argyle} object.
     */
    public Argyle getArgyle() {
	return argyle;
    }

    /**
     * Setter for the {@link Argyle} object.
     * 
     * @param argyle
     *            this class's new {@link Argyle} object.
     */
    public void setArgyle(Argyle argyle) {
	this.argyle = argyle;
    }

    /**
     * Initialises the various rpc services.
     */
    public void initRPC() {
	classSvc = (ClassServiceAsync) GWT.create(ClassService.class);
	prodCatSvc = (ProductCategoryServiceAsync) GWT
		.create(ProductCategoryService.class);
	locSvc = (LocationServiceAsync) GWT.create(LocationService.class);
	prodSvc = (ProductServiceAsync) GWT.create(ProductService.class);
	brProdSvc = (BranchProductServiceAsync) GWT
		.create(BranchProductService.class);
    }

    /**
     * {@link CallBack}< {@link Position}, {@link PositionError}> implementation
     * for getting user location.
     * 
     * @author godfried
     * 
     */
    protected class GeoCallback implements Callback<Position, PositionError> {
	@Override
	public void onFailure(PositionError caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(Position result) {
	    getArgyle().setLocation(
		    new GPSCoords(result.getCoordinates().getLatitude(), result
			    .getCoordinates().getLongitude()));
	}
    };

    /**
     * {@link AsyncCallback} implementation for adding data to the db.
     * 
     * @author godfried
     * 
     */
    protected class DataAsyncCallback implements AsyncCallback<Void> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(Void result) {
	    getArgyle().getDisplayData();
	}
    };

    /**
     * {@link AsyncCallback} implementation for registering classes with
     * Objectify.
     * 
     * @author godfried
     * 
     */
    protected class ClassAsyncCallback implements AsyncCallback<Boolean> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(Boolean result) {
	    getArgyle().getDisplayData();
	}
    };

    /**
     * {@link AsyncCallback} implementation for getting {@link ProductCategory}
     * objects from the db.
     * 
     * @author godfried
     * 
     */
    protected class ProductCategoryAsyncCallback implements
	    AsyncCallback<HashMap<String, ProductCategory>> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(HashMap<String, ProductCategory> result) {
	    getArgyle().displayProductCategories(result);
	}
    };

    /**
     * {@link AsyncCallback} implementation for getting {@link City} objects
     * from the db.
     * 
     * @author godfried
     * 
     */
    protected class CityAsyncCallback implements
	    AsyncCallback<HashMap<String, City>> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(HashMap<String, City> result) {
	    getArgyle().displayCities(result);
	}
    };

    /**
     * {@link AsyncCallback} implementation for getting {@link Product} objects
     * from the db.
     * 
     * @author godfried
     * 
     */
    protected class ProductAsyncCallback implements
	    AsyncCallback<HashMap<String, Product>> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(HashMap<String, Product> result) {
	    getArgyle().displayProducts(result);
	}
    };

    /**
     * {@link AsyncCallback} implementation for getting {@link BranchProduct}
     * objects from the db.
     * 
     * @author godfried
     * 
     */
    protected class BranchProductAsyncCallback1 implements
	    AsyncCallback<HashSet<BranchProduct>> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(HashSet<BranchProduct> result) {
	    getArgyle().displayBranchProducts(result);
	}
    };

    /**
     * {@link AsyncCallback} implementation for getting {@link Branch} objects
     * from the db.
     * 
     * @author godfried
     * 
     */
    protected class BranchProductAsyncCallback2 implements
	    AsyncCallback<HashMap<Branch, HashSet<BranchProduct>>> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(HashMap<Branch, HashSet<BranchProduct>> result) {
	    getArgyle().displayBranches(result);
	}
    }

    /**
     * {@link AsyncCallback} implementation for getting {@link DatePrice}
     * objects from the db.
     * 
     * @author godfried
     * 
     */
    protected class BranchProductAsyncCallback3 implements
	    AsyncCallback<HashMap<BranchProduct, HashSet<DatePrice>>> {
	@Override
	public void onFailure(Throwable caught) {
	    caught.printStackTrace();
	}

	@Override
	public void onSuccess(HashMap<BranchProduct, HashSet<DatePrice>> result) {
	    getArgyle().displayHistories(result);
	}
    }

    /**
     * Registers classes with Objectify.
     */
    public void registerClasses() {
	classSvc.registerClasses(new ClassAsyncCallback());
    }

    /**
     * Adds data to the db.
     * 
     * @param dbData
     *            the data to be added.
     */
    public void addData(IsEntity[] dbData) {
	// classSvc.addData(dbData, new DataAsyncCallback());
    }

    /**
     * Gets {@link City} objects from the db.
     */
    public void getCities() {
	locSvc.getCities(new CityAsyncCallback());
    }

    /**
     * Gets {@link ProductCategory} objects from the db.
     */
    public void getProductCategories() {
	prodCatSvc.getProductCategories(new ProductCategoryAsyncCallback());
    }

    /**
     * Gets {@link Product} objects from the db.
     */
    public void getProducts() {
	prodSvc.getProducts(new ProductAsyncCallback());
    }

    public void getBranchProducts(long productCategoryID, long cityID) {
	brProdSvc.getBranchProducts(productCategoryID, cityID,
		new BranchProductAsyncCallback1());
    }

    /**
     * Gets {@link Branch} objects from the db.
     * 
     * @param addedProducts
     *            the {@link Product} objects which must be availible at each
     *            {@link Branch}.
     */
    public void getBranches(HashMap<Long, Integer> addedProducts) {
	brProdSvc.getBranches(addedProducts, new BranchProductAsyncCallback2());
    }

    public void getHistories(Set<BranchProduct> branchProducts) {
	brProdSvc.getHistories(branchProducts,
		new BranchProductAsyncCallback3());

    }

    /**
     * Gets user location.
     */
    public void getUserLocation() {
	Geolocation geo = Geolocation.getIfSupported();
	geo.getCurrentPosition(new GeoCallback());

    }

}
