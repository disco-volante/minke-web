package com.cs.shopper.server;

import java.util.HashMap;

import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.rpc.ProductCategoryService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ProductCategoryServiceImpl extends RemoteServiceServlet implements
ProductCategoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1072938579303987439L;


	@Override
	public HashMap<ProductCategory, String> getProductCategories() {
		Objectify ofy = ObjectifyService.begin();
		ofy.delete(ofy.query(ProductCategory.class));
		addTypes(ofy);
		Query<ProductCategory> q = ofy.query(ProductCategory.class);
		HashMap<ProductCategory, String> types = new HashMap<ProductCategory, String>();
		for(ProductCategory type:q) types.put(type,type.getType());
		return types;

	}
	private void addTypes(Objectify ofy){
		ofy.put(new ProductCategory("Instant Coffee","Coffee","Consumables"));
		ofy.put(new ProductCategory("Rooibos Tea","Tea","Consumables"));
		ofy.put(new ProductCategory("Chai Tea","Tea","Consumables"));
		ofy.put(new ProductCategory("Decaf Tea","Tea","Consumables"));
		ofy.put(new ProductCategory("Decaf Filter Coffee","Coffee","Consumables"));
		ofy.put(new ProductCategory("Coffee Beans","Coffee","Consumables"));

	}

	@Override
	public void addProductCategory(ProductCategory type) {
		Objectify ofy = ObjectifyService.begin();
		ofy.put(type);
	}

}
