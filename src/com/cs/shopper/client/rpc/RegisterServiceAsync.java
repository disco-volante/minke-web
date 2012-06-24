package com.cs.shopper.client.rpc;

import com.cs.shopper.client.entities.user.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {

	void registerUser(User user, AsyncCallback<Boolean> callback);

}
