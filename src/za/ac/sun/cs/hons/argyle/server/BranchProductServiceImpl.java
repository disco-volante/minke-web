package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import za.ac.sun.cs.hons.argyle.client.rpc.BranchProductService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class BranchProductServiceImpl extends RemoteServiceServlet implements
	BranchProductService {
    private static final long serialVersionUID = -1072938579303987439L;

    @Override
    public HashSet<BranchProduct> getBranchProducts(long productCategoryID,
	    long cityID) {
	String[] propNames = new String[] { "cityID" };
	Object[] propValues = new Object[] { cityID };
	List<Location> locations = DAOService.locationDAO.listByProperties(
		propNames, propValues);
	HashSet<Branch> branches = new HashSet<Branch>();
	propNames = new String[] { "locationID" };
	for (Location loc : locations) {
	    propValues = new Object[] { loc.getID() };
	    Branch branch = DAOService.branchDAO.getByProperties(propNames,
		    propValues);
	    if (branch != null) {
		branches.add(branch);
	    }
	}
	propNames = new String[] { "categoryID" };
	propValues = new Object[] { productCategoryID };
	List<Product> products = DAOService.productDAO.listByProperties(
		propNames, propValues);
	HashSet<BranchProduct> branchProducts = new HashSet<BranchProduct>();
	propNames = new String[] { "productID", "branchID" };
	for (Product product : products) {
	    for (Branch branch : branches) {
		propValues = new Object[] { product.getID(), branch.getID() };
		BranchProduct bp = DAOService.branchProductDAO.getByProperties(
			propNames, propValues);
		if (bp != null) {
		    branchProducts.add(bp);
		}
	    }
	}
	return branchProducts;
    }

    @Override
    public HashMap<Branch, HashSet<BranchProduct>> getBranches(
	    HashMap<Long, Integer> productMap) {
	if (productMap == null) {
	    return null;
	}
	String[] propNames = new String[] { "productID" };
	Object[] propValues;
	HashMap<Long, HashSet<BranchProduct>> branchProducts = new HashMap<Long, HashSet<BranchProduct>>();
	HashMap<Long, Branch> branches = new HashMap<Long, Branch>();
	for (Long productID : productMap.keySet()) {
	    propValues = new Object[] { productID };
	    BranchProduct bp = DAOService.branchProductDAO.getByProperties(
		    propNames, propValues);
	    if (bp != null) {
		HashSet<BranchProduct> bpSet = branchProducts.get(bp
			.getBranchID());
		if (bpSet == null) {
		    try {
			Branch branch = DAOService.branchDAO.get(bp
				.getBranchID());
			branchProducts.put(branch.getID(),
				new HashSet<BranchProduct>());
			bpSet = branchProducts.get(branch.getID());
			branches.put(branch.getID(), branch);
		    } catch (EntityNotFoundException e) {
			e.printStackTrace();
		    }
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
	if(branchProducts == null){
	    return null;
	}
	HashMap<BranchProduct, HashSet<DatePrice>> histories = new HashMap<BranchProduct, HashSet<DatePrice>>();

	String[] propNames = new String[] { "branchProductID" };
	Object[] propValues;
	for (BranchProduct bp : branchProducts) {
	    propValues = new Object[] { bp.getID() };
	    List<DatePrice> datePriceList = DAOService.datePriceDAO
		    .listByProperties(propNames, propValues);
	    HashSet<DatePrice> datePrices = new HashSet<DatePrice>();
	    datePrices.addAll(datePriceList);
	    histories.put(bp, datePrices);
	}
	return histories;
    }
}
