package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.sun.cs.hons.minke.client.rpc.BranchProductService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;
import za.ac.sun.cs.hons.minke.server.utils.EntityUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author godfried
 * 
 */
public class BranchProductServiceImpl extends RemoteServiceServlet implements
		BranchProductService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public HashMap<BranchProduct, List<DatePrice>> getBranchProducts(
			HashMap<EntityID, HashSet<Long>> locations, HashSet<Long> categories, HashSet<Long> products) {
		HashSet<Branch> branches = EntityUtils.getLocationBranches(locations);
		HashMap<BranchProduct, List<DatePrice>> branchProducts = new HashMap<BranchProduct, List<DatePrice>>();
		if (categories != null && categories.size() > 0) {
			HashSet<Product> foundProducts = new HashSet<Product>();
			foundProducts.addAll(EntityUtils.getProductsByID(categories));
			branchProducts = EntityUtils.getBranchProducts(products,foundProducts,
					branches);
		}
		else if (products != null && products.size() > 0) {
			branchProducts = EntityUtils.getBranchProductsByID(products,
					branches);
		} else {
			branchProducts = EntityUtils.getBranchProducts(branches);
		}
		return branchProducts;
	}

	@Override
	public HashMap<Branch, HashMap<BranchProduct, List<DatePrice>>> getBranches(
			HashMap<Long, Integer> productMap) {
		if (productMap == null) {
			return null;
		}
		HashSet<Long> products = new HashSet<Long>();
		products.addAll(productMap.keySet());
		return EntityUtils.getProductBranches(products);
	}

	@Override
	public HashMap<BranchProduct, List<DatePrice>> getHistories(
			Set<BranchProduct> branchProducts) {
		if (branchProducts == null) {
			return null;
		}
		HashMap<BranchProduct, List<DatePrice>> histories = new HashMap<BranchProduct, List<DatePrice>>();
		String[] propNames = new String[] { "branchProductID" };
		Object[] propValues;
		for (BranchProduct bp : branchProducts) {
			propValues = new Object[] { bp.getID() };
			List<DatePrice> datePrices = DAOService.datePriceDAO
					.listByProperties(propNames, propValues);
			histories.put(bp, datePrices);
		}
		return histories;
	}

}
