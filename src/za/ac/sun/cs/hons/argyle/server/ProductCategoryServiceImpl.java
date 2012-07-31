package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;
import java.util.List;

import za.ac.sun.cs.hons.argyle.client.rpc.ProductCategoryService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ProductCategoryServiceImpl extends RemoteServiceServlet implements
	ProductCategoryService {
    /**
	 * 
	 */
    private static final long serialVersionUID = -1072938579303987439L;

    @Override
    public HashMap<String, ProductCategory> getProductCategories() {
	List<ProductCategory> retrieved = DAOService.productCategoryDAO
		.listAll();
	HashMap<String, ProductCategory> types = new HashMap<String, ProductCategory>();
	for (ProductCategory type : retrieved) {
	    types.put(type.toString(), type);
	}
	return types;

    }

    @Override
    public ProductCategory getProductCategory(BranchProduct bp) {
	Product product;
	try {
	    product = DAOService.productDAO.get(bp.getProductID());
	    return DAOService.productCategoryDAO.get(product.getCategoryID());
	} catch (EntityNotFoundException e) {
	    e.printStackTrace();
	    return null;
	}
    }

}
