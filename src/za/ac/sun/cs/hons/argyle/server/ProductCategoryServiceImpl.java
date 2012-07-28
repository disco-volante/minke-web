package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.rpc.ProductCategoryService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;

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
    public HashMap<String, ProductCategory> getProductCategories() {
	Objectify ofy = ObjectifyService.begin();
	Query<ProductCategory> q = ofy.query(ProductCategory.class);
	HashMap<String, ProductCategory> types = new HashMap<String, ProductCategory>();
	for (ProductCategory type : q) {
	    types.put(type.toString(), type);
	}
	return types;

    }

    @Override
    public void addProductCategory(ProductCategory type) {
	Objectify ofy = ObjectifyService.begin();
	ofy.put(type);
    }

}
