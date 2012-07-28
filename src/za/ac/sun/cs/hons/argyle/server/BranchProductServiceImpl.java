package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.rpc.BranchProductService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class BranchProductServiceImpl extends RemoteServiceServlet implements
	BranchProductService {
    private static final long serialVersionUID = -1072938579303987439L;

    @Override
    public HashMap<String, BranchProduct> getBranchProducts(
	    long productCategoryID, long cityID) {
	Objectify ofy = ObjectifyService.begin();
	Query<BranchProduct> q = ofy.query(BranchProduct.class);
	HashMap<String, BranchProduct> branchProducts = new HashMap<String, BranchProduct>();
	for (BranchProduct bp : q) {
	    if (bp.getBranch().getLocation().getCity().getID() == cityID
		    && bp.getProduct().getCategory().getID() == productCategoryID) {
		branchProducts.put(bp.toString(), bp);
	    }
	}
	return branchProducts;
    }

    @Override
    public HashMap<Branch, HashSet<BranchProduct>> getBranches(
	    HashMap<Long, Integer> productMap) {
	Objectify ofy = ObjectifyService.begin();
	Query<BranchProduct> q = ofy.query(BranchProduct.class);
	HashMap<Long, HashSet<BranchProduct>> branchProducts = new HashMap<Long, HashSet<BranchProduct>>();
	HashMap<Long, Branch> branches = new HashMap<Long, Branch>();
	// Set<Long> productIDs = productMap.keySet();
	for (BranchProduct bp : q) {
	    if (productMap.containsKey(bp.getProduct().getID())) {
		HashSet<BranchProduct> bpSet = branchProducts.get(bp
			.getBranch().getID());
		if (bpSet == null) {
		    branchProducts.put(bp.getBranch().getID(),
			    new HashSet<BranchProduct>());
		    bpSet = branchProducts.get(bp.getBranch().getID());
		    branches.put(bp.getBranch().getID(), bp.getBranch());
		}
		bpSet.add(bp);
	    }
	}
	HashMap<Branch, HashSet<BranchProduct>> matches = new HashMap<Branch, HashSet<BranchProduct>>();
	for (Entry<Long, HashSet<BranchProduct>> b : branchProducts.entrySet()) {
	    if (b.getValue().size() == productMap.size()) {
		matches.put(branches.get(b.getKey()), b.getValue());
	    }
	}
	return matches;
    }

    @Override
    public HashMap<BranchProduct, HashSet<DatePrice>> getHistories(
	    Set<BranchProduct> branchProducts) {
	HashMap<BranchProduct, HashSet<DatePrice>> histories = new HashMap<BranchProduct, HashSet<DatePrice>>();
	Objectify ofy = ObjectifyService.begin();
	Query<DatePrice> q = ofy.query(DatePrice.class);
	System.out.println(q.count());
	for (BranchProduct bp : branchProducts) {
	    for (DatePrice dp : q) {
		if (dp.getBranchProductID() != null && dp.getBranchProductID().equals(bp.getID())) {
		    HashSet<DatePrice> contents = histories.get(bp);
		    if(contents == null){
			contents = new HashSet<DatePrice>();
		    }
		    contents.add(dp);
		    histories.put(bp, contents);
		}
	    }
	}
	for (HashSet<DatePrice> set : histories.values()) {
	    System.out.println(set.size());
	}
	return histories;
    }
}
