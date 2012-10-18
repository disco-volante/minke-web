package za.ac.sun.cs.hons.minke.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClassServiceAsync {

    void registerClasses(AsyncCallback<Boolean> callback);

	void getEntities(String entity, AsyncCallback<List<?>> callback);

}
