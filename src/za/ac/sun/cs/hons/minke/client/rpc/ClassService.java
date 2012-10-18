package za.ac.sun.cs.hons.minke.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("class")
public interface ClassService extends RemoteService {

    boolean registerClasses();

    List<?> getEntities(String entity);

}
