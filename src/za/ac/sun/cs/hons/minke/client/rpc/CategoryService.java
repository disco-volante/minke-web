package za.ac.sun.cs.hons.minke.client.rpc;

import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("category")
public interface CategoryService extends RemoteService {

    EntityNameMap getCategories();

}
