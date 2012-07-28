package za.ac.sun.cs.hons.argyle.server.dao;

import com.googlecode.objectify.ObjectifyService;

import za.ac.sun.cs.hons.argyle.server.entities.Branch;

public class BranchDAO extends ObjectifyDAO<Branch> {
    static {

	ObjectifyService.register(Branch.class);

    }

    protected BranchDAO(Class<Branch> clazz) {
	super(clazz);
    }

}
