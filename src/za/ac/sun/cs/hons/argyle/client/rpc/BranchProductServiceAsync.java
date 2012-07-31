package za.ac.sun.cs.hons.argyle.client.rpc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BranchProductServiceAsync {

	void getBranchProducts(long productCategoryID, long cityID,
		AsyncCallback<HashSet<BranchProduct>> callback);

	void getBranches(HashMap<Long, Integer> addedProducts,
			AsyncCallback<HashMap<Branch, HashSet<BranchProduct>>> callback);

	void getHistories(Set<BranchProduct> branchProducts,
		AsyncCallback<HashMap<BranchProduct, HashSet<DatePrice>>> callback);

}
