package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ProductDAO extends ObjectifyDAO<Product> {
	static {

		ObjectifyService.register(Product.class);

	}

	public ProductDAO() {
		super(Product.class);
	}

	@Override
	public void delete(Product product) {
		List<BranchProduct> bps = DAOService.branchProductDAO.listByProperties(
				new String[] { "productID" }, new Object[] { product.getID() });
		if (bps != null) {
			for (BranchProduct bp : bps) {
				DAOService.branchProductDAO.delete(bp);
			}
		}
		List<ProductCategory> pcs = DAOService.productCategoryDAO
				.listByProperties(new String[] { "productID" },
						new Object[] { product.getID() });
		if (pcs != null) {
			for (ProductCategory pc : pcs) {
				DAOService.productCategoryDAO.delete(pc);
			}
		}
		super.delete(product);
	}

	@Override
	public Key<Product> add(Product product) {
		if (product != null && get(product.getID()) != null) {
			List<BranchProduct> bps = DAOService.branchProductDAO
					.listByProperties(new String[] { "productID" },
							new Object[] { product.getID() });
			if (bps != null) {
				for (BranchProduct bp : bps) {
					bp.setProduct(product);
					DAOService.branchProductDAO.add(bp);
				}
			}
			List<ProductCategory> pcs = DAOService.productCategoryDAO
					.listByProperties(new String[] { "productID" },
							new Object[] { product.getID() });
			if (pcs != null) {
				for (ProductCategory pc : pcs) {
					pc.setProduct(product);
					DAOService.productCategoryDAO.add(pc);
				}
			}
		}
		return super.add(product);
	}

}
