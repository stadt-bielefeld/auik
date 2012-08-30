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

package de.bielefeld.umweltamt.aui.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;
import de.bielefeld.umweltamt.aui.mappings.DatabaseTableWithCollection;

/**
 * A wrapper class for all access to the database, which only allows certain
 * functionality to the user/programmer, handles database exceptions and makes
 * sure modifying access is run within a transaction.<br>
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseAccess {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** The type of the DatabaseAccess */
    public enum DatabaseAccessType {
        /* Database access */
        GET_SESSION, BEGIN_TRANSACTION, COMMIT, ROLLBACK,
        /* Operations on entitys */
        GET, SAVEORUPDATE, MERGE, DELETE,
        /* Querys and criteria */
        CREATE_QUERY, CREATE_CRITERIA, LIST, LIST_CRITERIA,
        UNIQUE_RESULT, ITERATE
    }

    /** The session of the localThread */
    private Session session = null;
    /** The transaction */
    private Transaction transaction = null;
    /** A query */
    private Query query = null;
    /** A Criteria */
    private Criteria criteria = null;

    /**
     * A simple constructor
     * @param type The type of the DatabaseAccess
     */
    public DatabaseAccess(/*DatabaseAccessType type*/) {
//        this.type = type;
    }

    /**
     * Check if the proxy or persistent collection is initialized.
     * @param proxy a persistable object, proxy, persistent collection or
     *            <code>null</code>
     * @return <code>true</code> if the argument is already initialized, or is
     *         not a proxy or collection
     */
    public boolean isInitialized(Object proxy) {
        return Hibernate.isInitialized(proxy);
    }

    /**
     * Get the entity of clazz with the id
     * @param clazz The requested Class
     * @param id The requested id
     * @return Object The requested object
     */
    public Object get(Class<?> clazz, Serializable id) {
        return this.get(clazz, id, false);
    }

    /**
     * Get the entity of clazz with the id and initialize all Collections
     * @param clazz The requested Class
     * @param id The requested id
     * @return Object The requested object
     */
    public Object getAndInitCollections(Class<?> clazz, Serializable id) {
        return this.get(clazz, id, true);
    }

    /**
     * Get the entity of clazz with the id (and initialize all Collections)
     * @param clazz The requested Class
     * @param id The requested id
     * @param init boolean Initialize the Collections of the Class
     * @return Object The requested object
     */
    private Object get(Class<?> clazz, Serializable id, boolean init) {
        Object result = null;
        try {
            result = this.getSession().get(clazz, id);

            if (init) {
                Vector<Collection<?>> collections =
                    ((DatabaseTableWithCollection) result).getToInitCollections();
                for (Collection<?> collection : collections) {
                    Hibernate.initialize(collection);
                }
            }
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.GET, false);
        } finally {
            this.closeSession();
        }

        /* If the object is virtual deletable and virtual deleted,
         * return null */
        AbstractVirtuallyDeletableDatabaseTable virtDelObjekt = null;
        if (result instanceof AbstractVirtuallyDeletableDatabaseTable) {
            virtDelObjekt = (AbstractVirtuallyDeletableDatabaseTable) result;
            if (virtDelObjekt.is_deleted()) {
                return null;
            }
        }

        return result;
    }

    /**
     * Save or update an Object from the database (within a Transaction)<br>
     * Usage:<br>
     * <code>
     * boolean success = new DatabaseAccess().saveOrUpdate(myToModifyObject);
     * </code>
     * @param object The Object to save or update
     * @return boolean True, if everything went as planned, false otherwise
     */
    public boolean saveOrUpdate(Object object) {
        boolean success = false;
        /* Begin a new Transaction */
        if (this.beginTransaction()) {
            try {
                /* Save or update the object */
                this.getSession().saveOrUpdate(object);
                /* Commit the transaction */
                if (this.commitTransaction()) {
                    success = true;
                }
            } catch (HibernateException he) {
                this.handleDBException(
                    he, DatabaseAccessType.SAVEORUPDATE, true);
            } finally {
                this.closeSession();
            }
        }
        return success;
    }

    /**
     * Merge an Object from the database (within a Transaction)<br>
     * Usage:<br>
     * <code>
     * MyObject myMergedObject = new DatabaseAccess().merge(myToModifyObject);
     * </code>
     * @param object The Object to merge
     * @return Object the new Object, if everything went as planned,
     *      null otherwise
     */
    public Object merge(Object object) {
        boolean success = false;
        Object persistent = null;
        /* Begin a new Transaction */
        if (this.beginTransaction()) {
            try {
                /* Merge the object and set it to the persistent version */
                persistent = this.getSession().merge(object);
                /* Commit the transaction */
                if (this.commitTransaction()) {
                    success = true;
                }
            } catch (HibernateException he) {
                this.handleDBException(he, DatabaseAccessType.MERGE, true);
            } finally {
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
     * @param object The Object to delete
     * @return boolean True, if everything went as planned, false otherwise
     */
    public boolean delete(Object object) {

        /* If the object is virtual deletable, just delete it virtually */
        AbstractVirtuallyDeletableDatabaseTable virtDelObjekt = null;
        if (object instanceof AbstractVirtuallyDeletableDatabaseTable) {
            virtDelObjekt = (AbstractVirtuallyDeletableDatabaseTable) object;
            virtDelObjekt.set_deleted(true);
            return this.saveOrUpdate(virtDelObjekt);
        }

        /* Really delete the object */
        boolean success = false;
        /* Begin a new Transaction */
        if (this.beginTransaction()) {
            try {
                /* Delete the object */
                this.getSession().delete(object);
                /* Commit the transaction */
                if (this.commitTransaction()) {
                    success = true;
                }
            } catch (HibernateException he) {
                this.handleDBException(he, DatabaseAccessType.DELETE, true);
            } finally {
                this.closeSession();
            }
        }
        return success;
    }

    /**
     * Create a new instance of Query for the given query String<br>
     * Usage:<br>
     * <code><pre>
     * List<?> result = new DatabaseAccess().createQuery(
     *     "from Anh49Verwaltungsverfahren as verfahren "
     *         + "where verfahren.anh49Fachdaten = :fachdaten "
     *         + "order by verfahren.datum")
     *     .setEntity("fachdaten", fachdaten)
     *     .list();</pre></code>
     * @param queryString The query String
     * @return DatabaseAccess this
     */
    public DatabaseAccess createQuery(String queryString) {
        try {
            this.query = this.getSession().createQuery(queryString);
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.CREATE_QUERY, false);
        } finally {
            // This place is intentionally left blank.
        }
        return this;
    }

    /* Setter for named parameters */
    public DatabaseAccess setBoolean(String name, boolean val) {
        this.query.setBoolean(name, val);
        return this;
    }

    public DatabaseAccess setDate(String name, Date date) {
        this.query.setDate(name, date);
        return this;
    }

    public DatabaseAccess setEntity(String name, Object val) {
        this.query.setEntity(name, val);
        return this;
    }

    public DatabaseAccess setInteger(String name, int val) {
        this.query.setInteger(name, val);
        return this;
    }

    public DatabaseAccess setString(String name, String val) {
        this.query.setString(name, val);
        return this;
    }

    /* More query configuration */
    /**
     * Set the maximum number of rows to retrieve. If not set, there is no limit
     * to the number of rows retrieved.
     * @param maxResults the maximum number of rows
     * @return DatabaseAccess this
     */
    public DatabaseAccess setMaxResults(int maxResults) {
        this.query.setMaxResults(maxResults);
        return this;
    }

    // TODO: Do we really need this? (Cache needs to be set in config as well)
    public DatabaseAccess setCacheable(boolean cacheable) {
        this.query.setCacheable(cacheable);
        return this;
    }

    // TODO: Do we really need this?
    public DatabaseAccess setCacheRegion(String cacheRegion) {
        this.query.setCacheRegion(cacheRegion);
        return this;
    }

    /**
     * Execute the query and get the result as a List<?>
     * @return List<?> The result of the Query
     */
    /* Note: The result of the query only lives as long as the session! */
    public List<?> list() {
        List<?> queryResult = null;
        List<Object> virtDelResult = new ArrayList<Object>();

        try {
            queryResult = this.query.list();
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.LIST, false);
        } finally {
            this.closeSession();
        }

        // No results? Return empty list
        if (queryResult.isEmpty()) {
            return queryResult;
        }

        // Not a VirtDelDBTable? Return queryResult
        Object firstObject = queryResult.get(0);
        if (!(firstObject instanceof AbstractVirtuallyDeletableDatabaseTable)) {
            return queryResult;
        }

        // VirtDelDBTable? Build a new result with just the not deleted rows
        // TODO: This is the most dirty solution...
        // Add the "where xyz._deleted = FALSE" to all the querys?
        // Can we let the database do that? Maybe with an Index? Or many...
        AbstractVirtuallyDeletableDatabaseTable virtDelObjekt = null;
        for (Object object : queryResult) {
            virtDelObjekt = (AbstractVirtuallyDeletableDatabaseTable) object;
            if (!virtDelObjekt.is_deleted()) {
                virtDelResult.add(object);
            }
        }
        return virtDelResult;
    }

    /**
     * Execute the query and get the result as an array
     * @return Object[] The result of the Query
     */
    public <T> T[] array(T[] arrayType) {
        return this.list().toArray(arrayType);
    }

    /**
     * Convenience method to return a single instance that matches the query, or
     * null if the query returns no results.
     * @return The single result or <code>null</code>
     */
    public Object uniqueResult() {
        Object result = null;
        List<?> queryResult = null;
        try {
            // Can't use this:
            // result = this.query.uniqueResult();
            // because of the virt. deleted objects

            // Get filtered results (no virt.deleted objects)
            queryResult = this.list();
            // If there is one result, return it
            // If there is none, return null
            // If there are more, throw an Exception
            switch (queryResult.size()) {
                case 1: result = queryResult.get(0);  break;
                case 0: result = null;                break;
                default:
                    throw new HibernateException(
                        "More than one result in unique request!");
            }
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.UNIQUE_RESULT, false);
        } finally {
            this.closeSession();
        }
        return result;
    }

    /**
     * Create a new instance of <code>Criteria</code> for the given
     * <code>Class</code><br>
     * <br>
     * Note: For all classes except those in the
     * <code>de.bielefeld.umweltamt.aui.mappings.tipi</code>-Package
     * global Restrictions are set (field <code>_deleted = false</code>).<br>
     * Usage:<br>
     * <code><pre>
     * List<?> result = new DatabaseAccess()
     *     .createCriteria(Anh49Verwaltungsverfahren.class)
     *     .addRestrictionEqual("anh49Fachdaten", fachdaten))
     *     .addAscOrder("datum")
     *     .listCriteria();</pre></code>
     * @param persistentClass The class
     * @return DatabaseAccess this
     */
    public DatabaseAccess createCriteria(Class<?> persistentClass) {
        try {
            this.criteria = this.getSession().createCriteria(persistentClass);
            // Add global restrictions
            // For all classes except those in the tipi package
            if (!(persistentClass.getPackage().getName()
                .equals("de.bielefeld.umweltamt.aui.mappings.tipi"))) {
                this.criteria.add(Restrictions.eq("_deleted", false));
                // This will be for the new hibernate mappings:
//                this.criteria.add(Restrictions.eq("deleted", false));
            }
        } catch (HibernateException he) {
            this.handleDBException(
                he, DatabaseAccessType.CREATE_CRITERIA, false);
        } finally {
            // This place is intentionally left blank.
        }
        return this;
    }

    /* Restrictions */
    public DatabaseAccess addRestrictionEqual(
        String propertyName, Object value) {
        // This is a workaround for hibernate issue HHH-2951
        // See: https://hibernate.onjira.com/browse/HHH-2951
        if (value == null) {
            this.criteria.add(Restrictions.isNull(propertyName));
        } else {
            this.criteria.add(Restrictions.eq(propertyName, value));
        }
        return this;
    }

    public DatabaseAccess addRestrictionILike(
        String propertyName, Object value) {
        this.criteria.add(Restrictions.ilike(propertyName, value));
        return this;
    }

    public DatabaseAccess addRestrictionOr(Criterion lhs, Criterion rhs) {
        this.criteria.add(Restrictions.or(lhs, rhs));
        return this;
    }

    /**
     * Add ascending order by a given property to the <code>Criteria</code>
     * @param propertyName Property to sort by
     * @return <code>this</code>
     */
    public DatabaseAccess addAscOrder(String propertyName) {
        this.criteria.addOrder(Order.asc(propertyName));
        return this;
    }

    /**
     * Add descending order by a given property to the <code>Criteria</code>
     * @param propertyName Property to sort by
     * @return <code>this</code>
     */
    public DatabaseAccess addDescOrder(String propertyName) {
        this.criteria.addOrder(Order.desc(propertyName));
        return this;
    }

    /**
     * Add ascending order by a identifier property to the <code>Criteria</code>
     * @return <code>this</code>
     */
    public DatabaseAccess addIdOrder(Class<?> entityClass) {
        this.criteria.addOrder(Order.asc(
            this.getSession().getSessionFactory()
                .getClassMetadata(entityClass).getIdentifierPropertyName()));
        return this;
    }

    /**
     * Execute the criteria and get the result as a <code>List&lt;?&gt;</code>
     * @return List<?> The result of the Criteria
     */
    public List<?> listCriteria() {
        List<?> criteriaResult = null;
        try {
            criteriaResult = this.criteria.list();
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.LIST_CRITERIA, false);
        } finally {
            this.closeSession();
        }
        return criteriaResult;
    }

    /**
     * Execute the criteria and get the result as an array
     * @return Object[] The result of the criteria
     */
    public <T> T[] arrayCriteria(T[] arrayType) {
        return this.listCriteria().toArray(arrayType);
    }

    /**
     * Cast the result list to the right type
     * @param <T>
     * @param type
     * @return
     */
    // This will be my only suppressed warning, promise! ;-)
    @SuppressWarnings("unchecked")
    public <T> List<T> listCriteriaCastToType(T type) {
        List<T> resultList = new ArrayList<T>();
        List<?> objectList = this.listCriteria();
        T result = null;
        for (Object object : objectList) {
            result = (T) object;
            resultList.add(result);
        }
        return resultList;
    }

    /*
     * Are you thinking about using this? Don't.
     * Don't think about it and don't use it.
     * It only makes sense if the instances are already in the session or
     * in the cache and we should only think about such stuff if performance
     * becomes an issue.
     */
    public Iterator<?> iterate() {
        Iterator<?> result = null;
        /*
        try {
            result = this.query.iterate();
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.ITERATE, false);
        } finally {
            this.closeSession();
        }
        */
        return result;
    }

    /** Get the (current) Session */
    private Session getSession() {
        if (this.session == null) {
            try {
                this.session = HibernateSessionFactory.currentSession();
            } catch (HibernateException he) {
                this.handleDBException(he, DatabaseAccessType.GET_SESSION, true);
            } finally {
                // This place is intentionally left blank.
            }
        }
        return this.session;
    }

    /** Close the Session */
    private void closeSession() {
        if (this.session == null) {
            log.error("Trying to close a session we do not have.");
            return;
        }
        HibernateSessionFactory.closeSession();
    }

    /** Get a new Transaction */
    private boolean beginTransaction() {
        boolean success = false;
        /* Check if we have an old Transaction */
        if (this.transaction != null) {
            log.error("There seams to be an old Transaction active!");
            return success;
        }
        try {
            this.transaction = this.getSession().beginTransaction();
            success = true;
        } catch (HibernateException he) {
            this.handleDBException(
                he, DatabaseAccessType.BEGIN_TRANSACTION, true);
        } finally {
            // This place is intentionally left blank.
        }
        return success;
    }

    /**
     * Commit the transaction and set it to null.
     * @return boolean True, if everything went as planed, false otherwise
     */
    private boolean commitTransaction() {
        boolean success = false;
        try {
            this.transaction.commit();
            success = this.transaction.wasCommitted();
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.COMMIT, true);
        } finally {
            if (!success) {
                log.warn("Commit did not throw an Exception, but the "
                    + "Transaction was not committed for some reason.");
                try {
                    this.transaction.rollback();
                } catch (HibernateException he) {
                    this.handleDBException(
                        he, DatabaseAccessType.ROLLBACK, true);
                }
            }
            this.transaction = null;
        }
        return success;
    }

    /**
     * Make sure we always pass on the type of the failed database access
     * @param exception The occurred Exception
     * @param type The type of the DatabaseAccess
     * @param fatal <code>true</code> if the error was fatal, <code>false</code>
     *            otherwise
     */
    private void handleDBException(Throwable exception,
        DatabaseAccessType type, boolean fatal) {

        /* For testing talk with the user... */
        String message = "TESTPHASE: Hier /könnte/ etwas schief gegangen sein. "
            + "Wenn sich das AUIK ab jetzt anders als sonst verhält, "
            + "bitte die Stelle im Programm notieren und bei mir (Conny) "
            + "oder Gerd melden! Danke!";

        /* via GUI */
        GUIManager.getInstance().setErrorStatus(message);
        /* via message dialog */
        GUIManager.getInstance().showErrorMessage(message, "DB-Fehler");
        /* via stdout */
        log.error(message); // + "\n" + exception.getMessage());

        /* Hand the exception to the DatabaseManager */
        DatabaseManager.getInstance().handleDBException(exception,
            "DatabaseAccess." + type, fatal);
    }
}
