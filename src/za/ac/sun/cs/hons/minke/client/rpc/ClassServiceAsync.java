package za.ac.sun.cs.hons.minke.client.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ClassServiceAsync {

    void registerClasses(AsyncCallback<Boolean> callback);

	void getEntities(String entity, AsyncCallback<List<? extends IsEntity>> callback);

	void deleteEntity(IsEntity item, AsyncCallback<Void> asyncCallback);

	void updateEntity(IsEntity item, AsyncCallback<Void> asyncCallback);

}
