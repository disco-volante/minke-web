package za.ac.sun.cs.hons.argyle.client;

import java.util.HashMap;
import java.util.HashSet;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

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
    private HashSet<BranchProduct>		  branchProducts;
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

    public HashSet<BranchProduct> getBranchProducts() {
	return branchProducts;
    }

    public void setBranchProducts(HashSet<BranchProduct> result) {
	this.branchProducts = result;
    }

    public HashMap<Branch, HashSet<BranchProduct>> getBranches() {
	return branches;
    }

    public void setBranches(HashMap<Branch, HashSet<BranchProduct>> result) {
	this.branches = result;

    }

    public HashMap<Long, Integer> getAddedProducts() {
	return addedProducts;
    }

    public void setAddedProducts(HashMap<Long, Integer> addedProducts) {
	this.addedProducts = addedProducts;
    }
}