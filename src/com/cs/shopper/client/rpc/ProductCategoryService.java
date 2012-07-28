package com.cs.shopper.client.rpc;

import java.util.HashMap;

import com.cs.shopper.client.entities.product.ProductCategory;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("productCategory")
public interface ProductCategoryService extends RemoteService {


	void addProductCategory(ProductCategory type);

	HashMap<ProductCategory, String> getProductCategories();

}
