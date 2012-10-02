package za.ac.sun.cs.hons.minke.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.rpc.BranchProductService;
import za.ac.sun.cs.hons.minke.client.rpc.BranchProductServiceAsync;
import za.ac.sun.cs.hons.minke.client.rpc.CategoryService;
import za.ac.sun.cs.hons.minke.client.rpc.CategoryServiceAsync;
import za.ac.sun.cs.hons.minke.client.rpc.ClassService;
import za.ac.sun.cs.hons.minke.client.rpc.ClassServiceAsync;
import za.ac.sun.cs.hons.minke.client.rpc.LocationService;
import za.ac.sun.cs.hons.minke.client.rpc.LocationServiceAsync;
import za.ac.sun.cs.hons.minke.client.rpc.ProductService;
import za.ac.sun.cs.hons.minke.client.rpc.ProductServiceAsync;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.util.GuiUtils;

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
	protected WebSystem system;
	public ClassServiceAsync classSvc;
	public LocationServiceAsync locSvc;
	public ProductServiceAsync prodSvc;
	public BranchProductServiceAsync brProdSvc;
	public CategoryServiceAsync catSvc;

	/**
	 * Creates an {@link RPC} object and instantiates its rpcs.
	 * 
	 * @param system
	 *            the parent {@link WebSystem} object of this class.
	 */
	public RPC(WebSystem system) {
		setWebSystem(system);
		initRPC();
	}

	/**
	 * Getter for the {@link WebSystem} object.
	 * 
	 * @return this class's {@link WebSystem} object.
	 */
	public WebSystem getWebSystem() {
		return system;
	}

	/**
	 * Setter for the {@link WebSystem} object.
	 * 
	 * @param system
	 *            this class's new {@link WebSystem} object.
	 */
	public void setWebSystem(WebSystem system) {
		this.system = system;
	}

	/**
	 * Initialises the various rpc services.
	 */
	public void initRPC() {
		classSvc = (ClassServiceAsync) GWT.create(ClassService.class);
		catSvc = (CategoryServiceAsync) GWT.create(CategoryService.class);
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
			system.setUserCoords(0, 0);
		}

		@Override
		public void onSuccess(Position result) {
			system.setUserCoords(
					(int) (result.getCoordinates().getLatitude() * 1E6),
					(int) (result.getCoordinates().getLongitude() * 1E6));
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
			GuiUtils.hideLoader();
			GuiUtils.showError("Startup Error",
					"Something went wrong, please reopen this page.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(Boolean result) {
			system.getDisplayData();
		}
	};

	/**
	 * {@link AsyncCallback} implementation for getting {@link ProductCategory}
	 * objects from the db.
	 * 
	 * @author godfried
	 * 
	 */
	protected class CategoryAsyncCallback implements
			AsyncCallback<EntityNameMap> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Something went wrong, please reopen this page.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(EntityNameMap result) {
			system.displayCategories(result);
		}
	};

	/**
	 * {@link AsyncCallback} implementation for getting {@link City} objects
	 * from the db.
	 * 
	 * @author godfried
	 * 
	 */
	protected class LocationsAsyncCallback implements
			AsyncCallback<EntityNameMap> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Something went wrong, please reopen this page.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(EntityNameMap result) {
			system.displayLocations(result);
		}
	};

	/**
	 * {@link AsyncCallback} implementation for getting {@link City} objects
	 * from the db.
	 * 
	 * @author godfried
	 * 
	 */
	protected class LocationAsyncCallback implements AsyncCallback<Location> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Something went wrong, please reopen this page.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(Location result) {
			GuiUtils.hideLoader();
			system.displayLocation(result);
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
			AsyncCallback<EntityNameMap> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Something went wrong, please reopen this page.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(EntityNameMap result) {
			system.displayProducts(result);
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
			AsyncCallback<HashMap<BranchProduct, List<DatePrice>>> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Something went wrong, please reopen this page.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(HashMap<BranchProduct, List<DatePrice>> result) {
			system.displayBranchProducts(result);
		}
	};

	/**
	 * {@link AsyncCallback} implementation for getting {@link Branch} objects
	 * from the db.
	 * 
	 * @author godfried
	 * 
	 */
	protected class BranchProductAsyncCallback2
			implements
			AsyncCallback<HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>>> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Your request was unsuccessful, please try again.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(
				HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> result) {
			system.displayBranches(result);
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
			AsyncCallback<HashMap<BranchProduct, List<DatePrice>>> {
		@Override
		public void onFailure(Throwable caught) {
			GuiUtils.hideLoader();
			GuiUtils.showError("Data Retrieval Error",
					"Your request was unsuccessful, please try again.");
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(HashMap<BranchProduct, List<DatePrice>> result) {
			system.displayHistories(result);
		}
	}

	/**
	 * Registers classes with Objectify.
	 */
	public void registerClasses() {
		classSvc.registerClasses(new ClassAsyncCallback());
	}

	/**
	 * Gets {@link City} objects from the db.
	 */
	public void getLocations() {
		locSvc.getCities(new LocationsAsyncCallback());
		locSvc.getCountries(new LocationsAsyncCallback());
		locSvc.getProvinces(new LocationsAsyncCallback());
	}

	/**
	 * Gets {@link Category} objects from the db.
	 */
	public void getCategories() {
		catSvc.getCategories(new CategoryAsyncCallback());
	}

	/**
	 * Gets {@link Product} objects from the db.
	 */
	public void getProducts() {
		prodSvc.getProducts(new ProductAsyncCallback());
	}

	public void getBranchProductsP(HashMap<EntityID, HashSet<Long>> l,
			HashSet<Long> p) {
		brProdSvc.getBranchProductsP(l, p, new BranchProductAsyncCallback1());
	}

	public void getBranchProductsC(HashMap<EntityID, HashSet<Long>> l,
			HashSet<Long> c) {
		brProdSvc.getBranchProductsC(l, c, new BranchProductAsyncCallback1());
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

	public void getLocation(Long locID) {
		locSvc.getLocation(locID, new LocationAsyncCallback());
	}

}
