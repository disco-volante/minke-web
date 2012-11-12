package za.ac.sun.cs.hons.minke.client.rpc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BranchProductServiceAsync {

	void getBranchProducts(HashMap<EntityID, HashSet<Long>> l, HashSet<Long> categories,HashSet<Long> p,
			AsyncCallback<HashMap<BranchProduct, List<DatePrice>>> callback);

	void getBranches(HashMap<Long, Integer> addedProducts,
			AsyncCallback<HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>>> callback);

	void getHistories(Set<BranchProduct> branchProducts,
			AsyncCallback<HashMap<BranchProduct, List<DatePrice>>> callback);

}
