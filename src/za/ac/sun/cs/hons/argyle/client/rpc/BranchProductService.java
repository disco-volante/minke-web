package za.ac.sun.cs.hons.argyle.client.rpc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("branchproduct")
public interface BranchProductService extends RemoteService {

    HashSet<BranchProduct> getBranchProducts(long productCategoryID, long cityID);

	HashMap<Branch, HashSet<BranchProduct>> getBranches(
			HashMap<Long, Integer> addedProducts);

	HashMap<BranchProduct, HashSet<DatePrice>> getHistories(Set<BranchProduct> branchProducts);

}
