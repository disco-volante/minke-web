package com.cs.shopper.client;

import java.util.HashMap;
import java.util.Set;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.entities.user.User;
import com.cs.shopper.client.gui.HomePage;
import com.cs.shopper.client.rpc.ClassService;
import com.cs.shopper.client.rpc.ClassServiceAsync;
import com.cs.shopper.client.rpc.LocationService;
import com.cs.shopper.client.rpc.LocationServiceAsync;
import com.cs.shopper.client.rpc.LoginService;
import com.cs.shopper.client.rpc.LoginServiceAsync;
import com.cs.shopper.client.rpc.ProductCategoryService;
import com.cs.shopper.client.rpc.ProductCategoryServiceAsync;
import com.cs.shopper.client.rpc.ProductService;
import com.cs.shopper.client.rpc.ProductServiceAsync;
import com.cs.shopper.client.rpc.RegisterService;
import com.cs.shopper.client.rpc.RegisterServiceAsync;
import com.cs.shopper.client.utils.TripleHashMap;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

public class Shopper implements EntryPoint {
	private ShopperData data = new ShopperData();
	private class ClassAsyncCallback implements AsyncCallback<Boolean>{
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}
		@Override
		public void onSuccess(Boolean result) {
			System.out.println("Classes registered: "+result);
			getDisplayData();
		}
	};
	private class RegisterAsyncCallback implements AsyncCallback<Boolean> {
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(Boolean result) {
			getHomePage().regMsg(result);
		}
	};
	private class LoginAsyncCallback implements AsyncCallback<User>{
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(User result) {
			if(result!=null)setUser(result);
			getHomePage().loginMsg(result!=null);
		}
	};
	private class ProductCategoryAsyncCallback implements AsyncCallback<HashMap<ProductCategory, String>>{
		private boolean disp;
		private ProductCategoryAsyncCallback(boolean disp){this.disp=disp;};
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(HashMap<ProductCategory, String> result) {
			setProductCategorys(result);
			if(disp)displayProductCategories();
			else if(getData().getLoaded()){
				getData().setLoaded(false);
				loadGUI();
			}
			else getData().setLoaded(true);
		}
	};
	private class CityAsyncCallback implements AsyncCallback<HashMap<City, String>>{
		private boolean disp;
		private CityAsyncCallback(boolean disp){this.disp=disp;};
		@Override
		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		@Override
		public void onSuccess(HashMap<City, String> result) {
			setCities(result);
			if(disp)displayCities();
			else if(getData().getLoaded()){
				getData().setLoaded(false);
				loadGUI();
			}
			else getData().setLoaded(true);
		}
	};

	@Override
	public void onModuleLoad() {
		initProcesses();
	}

	protected void initProcesses() {
		initRPC();
		getData().setProductCategories(new HashMap<ProductCategory, String>());
		getData().setProducts(new TripleHashMap<City, ProductCategory, ProductData, String>());
		getData().setCities(new HashMap<City,String>());

	}
	protected void loadGUI(){
		setHomePage(new HomePage(this));
		RootPanel rp = RootPanel.get();
		rp.add(getHomePage());
	}


	private void initRPC() {
		ClassServiceAsync classSvc = GWT.create(ClassService.class);
		getData().setProdTypeSvc((ProductCategoryServiceAsync) GWT.create(ProductCategoryService.class));
		getData().setLocSvc((LocationServiceAsync) GWT.create(LocationService.class));
		getData().setRegSvc((RegisterServiceAsync) GWT.create(RegisterService.class));
		getData().setLoginSvc((LoginServiceAsync) GWT.create(LoginService.class));
		getData().setProdSvc((ProductServiceAsync) GWT.create(ProductService.class));	
		AsyncCallback<Boolean> callback = new ClassAsyncCallback();
		classSvc.registerClasses(callback);

	}
	protected void getDisplayData() {
		addCities(false);
		addProductCategories(false);		
	}
	private void setHomePage(HomePage homePage) {
		getData().setHp(homePage);
	}
	protected HomePage getHomePage() {
		return getData().getHp();
	}


	protected void setProductCategorys(HashMap<ProductCategory, String> result) {
		getData().setProductCategories(result);

	}
	protected void setProducts(HashMap<ProductData, String> result, City city,
			ProductCategory type) {
		getData().getProducts().put(city,type,result);
	}

	protected void setCities(HashMap<City, String> result) {
		getData().setCities(result) ;
	}

	public HashMap<ProductCategory, String> getProductCategorys() {
		return getData().getProductCategories();
	}
	public Set<ProductCategory> getProductCategorySet() {

		return getData().getProductCategories().keySet();
	}

	public Set<City> getCitySet() {
		return getData().getCities().keySet();
	}
	public HashMap<City,String> getCities() {
		return getData().getCities();
	}

	public User getUser() {
		return getData().getUser();
	}

	public void setUser(User user) {
		getData().setUser(user);
	}
	public void addRegister(User user) {
		AsyncCallback<Boolean> callback = new RegisterAsyncCallback();
		getData().getRegSvc().registerUser(user, callback);

	}

	public void checkLogin(String username,String password) {
		AsyncCallback<User> callback = new LoginAsyncCallback();
		getData().getLoginSvc().loginUser(username,password, callback);
	}
	public void addProductCategories(boolean disp) {
		if (!getData().getProductCategories().isEmpty()){
			if(disp) displayProductCategories();
		}
		else {
			AsyncCallback<HashMap<ProductCategory, String>> callback = new ProductCategoryAsyncCallback(disp);
			getData().getProdTypeSvc().getProductCategories(callback);
		}
	}

	public void addProducts(ProductCategory type, City city) {
		if (getData().getProducts().contains(city,type))
			displayProducts(city, type);
		else {
			final City c = city;
			final ProductCategory t = type;
			class ProductAsyncCallback implements AsyncCallback<HashMap<ProductData, String>>{
				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
				}
				@Override
				public void onSuccess(HashMap<ProductData, String> result) {
					setProducts(result, c, t);
					displayProducts(c, t);
				}
			};
			AsyncCallback<HashMap<ProductData, String>> callback = new ProductAsyncCallback();
			getData().getProdSvc().getProducts(type, city, callback);
		}

	}
	public void addCities(boolean disp) {
		if (!getData().getCities().isEmpty()){
			if(disp)displayCities();
		}
		else {
			AsyncCallback<HashMap<City, String>> callback = new CityAsyncCallback(disp);
			getData().getLocSvc().getCities(callback);
		}
	}

	protected void displayProductCategories(){
		getData().getPd().showProductCategories(getProductCategorySet());

	}

	protected void displayProducts(City city, ProductCategory type) {
		getData().getPd().showProducts(getData().getProducts().bottomKeys(city, type));
	}

	protected void displayCities() {
		getData().getLd().showCities(getCitySet());

	}

	public ShopperData getData() {
		return data;
	}
}
