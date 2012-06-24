package com.cs.shopper.server;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.entities.user.User;
import com.cs.shopper.client.rpc.ClassService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.ObjectifyService;

public class ClassServiceImpl extends RemoteServiceServlet implements ClassService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean registerClasses() {
		ObjectifyService.register(User.class);
		ObjectifyService.register(City.class);
		ObjectifyService.register(ProductCategory.class);
		ObjectifyService.register(ProductData.class);
		return true;
	}

}
