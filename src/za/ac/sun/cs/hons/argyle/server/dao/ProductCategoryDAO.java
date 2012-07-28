package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.ProductCategory;

import com.googlecode.objectify.ObjectifyService;

public class ProductCategoryDAO extends ObjectifyDAO<ProductCategory> {
    static {

	ObjectifyService.register(ProductCategory.class);

    }

    protected ProductCategoryDAO(Class<ProductCategory> clazz) {
	super(clazz);
    }

}
