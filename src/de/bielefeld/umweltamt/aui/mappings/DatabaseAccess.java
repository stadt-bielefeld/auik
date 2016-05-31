/**
 * Copyright 2005-2042, Stadt Bielefeld
 *
 * This file is part of AUIK (Anlagen- und Indirekteinleiter-Kataster).
 *
 * AUIK is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 2 of the License, or (at your
 * option) any later version.
 *
 * AUIK is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with AUIK. If not, see <http://www.gnu.org/licenses/>.
 *
 * AUIK has been developed by Stadt Bielefeld and Intevation GmbH.
 */

package de.bielefeld.umweltamt.aui.mappings;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.resource.transaction.spi.TransactionStatus; 



import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A wrapper class for all access to the database, which only allows certain
 * functionality to the user/programmer, handles database exceptions and makes
 * sure modifying access is run within a transaction.<br>
 *
 * @author <a href="mailto:post@connz.de">Conny Pearce (u633z)</a>
 */
public class DatabaseAccess
{

	/** Logging */
	private static final AuikLogger log = AuikLogger.getLogger();

	/** The session of the localThread */
	private Session session = null;
	/** The transaction */
	private Transaction transaction = null;
	/** A Criteria */
	private Criteria criteria = null;

	/**
	 * A simple constructor
	 */
	public DatabaseAccess()
	{
		// This is intentionally left blank
	}

	/**
	 * Check if the proxy or persistent collection is initialized.<br>
	 * TODO: This is just used in the toString method of the AtlProbenahme.
	 * Check if we really need it!
	 * 
	 * @param proxy
	 *            a persistable object, proxy, persistent collection or
	 *            <code>null</code>
	 * @return <code>true</code> if the argument is already initialized, or is
	 *         not a proxy or collection
	 */
	public static boolean isInitialized(Object proxy)
	{
		return Hibernate.isInitialized(proxy);
	}

	/**
	 * Get the entity of clazz with the id
	 * 
	 * @param clazz
	 *            The requested Class
	 * @param id
	 *            The requested id
	 * @return Object The requested object
	 */
	public Object get(Class<?> clazz, Serializable id)
	{
		Object result = null;
		try
		{
			result = this.getSession().get(clazz, id);
		}
		catch (HibernateException he)
		{
			this.handleDBException(he, false);
		}
		finally
		{
			this.closeSession();
		}

		/* If the object is marked as deleted, return null */
		if (this.isMarkedAsDeleted(result))
		{
			return null;
		}

		return result;
	}

	/**
	 * Check if the object is marked as deleted
	 * 
	 * @param object
	 * @return
	 */
	private boolean isMarkedAsDeleted(Object object)
	{
		if (object == null)
			return false;
		Class<?> clazz = object.getClass();
		Method method = null;
		while (clazz != null)
		{
			try
			{
				method = clazz.getDeclaredMethod("isDeleted");
				return (Boolean) method.invoke(object);
			}
			catch (NoSuchMethodException nsme)
			{
				// This is intentionally left blank
			}
			catch (Exception e)
			{
				log.error("This /should/ not happen...");
			}
			clazz = clazz.getSuperclass();
		}
		return false;
	}

	/**
	 * Merge an Object from the database (within a Transaction)<br>
	 * Usage:<br>
	 * <code>
	 * MyObject myMergedObject = new DatabaseAccess().merge(myToModifyObject);
	 * </code>
	 * 
	 * @param object
	 *            The Object to merge
	 * @return Object the new Object, if everything went as planned,
	 *         null otherwise
	 */
	public Object merge(Object object)
	{
		boolean success = false;
		Object persistent = null;
		/* Begin a new Transaction */
		if (this.beginTransaction())
		{
			try
			{
				/* Merge the object and set it to the persistent version */
				persistent = this.getSession().merge(object);
				/* Commit the transaction */
				if (this.commitTransaction())
				{
					success = true;
				}
			}
			catch (HibernateException he)
			{
				this.handleDBException(he, true);
			}
			finally
			{
				this.closeSession();
			}
		}
		return success ? persistent : null;
	}

	/**
	 * Delete an Object from the database (within a Transaction)<br>
	 * Usage:<br>
	 * <code>
	 * boolean success = new DatabaseAccess().delete(myToDeleteObject);
	 * </code>
	 * 
	 * @param object
	 *            The Object to delete
	 * @return boolean True, if everything went as planned, false otherwise
	 */
	public boolean delete(Object object)
	{
		/* Only mark the object as deleted and save that */
		this.markedAsDeleted(object);
		return (this.merge(object) != null);

		/* Really delete the object */
		//        boolean success = false;
		//        /* Begin a new Transaction */
		//        if (this.beginTransaction()) {
		//            try {
		//                /* Delete the object */
		//                this.getSession().delete(object);
		//                /* Commit the transaction */
		//                if (this.commitTransaction()) {
		//                    success = true;
		//                }
		//            } catch (HibernateException he) {
		//                this.handleDBException(he, DatabaseAccessType.DELETE, true);
		//            } finally {
		//                this.closeSession();
		//            }
		//        }
		//        return success;
	}

	/**
	 * Mark an object as deleted
	 * 
	 * @param object
	 * @return
	 */
	private void markedAsDeleted(Object object)
	{
		if (object == null)
			return;
		Class<?> clazz = object.getClass();
		Method method = null;
		while (clazz != null)
		{
			try
			{
				method = clazz.getDeclaredMethod("setDeleted", boolean.class);
				method.invoke(object, true);
			}
			catch (NoSuchMethodException nsme)
			{
				// This is intentionally left blank
			}
			catch (Exception e)
			{
				log.error("This /should/ not happen...");
			}
			clazz = clazz.getSuperclass();
		}
	}

	/**
	 * Execute a detached query/criteria and return the result as a list <br>
	 * <br>
	 * Note: For all classes except those in the
	 * <code>de.bielefeld.umweltamt.aui.mappings.tipi</code>-Package
	 * global Restrictions are set (field <code>_deleted = false</code>). <br>
	 * <br>
	 * Usage: <code><pre>
	 * DetachedCriteria criteria =
	 *     DetachedCriteria.forClass(Anh49Verwaltungsverfahren.class);
	 * criteria.add(Restrictions.eq("anh49Fachdaten", fachdaten);
	 * ...
	 * criteria.addOrder(Order.asc("datum"));
	 * ...
	 * List<Anh49Verwaltungsverfahren> result = new DatabaseAccess()
	 *     .executeCriteriaToList(criteria, Anh49Verwaltungsverfahren.class);
	 * </pre></code>
	 * 
	 * @param <T>
	 *            type of the result
	 * @param detachedCriteria
	 *            query/criteria to execute
	 * @param type
	 *            type of the result
	 * @return List<T> result of the query/criteria as a list
	 */
	/*
	 * We are using detached criteria to keep the opening and closing of the
	 * session within on method call here to keep it saver
	 */
	public <T> List<T> executeCriteriaToList(
		DetachedCriteria detachedCriteria, T type)
	{
		this.getExecutableCriteria(detachedCriteria);
		return this.listCriteriaCastToType(type);
	}

	/**
	 * Execute a detached query/criteria and return the result as an array <br>
	 * <br>
	 * Note: For all classes except those in the
	 * <code>de.bielefeld.umweltamt.aui.mappings.tipi</code>-Package
	 * global Restrictions are set (field <code>_deleted = false</code>). <br>
	 * <br>
	 * Usage: <code><pre>
	 * DetachedCriteria criteria =
	 *     DetachedCriteria.forClass(Anh49Verwaltungsverfahren.class);
	 * criteria.add(Restrictions.eq("anh49Fachdaten", fachdaten);
	 * ...
	 * criteria.addOrder(Order.asc("datum"));
	 * ...
	 * Anh49Verwaltungsverfahren[] result = new DatabaseAccess()
	 *     .executeCriteriaToArray(criteria, new Anh49Verwaltungsverfahren[0]);
	 * </pre></code>
	 * 
	 * @param <T>
	 *            type of the result
	 * @param detachedCriteria
	 *            query/criteria to execute
	 * @param type
	 *            type of the result
	 * @return <code>T[]</code> result of the query/criteria as an array
	 */
	/*
	 * We are using detached criteria to keep the opening and closing of the
	 * session within on method call here to keep it saver
	 */
	public <T> T[] executeCriteriaToArray(
		DetachedCriteria detachedCriteria, T[] type)
	{
		this.getExecutableCriteria(detachedCriteria);
		return this.arrayCriteria(type);
	}

	/**
	 * Execute a detached query/criteria and return the unique result <br>
	 * <br>
	 * Note: For all classes except those in the
	 * <code>de.bielefeld.umweltamt.aui.mappings.tipi</code>-Package
	 * global Restrictions are set (field <code>_deleted = false</code>). <br>
	 * <br>
	 * Usage: <code><pre>
	 * DetachedCriteria criteria =
	 *     DetachedCriteria.forClass(Anh49Verwaltungsverfahren.class);
	 * criteria.add(Restrictions.eq("anh49Fachdaten", fachdaten);
	 * ...
	 * criteria.addOrder(Order.asc("datum"));
	 * ...
	 * Anh49Verwaltungsverfahren result = new DatabaseAccess()
	 *     .executeCriteriaToUniqueResult(
	 *         criteria, new Anh49Verwaltungsverfahren());
	 * </pre></code>
	 * 
	 * @param <T>
	 *            type of the result
	 * @param detachedCriteria
	 *            query/criteria to execute
	 * @param type
	 *            type of the result
	 * @return T result of the query/criteria
	 */
	/*
	 * We are using detached criteria to keep the opening and closing of the
	 * session within on method call here to keep it saver
	 */
	public <T> T executeCriteriaToUniqueResult(
		DetachedCriteria detachedCriteria, T type)
	{
		this.getExecutableCriteria(detachedCriteria);
		List<T> list = this.listCriteriaCastToType(type);
		switch (list.size())
		{
			case 1:
				return list.get(0);
			case 0:
				return null;
			default:
				log.error("More than one result in unique request!");
				return null;
		}
	}

	/**
	 * Get an executable criteria for a given detached criteria.
	 * Add global <code>Restrictions</code>
	 * 
	 * @param detachedCriteria
	 */
	private void getExecutableCriteria(
		DetachedCriteria detachedCriteria)
	{
		try
		{
			this.criteria =
					detachedCriteria.getExecutableCriteria(this.getSession());
			// Add global restrictions
			// For all classes except those in the tipi package
			if (!(this.criteria.toString().contains(
					"de.bielefeld.umweltamt.aui.mappings.elka")))
			{
				this.criteria.add(Restrictions.eq("deleted", false));
			}
		}
		catch (HibernateException he)
		{
			this.handleDBException(he, false);
		}
		finally
		{
			// This place is intentionally left blank.
		}
	}

	/**
	 * Execute the criteria and get the result as a <code>List&lt;?&gt;</code>
	 * 
	 * @return List<?> The result of the Criteria
	 */
	private List<?> listCriteria()
	{
		List<?> criteriaResult = null;
		try
		{
			criteriaResult = this.criteria.list();
		}
		catch (HibernateException he)
		{
			this.handleDBException(he, false);
		}
		finally
		{
			this.closeSession();
		}
		return criteriaResult;
	}

	/**
	 * Execute the criteria and get the result as an array
	 * 
	 * @param <T>
	 * @param arrayType
	 * @return T[]
	 */
	private <T> T[] arrayCriteria(T[] arrayType)
	{
		return this.listCriteria().toArray(arrayType);
	}

	/**
	 * Cast the result list to the right type
	 * 
	 * @param <T>
	 * @param type
	 * @return List<T>
	 */
	// This will be my only suppressed warning, promise! ;-)
	@SuppressWarnings("unchecked")
	private <T> List<T> listCriteriaCastToType(T type)
	{
		List<T> resultList = new ArrayList<T>();
		List<?> objectList = this.listCriteria();
		T result = null;
		for (Object object : objectList)
		{
			result = (T) object;
			resultList.add(result);
		}
		return resultList;
	}

	/**
	 * This is a workaround for hibernate issue HHH-2951<br>
	 * See: https://hibernate.onjira.com/browse/HHH-2951
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public static Criterion getRestrictionsEqualOrNull(
		String propertyName, Object value)
	{
		if (value == null)
		{
			return Restrictions.isNull(propertyName);
		}
		else
		{
			return Restrictions.eq(propertyName, value);
		}
	}

	/**
	 * Get an ascending order for the identifier property of the class<br>
	 * Note: This opens and closes the session!
	 * 
	 * @return <code>Order</code>
	 */
	public Order getIdOrder(Class<?> entityClass)
	{
		Order idOrder = Order.asc(
				this.getSession().getSessionFactory()
						.getClassMetadata(entityClass).getIdentifierPropertyName());
		this.closeSession();
		return idOrder;
	}

	/** Get the (current) Session */
	private Session getSession()
	{
		if (this.session == null)
		{
			try
			{
				this.session = HibernateSessionFactory.currentSession();
			}
			catch (HibernateException he)
			{
				this.handleDBException(he, true);
			}
			finally
			{
				// This place is intentionally left blank.
			}
		}
		return this.session;
	}

	/** Close the Session */
	private void closeSession()
	{
		if (this.session == null)
		{
			log.error("Trying to close a session we do not have.");
			return;
		}
		HibernateSessionFactory.closeSession();
	}

	/** Get a new Transaction */
	private boolean beginTransaction()
	{
		boolean success = false;
		/* Check if we have an old Transaction */
		if (this.transaction != null)
		{
			log.error("There seams to be an old Transaction active!");
			return success;
		}
		try
		{
			this.transaction = this.getSession().beginTransaction();
			success = true;
		}
		catch (HibernateException he)
		{
			this.handleDBException(he, true);
		}
		finally
		{
			// This place is intentionally left blank.
		}
		return success;
	}

	/**
	 * Commit the transaction and set it to null.
	 * 
	 * @return boolean True, if everything went as planed, false otherwise
	 */
	private boolean commitTransaction()
	{
		boolean success = false;
		try
		{
			this.transaction.commit();
			success = this.transaction.getStatus().isOneOf(TransactionStatus.COMMITTED);
			//success = this.transaction.wasCommitted();
		}
		catch (HibernateException he)
		{
			this.handleDBException(he, true);
		}
		finally
		{
			if (!success)
			{
				log.warn("Commit did not throw an Exception, but the "
						+ "Transaction was not committed for some reason.");
				try
				{
					this.transaction.rollback();
				}
				catch (HibernateException he)
				{
					this.handleDBException(he, true);
				}
			}
			this.transaction = null;
		}
		return success;
	}

	/**
	 * Make sure we always pass on the type of the failed database access
	 * 
	 * @param exception
	 *            The occurred Exception
	 * @param fatal
	 *            <code>true</code> if the error was fatal, <code>false</code>
	 *            otherwise
	 */
	private void handleDBException(Throwable exception, boolean fatal)
	{
		/* For testing talk with the user... */

		String message = String
				.format("%s %s",
						"Es ist ein unerwarteter Fehler aufgreten! Der Vorgang wird abgebrochen! Der Fehlertext lautet: ",
						exception.getMessage());

		/* via GUI */
		GUIManager.getInstance().setErrorStatus(message);
		/* via message dialog */
		GUIManager.getInstance().showErrorMessage(message, "DB-Fehler");
		/* via stdout */
		log.error(exception.getStackTrace().toString());

		/* Hand the exception to the DatabaseManager */
		DatabaseManager.getInstance().handleDBException(
														exception, "DatabaseAccess", fatal);
	}
}
