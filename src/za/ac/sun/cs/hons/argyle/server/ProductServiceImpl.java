package za.ac.sun.cs.hons.argyle.server;

import java.util.HashMap;

import za.ac.sun.cs.hons.argyle.client.rpc.ProductService;
import za.ac.sun.cs.hons.argyle.client.serialization.entities.product.Product;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class ProductServiceImpl extends RemoteServiceServlet implements
		ProductService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public HashMap<String, Product> getProducts() {
		Objectify ofy = ObjectifyService.begin();
		Query<Product> q = ofy.query(Product.class);
		HashMap<String, Product> products = new HashMap<String, Product>();
		for (Product data : q) {
			products.put(data.toString(),data);
		}
		return products;
	}
}
