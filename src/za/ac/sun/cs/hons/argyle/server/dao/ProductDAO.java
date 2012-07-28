package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.Product;

import com.googlecode.objectify.ObjectifyService;

public class ProductDAO extends ObjectifyDAO<Product> {
    static {

	ObjectifyService.register(Product.class);

    }

    protected ProductDAO(Class<Product> clazz) {
	super(clazz);
    }

}
