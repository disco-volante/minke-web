package za.ac.sun.cs.hons.argyle.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClassServiceAsync {

    void registerClasses(AsyncCallback<Boolean> callback);

}
