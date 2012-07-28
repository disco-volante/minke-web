package com.cs.shopper.client.rpc;

import com.cs.shopper.client.entities.user.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService {
	  Boolean registerUser(User user);;
}
