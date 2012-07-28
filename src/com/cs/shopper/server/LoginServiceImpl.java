package com.cs.shopper.server;

import com.cs.shopper.client.entities.user.User;
import com.cs.shopper.client.rpc.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public User loginUser(String username, String password) {
		Objectify ofy = ObjectifyService.begin();
		User user = ofy.get(new Key<User>(User.class,username));
		if(user!=null && user.getPassword().equals(password)) return user;
		return null; 
	}

}
