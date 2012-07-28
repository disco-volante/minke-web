package za.ac.sun.cs.hons.argyle.client.rpc;

import za.ac.sun.cs.hons.argyle.client.serialization.entities.IsEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClassServiceAsync {

    void registerClasses(AsyncCallback<Boolean> callback);

    void addData(IsEntity[] entities, AsyncCallback<Void> callback);

}
