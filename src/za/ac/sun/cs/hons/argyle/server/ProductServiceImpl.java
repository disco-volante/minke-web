package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;
import java.util.List;

import za.ac.sun.cs.hons.argyle.client.rpc.ProductService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProductServiceImpl extends RemoteServiceServlet implements
	ProductService {
    private static final long serialVersionUID = -1072938579303987439L;

    @Override
    public HashMap<String, Product> getProducts() {
	List<Product> productList = DAOService.productDAO.listAll();
	HashMap<String, Product> products = new HashMap<String, Product>();
	for (Product product : productList) {
	    products.put(product.toString(), product);
	}
	return products;
    }

    @Override
    public Product getProduct(BranchProduct bp) {
	try {
	    return DAOService.productDAO.get(bp.getProductID());
	} catch (EntityNotFoundException e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
