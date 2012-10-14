package za.ac.sun.cs.hons.minke.server.dao;

import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyOpts;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

/**
 * 
 * @author godfried
 * 
 * @param <T>
 */
public class ObjectifyDAO<T> extends DAOBase {
	private Class<T> clazz;

	/**
	 * We've got to get the associated domain class somehow
	 * 
	 * @param clazz
	 */
	protected ObjectifyDAO(Class<T> clazz) {
		super(new ObjectifyOpts().setSessionCache(true));
		this.clazz = clazz;
	}

	/**
	 * 
	 * @param entity
	 * @return
	 */
	public Key<T> add(T entity) {
		Key<T> key = ofy().put(entity);
		return key;
	}

	/**
	 * 
	 * @param entities
	 * @return
	 */
	public Map<Key<T>, T> add(T... entities) {
		Map<Key<T>, T> keys = ofy().put(entities);
		return keys;
	}

	/**
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		ofy().delete(entity);
	}

	/**
	 * 
	 * @param entityKey
	 */
	public void delete(Key<T> entityKey) {
		ofy().delete(entityKey);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws EntityNotFoundException
	 */
	public T get(Long id) {
		T obj;
		try {
			obj = ofy().get(this.clazz, id);
		} catch (NotFoundException nfe) {
			return null;
		}
		return obj;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public T get(Key<T> key) {
		T obj = ofy().get(key);
		return obj;
	}

	/**
	 * Convenience method to get an object matching a single property
	 * 
	 * @param propName
	 * @param propValue
	 * @return T matching Object
	 */
	public T getByProperties(String[] propNames, Object[] propValues) {
		if (propNames.length != propValues.length) {
			return null;
		}
		Query<T> q = queryByProperties(propNames, propValues);
		return q.get();
	}

	/**
	 * 
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public Query<T> queryByProperties(String[] propNames, Object[] propValues) {
		Query<T> q = ofy().query(clazz);
		for (int i = 0; i < propValues.length; i++) {
			q.filter(propNames[i], propValues[i]);
		}
		return q;
	}

	/**
	 * 
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List<T> listByProperties(String[] propNames, Object[] propValues) {
		if (propNames.length != propValues.length) {
			return null;
		}
		Query<T> q = queryByProperties(propNames, propValues);
		List<T> list = q.list();
		return list;
	}

	/**
	 * 
	 * @param propNames
	 * @param propValues
	 * @return
	 */
	public List<Key<T>> listKeysByProperties(String[] propNames,
			Object[] propValues) {
		if (propNames.length != propValues.length) {
			return null;
		}
		Query<T> q = queryByProperties(propNames, propValues);
		List<Key<T>> list = q.listKeys();
		return list;
	}

	/**
	 * 
	 * @return
	 */
	public List<T> listAll() {
		Query<T> q = ofy().query(clazz);
		List<T> list = q.list();
		return list;
	}

}