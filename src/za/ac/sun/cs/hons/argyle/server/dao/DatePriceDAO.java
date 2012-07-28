package za.ac.sun.cs.hons.argyle.server.dao;

import za.ac.sun.cs.hons.argyle.server.entities.DatePrice;

import com.googlecode.objectify.ObjectifyService;

public class DatePriceDAO extends ObjectifyDAO<DatePrice> {
    static {

	ObjectifyService.register(DatePrice.class);

    }

    protected DatePriceDAO(Class<DatePrice> clazz) {
	super(clazz);
    }

}
