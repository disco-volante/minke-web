package za.ac.sun.cs.hons.minke.client.rpc;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CategoryServiceAsync {

    void getCategories(AsyncCallback<EntityNameMap> callback);

}
