package com.cs.shopper.client;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.entities.user.User;
import com.cs.shopper.client.gui.HomePage;
import com.cs.shopper.client.rpc.LocationServiceAsync;
import com.cs.shopper.client.rpc.LoginServiceAsync;
import com.cs.shopper.client.rpc.ProductCategoryServiceAsync;
import com.cs.shopper.client.rpc.ProductServiceAsync;
import com.cs.shopper.client.rpc.RegisterServiceAsync;
import com.cs.shopper.client.utils.LocationDisplay;
import com.cs.shopper.client.utils.ProductDisplay;
import com.cs.shopper.client.utils.TripleHashMap;

public class ShopperData {
	private LocationServiceAsync locSvc;
	private RegisterServiceAsync regSvc;
	private LoginServiceAsync loginSvc;
	private ProductServiceAsync prodSvc;
	private ProductCategoryServiceAsync prodTypeSvc;
	private HashMap<ProductCategory, String> productCategories;
	private HashMap<City, String> cities;
	private TripleHashMap<City, ProductCategory, ProductData, String> products;
	private HomePage hp;
	private LocationDisplay ld;
	private ProductDisplay pd;
	private boolean loaded;
	private User user;

	public ShopperData() {
	}

	public LocationServiceAsync getLocSvc() {
		return locSvc;
	}

	public void setLocSvc(LocationServiceAsync locSvc) {
		this.locSvc = locSvc;
	}

	public RegisterServiceAsync getRegSvc() {
		return regSvc;
	}

	public void setRegSvc(RegisterServiceAsync regSvc) {
		this.regSvc = regSvc;
	}

	public LoginServiceAsync getLoginSvc() {
		return loginSvc;
	}

	public void setLoginSvc(LoginServiceAsync loginSvc) {
		this.loginSvc = loginSvc;
	}

	public ProductServiceAsync getProdSvc() {
		return prodSvc;
	}

	public void setProdSvc(ProductServiceAsync prodSvc) {
		this.prodSvc = prodSvc;
	}

	public ProductCategoryServiceAsync getProdTypeSvc() {
		return prodTypeSvc;
	}

	public void setProdTypeSvc(ProductCategoryServiceAsync prodTypeSvc) {
		this.prodTypeSvc = prodTypeSvc;
	}

	public HashMap<ProductCategory, String> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(
			HashMap<ProductCategory, String> productCategories) {
		this.productCategories = productCategories;
	}

	public HashMap<City, String> getCities() {
		return cities;
	}

	public void setCities(HashMap<City, String> cities) {
		this.cities = cities;
	}

	public TripleHashMap<City, ProductCategory, ProductData, String> getProducts() {
		return products;
	}

	public void setProducts(
			TripleHashMap<City, ProductCategory, ProductData, String> products) {
		this.products = products;
	}

	public HomePage getHp() {
		return hp;
	}

	public void setHp(HomePage hp) {
		this.hp = hp;
	}

	public LocationDisplay getLd() {
		return ld;
	}

	public void setLd(LocationDisplay ld) {
		this.ld = ld;
	}

	public ProductDisplay getPd() {
		return pd;
	}

	public void setPd(ProductDisplay pd) {
		this.pd = pd;
	}

	public boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}