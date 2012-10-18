package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.rpc.ClassService;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;
import za.ac.sun.cs.hons.minke.server.utils.EntityUtils;

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
		return true;
	}

	@Override
	public List<?> getEntities(String entity) {
		return EntityUtils.getAll(entity);
	}

	
}
