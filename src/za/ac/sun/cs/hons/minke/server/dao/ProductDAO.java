package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;

import com.googlecode.objectify.ObjectifyService;

public class ProductDAO extends ObjectifyDAO<Product> {
    static {

	ObjectifyService.register(Product.class);

    }

    public ProductDAO() {
	super(Product.class);
    }

}
