package com.cs.shopper.client.rpc;

import java.util.HashMap;

import com.cs.shopper.client.entities.location.City;
import com.cs.shopper.client.entities.product.ProductData;
import com.cs.shopper.client.entities.product.ProductCategory;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("product")
public interface ProductService extends RemoteService {

	HashMap<ProductData, String> getProducts(ProductCategory type, City city);

	void addProducts(ProductData[] products2);

}
