package za.ac.sun.cs.hons.argyle.client.rpc;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProductServiceAsync {

	void getProducts(AsyncCallback<HashMap<String, Product>> callback);

}
