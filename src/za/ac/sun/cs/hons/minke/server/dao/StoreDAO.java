package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;

import com.googlecode.objectify.ObjectifyService;

public class StoreDAO extends ObjectifyDAO<Store> {
    static {

	ObjectifyService.register(Store.class);

    }

    public StoreDAO() {
	super(Store.class);
    }

}
