package za.ac.sun.cs.hons.minke.client.rpc;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Product;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("product")
public interface ProductService extends RemoteService {

	EntityNameMap getProducts();

	Product getProduct(BranchProduct bp);

}
