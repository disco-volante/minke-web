package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.BranchProduct;

import com.googlecode.objectify.ObjectifyService;

public class BranchProductDAO extends ObjectifyDAO<BranchProduct> {
    static {

	ObjectifyService.register(BranchProduct.class);

    }

    protected BranchProductDAO(Class<BranchProduct> clazz) {
	super(clazz);
    }

}
