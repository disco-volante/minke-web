package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;

import com.googlecode.objectify.ObjectifyService;

public class ProductCategoryDAO extends ObjectifyDAO<ProductCategory> {
    static {

	ObjectifyService.register(ProductCategory.class);

    }

    public ProductCategoryDAO() {
	super(ProductCategory.class);
    }

}
