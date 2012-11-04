package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.rpc.ClassService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.IsEntity;
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
	public List<? extends IsEntity> getEntities(String entity) {
		return EntityUtils.getAll(entity);
	}

	@Override
	public void deleteEntity(IsEntity item) {
		EntityUtils.delete(item);
	}

	@Override
	public void updateEntity(IsEntity item) {
		EntityUtils.update(item);		
	}

	
}
