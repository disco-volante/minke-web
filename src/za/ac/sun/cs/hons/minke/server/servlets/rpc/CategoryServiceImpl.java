package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import za.ac.sun.cs.hons.minke.client.rpc.CategoryService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.server.dao.DAOService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * 
 * @author godfried
 * 
 */
public class CategoryServiceImpl extends RemoteServiceServlet implements
		CategoryService {
	private static final long serialVersionUID = -1072938579303987439L;

	@Override
	public EntityNameMap getCategories() {
			return DAOService.entityMapDAO.get((long) EntityID.CATEGORY.getID());
	}

}
