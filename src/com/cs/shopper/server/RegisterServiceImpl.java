package com.cs.shopper.server;

import com.cs.shopper.client.entities.user.User;
import com.cs.shopper.client.rpc.RegisterService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class RegisterServiceImpl extends RemoteServiceServlet implements
RegisterService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public Boolean registerUser(User user) {
		Objectify ofy = ObjectifyService.begin();
		if(ofy.get(new Key<User>(User.class,user.getUsername())) != null) return false;
		ofy.put(user);
		return true;
	}
}

