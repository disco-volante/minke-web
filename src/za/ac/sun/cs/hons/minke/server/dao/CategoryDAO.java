package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;

import com.googlecode.objectify.ObjectifyService;

public class CategoryDAO extends ObjectifyDAO<Category> {
    static {

	ObjectifyService.register(Category.class);

    }

    public CategoryDAO() {
	super(Category.class);
    }

}
