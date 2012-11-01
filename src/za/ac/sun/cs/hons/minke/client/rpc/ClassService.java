package za.ac.sun.cs.hons.minke.client.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("class")
public interface ClassService extends RemoteService {

    boolean registerClasses();

    List<? extends IsEntity> getEntities(String entity);

	void deleteEntity(IsEntity item);

	void updateEntity(IsEntity item);

}
