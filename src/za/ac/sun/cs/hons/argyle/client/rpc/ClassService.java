package za.ac.sun.cs.hons.argyle.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("class")
public interface ClassService extends RemoteService {

    boolean registerClasses();

}
