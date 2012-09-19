package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.googlecode.objectify.ObjectifyService;

public class BranchDAO extends ObjectifyDAO<Branch> {
    static {

	ObjectifyService.register(Branch.class);

    }

    public BranchDAO() {
	super(Branch.class);
    }

}
