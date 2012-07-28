package za.ac.sun.cs.hons.argyle.server;

import za.ac.sun.cs.hons.argyle.client.rpc.ClassService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.City;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.location.Location;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.BranchProduct;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Brand;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.DatePrice;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.ProductCategory;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Branch;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.store.Store;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class ClassServiceImpl extends RemoteServiceServlet implements
	ClassService {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Override
    public boolean registerClasses() {
	ObjectifyService.register(City.class);
	ObjectifyService.register(Location.class);
	ObjectifyService.register(ProductCategory.class);
	ObjectifyService.register(Brand.class);
	ObjectifyService.register(Product.class);
	ObjectifyService.register(Store.class);
	ObjectifyService.register(Branch.class);
	ObjectifyService.register(DatePrice.class);
	ObjectifyService.register(BranchProduct.class);
	return true;
    }

    @Override
    public void addData(IsEntity[] entities) {
	Objectify ofy = ObjectifyService.begin();
	for (IsEntity o : entities) {
	    if (o != null) {
		ofy.put(o);
	    }
	}

    }
}
