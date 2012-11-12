package za.ac.sun.cs.hons.minke.client.rpc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("branchproduct")
public interface BranchProductService extends RemoteService {
	HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> getBranches(
			HashMap<Long, Integer> addedProducts);

	HashMap<BranchProduct, List<DatePrice>> getHistories(
			Set<BranchProduct> branchProducts);

	HashMap<BranchProduct, List<DatePrice>> getBranchProducts(
			HashMap<EntityID, HashSet<Long>> l, HashSet<Long> categories,
			HashSet<Long> p);

}
