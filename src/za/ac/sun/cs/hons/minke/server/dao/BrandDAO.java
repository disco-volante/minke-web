package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;

import com.googlecode.objectify.ObjectifyService;

public class BrandDAO extends ObjectifyDAO<Brand> {
	static {

		ObjectifyService.register(Brand.class);

	}

	public BrandDAO() {
		super(Brand.class);
	}

	@Override
	public void delete(Brand brand) {
		List<Product> products = DAOService.productDAO.listByProperties(
				new String[] { "brandID" }, new Object[] { brand.getID() });
		for (Product p : products) {
			DAOService.productDAO.delete(p);
		}
		super.delete(brand);
	}

}
