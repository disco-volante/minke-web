package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class BranchProductDAO extends ObjectifyDAO<BranchProduct> {
	static {

		ObjectifyService.register(BranchProduct.class);

	}

	public BranchProductDAO() {
		super(BranchProduct.class);
	}

	@Override
	public Key<BranchProduct> add(BranchProduct bp) {
		if (bp.getDatePriceID() == -1) {
			bp.setDatePrice(DAOService.datePriceDAO.get(DAOService.datePriceDAO
					.add(bp.getDatePrice())));
		}
		if (bp.getDatePrice() != null
				&& bp.getDatePrice().getBranchProductID() == -1) {
			Key<BranchProduct> key = super.add(bp);
			bp.getDatePrice().setBranchProductID(key.getId());
			bp.setDatePrice(DAOService.datePriceDAO.get(DAOService.datePriceDAO
					.add(bp.getDatePrice())));
			return key;
		}
		Key<BranchProduct> key = super.add(bp);
		bp.getDatePrice().setBranchProductID(key.getId());
		bp.setDatePrice(DAOService.datePriceDAO.get(DAOService.datePriceDAO.add(bp.getDatePrice())));
		return super.add(bp);
	}

	@Override
	public void delete(BranchProduct bp) {
		List<DatePrice> dps = DAOService.datePriceDAO
				.listByProperties(new String[] { "branchProductID" },
						new Object[] { bp.getID() });
		if (dps != null) {
			for (DatePrice dp : dps) {
				DAOService.datePriceDAO.delete(dp);
			}
		}
		super.delete(bp);
	}

}
