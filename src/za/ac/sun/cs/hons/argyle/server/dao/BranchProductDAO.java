package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;

import com.googlecode.objectify.ObjectifyService;

public class BranchProductDAO extends ObjectifyDAO<BranchProduct> {
    static {

	ObjectifyService.register(BranchProduct.class);

    }

    public BranchProductDAO() {
	super(BranchProduct.class);
    }

}
