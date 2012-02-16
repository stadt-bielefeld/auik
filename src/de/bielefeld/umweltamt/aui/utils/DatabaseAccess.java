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
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.GUIManager;
import de.bielefeld.umweltamt.aui.HauptFrame;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.AbstractVirtuallyDeletableDatabaseTable;

/**
 * A wrapper class for all access to the database, which only allows certain
 * functionality to the user/programmer, handles database exceptions and makes
 * sure modifying access is run within a transaction.<br>
 * <br>
 * TODO: Let the DatabaseAccess only work on Subclasses of
 * AbstractVirtuallyDeletableDatabaseTable to ensure that we do not really
 * delete anything in the database.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseAccess {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** The type of the DatabaseAccess */
    public enum DatabaseAccessType {
        /* Database access */
        INITIALIZE, GET_SESSION, BEGIN_TRANSACTION, COMMIT, ROLLBACK,
        /* Operations on entitys */
        GET, SAVEORUPDATE, MERGE, DELETE,
        /* Querys */
        CREATE_QUERY, CREATE_FILTER, LIST, UNIQUE_RESULT, ITERATE
    }

    /** The session of the localThread */
    private Session session = null;
    /** The transaction */
    private Transaction transaction = null;
    /** A query */
    private Query query = null;

    /**
     * A simple constructor
     * @param type The type of the DatabaseAccess
     */
    public DatabaseAccess(/*DatabaseAccessType type*/) {
//        this.type = type;
    }

    /**
     * Force initialization of a proxy or persistent collection.<br>
     * <br>
     * Note: This only ensures intialization of a proxy object or collection; it
     * is not guaranteed that the elements INSIDE the collection will be
     * initialized/materialized.
     * @param proxy a persistable object, proxy, persistent collection or
     *            <code>null</code>
     */
    public void initialize(Object proxy) {
        try {
            Hibernate.initialize(proxy);
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.INITIALIZE, false);
        } finally {
            // This place is intentionally left blank.
        }
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
        Object result = null;
        try {
            result = this.getSession().get(clazz, id);
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
                this.session.saveOrUpdate(object);
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
     * boolean success = new DatabaseAccess().merge(myToModifyObject);
     * </code>
     * @param object The Object to merge
     * @return boolean True, if everything went as planned, false otherwise
     */
    public boolean merge(Object object) {
        boolean success = false;
        /* Begin a new Transaction */
        if (this.beginTransaction()) {
            try {
                /* Save or update the object */
                this.session.merge(object);
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
        return success;
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
                this.session.delete(object);
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
            this.query = getSession().createQuery(queryString);
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.CREATE_QUERY, false);
        } finally {
            // This place is intentionally left blank.
        }
        return this;
    }

    /**
     * Create a new instance of Query for the given Collection and filter
     * String.<br>
     * Usage:<br>
     * <code><pre>
     * List<?> result = new DatabaseAccess().createFilter(
     *     newProbe.getAtlAnalysepositionen(),
     *     "order by this.atlParameter.reihenfolge")
     *     .list();</pre></code>
     * @param collection The Collection
     * @param filterString The filter String
     * @return DatabaseAccess this
     */
    public DatabaseAccess createFilter(Object collection, String filterString) {
        try {
            this.query = getSession().createFilter(collection, filterString);
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.CREATE_FILTER, false);
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

    public DatabaseAccess setDouble(String name, double val) {
        this.query.setDouble(name, val);
        return this;
    }

    public DatabaseAccess setEntity(String name, Object val) {
        this.query.setEntity(name, val);
        return this;
    }

    public DatabaseAccess setFloat(String name, float val) {
        this.query.setFloat(name, val);
        return this;
    }

    public DatabaseAccess setInteger(String name, int val) {
        this.query.setInteger(name, val);
        return this;
    }

    public DatabaseAccess setLong(String name, long val) {
        this.query.setLong(name, val);
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
        List<?> result = null;
        try {
            result = this.query.list();
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.LIST, false);
        } finally {
            this.closeSession();
        }

        // TODO: This is the most dirty solution...
        // Add the "where xyz._deleted = FALSE" to all the querys?
        // Can we let the database do that? Maybe with an Index? Or many...
        AbstractVirtuallyDeletableDatabaseTable virtDelObjekt = null;
        for (Object object : result) {
            /* If the object is virtual deletable and virtual deleted,
             * remove it from the result list */
            if (object instanceof AbstractVirtuallyDeletableDatabaseTable) {
                virtDelObjekt = (AbstractVirtuallyDeletableDatabaseTable) object;
                if (virtDelObjekt.is_deleted()) {
                    result.remove(object);
                }
            }
        }

        return result;
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
    // TODO: There are a lot of querys where we should use this but are still
    // using the first object of the list.
    public Object uniqueResult() {
        Object result = null;
        try {
            result = this.query.uniqueResult();
        } catch (HibernateException he) {
            this.handleDBException(he, DatabaseAccessType.UNIQUE_RESULT, false);
        } finally {
            this.closeSession();
        }

        // TODO: This is the most dirty solution...
        // Add the "where xyz._deleted = FALSE" to all the querys?
        // Can we let the database do that? Maybe with an Index? Or many...
        AbstractVirtuallyDeletableDatabaseTable virtDelObjekt = null;
        if (result instanceof AbstractVirtuallyDeletableDatabaseTable) {
            virtDelObjekt = (AbstractVirtuallyDeletableDatabaseTable) result;
            if (virtDelObjekt.is_deleted()) {
                result = null;
            }
        }

        return result;
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
        HauptFrame runningFrame = GUIManager.getInstance().getRunningFrame();
        if (runningFrame != null) {
            runningFrame.changeStatus(message, HauptFrame.ERROR_COLOR);
        }
        /* via message dialog */
        JOptionPane.showMessageDialog(runningFrame, message, "Fehler",
            JOptionPane.ERROR_MESSAGE);
        /* via stdout */
        log.error(message);

        /* Hand the exception to the DatabaseManager */
        DatabaseManager.getInstance().handleDBException(exception,
            "DatabaseAccess." + type, fatal);
    }
}
