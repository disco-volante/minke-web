package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;

import com.googlecode.objectify.ObjectifyService;

/**
 * 
 * @author godfried
 * 
 */
public class ProductCategoryDAO extends ObjectifyDAO<ProductCategory> {
	static {

		ObjectifyService.register(ProductCategory.class);

	}

	/**
     * 
     */
	public ProductCategoryDAO() {
		super(ProductCategory.class);
	}

}
