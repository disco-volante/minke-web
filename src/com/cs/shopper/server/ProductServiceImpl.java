package com.cs.shopper.server;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.rpc.ProductService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ProductServiceImpl extends RemoteServiceServlet implements
		ProductService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public void addProducts(ProductData[] products2) {

	}

	@Override
	public HashMap<ProductData, String> getProducts(ProductCategory type, City city) {
		Objectify ofy = ObjectifyService.begin();
		Query<ProductData> q = ofy.query(ProductData.class).filter("type", type).filter("city", city);
		HashMap<ProductData, String> products = new HashMap<ProductData, String>();
		for(ProductData data:q) products.put(data,data.getName());
		return products;
	}

}
