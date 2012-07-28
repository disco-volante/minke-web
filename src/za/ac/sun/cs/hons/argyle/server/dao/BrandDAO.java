package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.Brand;

import com.googlecode.objectify.ObjectifyService;

public class BrandDAO extends ObjectifyDAO<Brand> {
    static {

	ObjectifyService.register(Brand.class);

    }

    protected BrandDAO(Class<Brand> clazz) {
	super(clazz);
    }

}
