package za.ac.sun.cs.hons.argyle.server.dao;

import com.googlecode.objectify.ObjectifyService;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Brand;

public class BrandDAO extends ObjectifyDAO<Brand> {
    static {

	ObjectifyService.register(Brand.class);

    }

    public BrandDAO() {
	super(Brand.class);
    }

}
