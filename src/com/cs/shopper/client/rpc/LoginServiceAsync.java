package com.cs.shopper.client.rpc;

import com.cs.shopper.client.entities.user.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {

	void loginUser(String username, String password,
			AsyncCallback<User> callback);

}
