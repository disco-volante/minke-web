package za.ac.sun.cs.hons.argyle.client.rpc;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductCategoryServiceAsync {

    void getProductCategories(
	    AsyncCallback<HashMap<String, ProductCategory>> callback);

    void getProductCategory(BranchProduct bp,
	    AsyncCallback<ProductCategory> productCategoryCallBack);

}
