package za.ac.sun.cs.hons.minke.server.servlets.rpc;

import java.util.List;

import za.ac.sun.cs.hons.minke.client.rpc.CategoryService;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityID;
import za.ac.sun.cs.hons.minke.client.serialization.entities.EntityNameMap;
import za.ac.sun.cs.hons.minke.client.serialization.entities.product.Category;
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
		EntityNameMap map = DAOService.entityMapDAO
				.get((long) EntityID.CATEGORY.getID());
		if(map == null){
			map = new EntityNameMap(EntityID.CATEGORY);
		}
		List<Category> cats = DAOService.categoryDAO.listAll();
		map.add(cats.toArray(new Category[cats.size()]));
		DAOService.entityMapDAO.add(map);
		return DAOService.entityMapDAO.get((long) EntityID.CATEGORY.getID());
	}

}
