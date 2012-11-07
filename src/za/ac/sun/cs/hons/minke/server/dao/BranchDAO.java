package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class BranchDAO extends ObjectifyDAO<Branch> {
	static {

		ObjectifyService.register(Branch.class);

	}

	public BranchDAO() {
		super(Branch.class);
	}

	@Override
	public void delete(Branch branch) {
		List<BranchProduct> bps = DAOService.branchProductDAO.listByProperties(
				new String[] { "branchID" }, new Object[] { branch.getID() });
		if (bps != null) {
			for (BranchProduct bp : bps) {
				DAOService.branchProductDAO.delete(bp);
			}
		}
		super.delete(branch);
	}

	@Override
	public Key<Branch> add(Branch branch) {
		if (branch != null && get(branch.getID()) != null) {
			List<BranchProduct> bps = DAOService.branchProductDAO
					.listByProperties(new String[] { "branchID" },
							new Object[] { branch.getID() });
			if (bps != null) {
				for (BranchProduct bp : bps) {
					bp.setBranch(branch);
					DAOService.branchProductDAO.add(bp);
				}
			}
		}
		return super.add(branch);
	}

}
