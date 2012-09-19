package za.ac.sun.cs.hons.minke.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClassServiceAsync {

    void registerClasses(AsyncCallback<Boolean> callback);

}
