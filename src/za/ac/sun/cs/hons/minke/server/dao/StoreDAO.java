package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Store;

import com.googlecode.objectify.ObjectifyService;

public class StoreDAO extends ObjectifyDAO<Store> {
    static {

	ObjectifyService.register(Store.class);

    }

    public StoreDAO() {
	super(Store.class);
    }
    
    @Override
	public void delete(Store store) {
		List<Branch> branches = DAOService.branchDAO
				.listByProperties(new String[] { "storeID" },
						new Object[] { store.getID() });
		for (Branch branch : branches) {
			DAOService.branchDAO.delete(branch);
		}
		super.delete(store);
	}

}
