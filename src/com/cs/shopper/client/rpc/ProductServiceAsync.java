package com.cs.shopper.client.rpc;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductServiceAsync {


	void getProducts(ProductCategory type, City city,
			AsyncCallback<HashMap<ProductData, String>> callback);

	void addProducts(ProductData[] products2, AsyncCallback<Void> callback);


}
