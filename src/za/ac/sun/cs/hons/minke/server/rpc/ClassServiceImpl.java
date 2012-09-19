package za.ac.sun.cs.hons.minke.server.rpc;

import za.ac.sun.cs.hons.minke.client.rpc.ClassService;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author godfried
 * 
 */
public class ClassServiceImpl extends RemoteServiceServlet implements
		ClassService {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean registerClasses() {
		DAOService.init();
		//EntityUtils.addData();
		return true;
	}

	
}
