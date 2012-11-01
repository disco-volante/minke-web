package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;

import com.googlecode.objectify.ObjectifyService;

public class BranchProductDAO extends ObjectifyDAO<BranchProduct> {
    static {

	ObjectifyService.register(BranchProduct.class);

    }

    public BranchProductDAO() {
	super(BranchProduct.class);
    }
    
    @Override
   	public void delete(BranchProduct bp) {
   		List<DatePrice> dps = DAOService.datePriceDAO
   				.listByProperties(new String[] { "branchProductID" },
   						new Object[] { bp.getID() });
   		for (DatePrice dp : dps) {
   			DAOService.datePriceDAO.delete(dp);
   		}
   		super.delete(bp);
   	}


}
