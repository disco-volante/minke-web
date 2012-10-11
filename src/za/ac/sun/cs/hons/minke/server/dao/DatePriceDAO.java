package za.ac.sun.cs.hons.minke.server.dao;

import za.ac.sun.cs.hons.minke.client.serialization.entities.product.DatePrice;

import com.googlecode.objectify.ObjectifyService;

public class DatePriceDAO extends ObjectifyDAO<DatePrice> {
	static {

		ObjectifyService.register(DatePrice.class);

	}

	public DatePriceDAO() {
		super(DatePrice.class);
	}

}
