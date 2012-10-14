package za.ac.sun.cs.hons.minke.server.servlets.rpc;

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
		return DAOService.entityMapDAO.get(EntityID.PRODUCT);

	}

	@Override
	public Product getProduct(BranchProduct bp) {
		return DAOService.productDAO.get(bp.getProductID());

	}

}
