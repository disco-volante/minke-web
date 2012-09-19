package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Brand;

import com.googlecode.objectify.ObjectifyService;

public class BrandDAO extends ObjectifyDAO<Brand> {
    static {

	ObjectifyService.register(Brand.class);

    }

    public BrandDAO() {
	super(Brand.class);
    }

}
