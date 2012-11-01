package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.ProductCategory;

import com.googlecode.objectify.ObjectifyService;

public class CategoryDAO extends ObjectifyDAO<Category> {
	static {

		ObjectifyService.register(Category.class);

	}

	public CategoryDAO() {
		super(Category.class);
	}

	@Override
	public void delete(Category category) {
		List<ProductCategory> pcs = DAOService.productCategoryDAO
				.listByProperties(new String[] { "categoryID" },
						new Object[] { category.getID() });
		for (ProductCategory pc : pcs) {
			DAOService.productCategoryDAO.delete(pc);
		}
		super.delete(category);
	}

}
