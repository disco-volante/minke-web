package com.cs.shopper.client.rpc;

import java.util.HashMap;

import com.cs.shopper.client.entities.product.ProductCategory;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductCategoryServiceAsync {

	void getProductCategories(AsyncCallback<HashMap<ProductCategory, String>> callback);

	void addProductCategory(ProductCategory type, AsyncCallback<Void> callback);


}
