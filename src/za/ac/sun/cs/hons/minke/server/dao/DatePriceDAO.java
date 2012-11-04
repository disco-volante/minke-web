package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;

import com.googlecode.objectify.ObjectifyService;

public class DatePriceDAO extends ObjectifyDAO<DatePrice> {
	static {

		ObjectifyService.register(DatePrice.class);

	}

	public DatePriceDAO() {
		super(DatePrice.class);
	}

	@Override
	public void delete(DatePrice dp) {
		BranchProduct bp = DAOService.branchProductDAO.getByProperties(
				new String[] { "datePriceID" }, new Object[] { dp.getID() });
		if (bp != null) {
			List<DatePrice> dps = DAOService.datePriceDAO.listByProperties(
					new String[] { "branchProductID" },
					new Object[] { bp.getID() });
			if (dps != null) {
				DatePrice curMax = null;
				for (DatePrice _dp : dps) {
					if (!_dp.equals(dp)
							&& (curMax == null || _dp.getDate().after(
									curMax.getDate()))) {
						curMax = _dp;
					}
				}
				if (curMax != null) {
					bp.setDatePrice(curMax);
					DAOService.branchProductDAO.add(bp);
				}
			}
		}
		super.delete(dp);
	}

}
