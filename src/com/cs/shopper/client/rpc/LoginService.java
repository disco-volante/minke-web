package com.cs.shopper.client.rpc;

import com.cs.shopper.client.entities.user.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	  User loginUser(String username, String password);;
}
