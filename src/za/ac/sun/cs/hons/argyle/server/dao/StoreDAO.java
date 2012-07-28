package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.Store;

import com.googlecode.objectify.ObjectifyService;

public class StoreDAO extends ObjectifyDAO<Store> {
    static {

	ObjectifyService.register(Store.class);

    }

    protected StoreDAO(Class<Store> clazz) {
	super(clazz);
    }

}
