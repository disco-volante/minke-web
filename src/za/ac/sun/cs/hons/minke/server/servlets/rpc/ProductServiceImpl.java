package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.rpc.ProductService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author godfried
 * 
 */
public class ProductServiceImpl extends RemoteServiceServlet implements
		ProductService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public EntityNameMap getProducts() {
		EntityNameMap map = new EntityNameMap(EntityID.PRODUCT);
		List<Product> products = DAOService.productDAO.listAll();
		map.add(products.toArray(new Product[products.size()]));
		return map;

	}

	@Override
	public Product getProduct(BranchProduct bp) {
		return DAOService.productDAO.get(bp.getProductID());

	}

}
