package za.ac.sun.cs.hons.argyle.server.dao;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.QueryResultIterable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Query;
import com.googlecode.objectify.util.DAOBase;

;

public class ObjectifyDAO<T> extends DAOBase {
    private Class<T> clazz;

    /**
     * We've got to get the associated domain class somehow
     * 
     * @param clazz
     */
    protected ObjectifyDAO(Class<T> clazz) {
	this.clazz = clazz;
    }

    public Key<T> add(T entity)

    {
	Key<T> key = ofy().put(entity);
	return key;
    }
    public Map<Key<T>, T> add(T... entities)

    {
	Map<Key<T>, T> keys = ofy().put(entities);
	return keys;
    }
    public void delete(T entity) {
	ofy().delete(entity);
    }

    public void delete(Key<T> entityKey) {
	ofy().delete(entityKey);
    }

    public T get(Long id) throws EntityNotFoundException {
	T obj = ofy().get(this.clazz, id);
	return obj;
    }

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

    public Query<T> queryByProperties(String[] propNames, Object[] propValues) {
	Query<T> q = ofy().query(clazz);
	for (int i = 0; i < propValues.length; i++) {
	    q.filter(propNames[i], propValues[i]);
	}
	return q;
    }

    public List<T> listByProperties(String[] propNames, Object[] propValues) {
	if (propNames.length != propValues.length) {
	    return null;
	}
	Query<T> q = queryByProperties(propNames, propValues);
	List<T> list = q.list();
	return list;
    }

    public QueryResultIterable<Key<T>> listKeysByProperties(String[] propNames,
	    Object[] propValues) {
	if (propNames.length != propValues.length) {
	    return null;
	}
	Query<T> q = queryByProperties(propNames, propValues);
	QueryResultIterable<Key<T>> keys = q.fetchKeys();
	return keys;
    }

    public T getByExample(T u, String... matchProperties) {
	Query<T> q = ofy().query(clazz);
	// Find non-null properties and add to query
	for (String propName : matchProperties) {
	    Object propValue = getPropertyValue(u, propName);
	    q.filter(propName, propValue);
	}
	T obj = q.get();
	return obj;
    }

    public List<T> listByExample(T u, String... matchProperties) {
	Query<T> q = ofy().query(clazz);
	// Find non-null properties and add to query
	for (String propName : matchProperties) {
	    Object propValue = getPropertyValue(u, propName);
	    q.filter(propName, propValue);
	}
	List<T> list = q.list();
	return list;
    }

    public List<T> listAll() {
	Query<T> q = ofy().query(clazz);
	List<T> list = q.list();
	return list;
    }

    private Object getPropertyValue(Object obj, String propertyName) {
	BeanInfo beanInfo;
	try {
	    beanInfo = Introspector.getBeanInfo(obj.getClass());
	} catch (IntrospectionException e) {
	    throw new RuntimeException(e);
	}
	PropertyDescriptor[] propertyDescriptors = beanInfo
		.getPropertyDescriptors();
	for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
	    String propName = propertyDescriptor.getName();
	    if (propName.equals(propertyName)) {
		Method readMethod = propertyDescriptor.getReadMethod();
		try {
		    Object value = readMethod.invoke(obj, new Object[] {});
		    return value;
		} catch (IllegalArgumentException e) {
		    throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
		    throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
		    throw new RuntimeException(e);
		}
	    }
	}
	return null;
    }

}