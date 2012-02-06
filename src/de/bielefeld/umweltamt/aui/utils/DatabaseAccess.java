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

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A wrapper class for all access to the database, which only allows certain
 * functionality to the user/programmer, handles database exceptions and
 * makes sure modifying access is run within a transaction.
 *
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class DatabaseAccess {

    /** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();
    /** Database manager */
    private static final DatabaseManager dbManager =
        DatabaseManager.getInstance();

//    public enum DatabaseAccessType {
//        GET, SAVEORUPDATE, DELETE, QUERY
//    }
//    /** Type of the DatabaseAccess */
//    private DatabaseAccessType type = null;

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
            dbManager.handleDBException(he, "get", true);
        } finally {
            // This place is intentionally left blank.
        }
        return result;
    }

    /**
     * Save or update an Object from the database (within a Transaction)<br>
     * Usage:<pre>
     * boolean success = new DatabaseAccess().delete(myToModifyObject);
     * </pre>
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
                dbManager.handleDBException(
                        he, "DatabaseAccess.saveOrUpdate()", true);
            }
        }
        return success;
    }

    /**
     * Delete an Object from the database (within a Transaction)<br>
     * Usage: <pre>
     * boolean success = new DatabaseAccess().delete(myToDeleteObject);
     * </pre>
     * @param object The Object to delete
     * @return boolean True, if everything went as planned, false otherwise
     */
    public boolean delete(Object object) {
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
                dbManager.handleDBException(
                        he, "DatabaseAccess.delete()", true);
            }
        }
        return success;
    }

    /**
     * Create a query with the given String<br>
     * Usage:
     * <pre>List<?> result = new DatabaseAccess().createQuery(
     *          "from Anh49Verwaltungsverfahren as verfahren where " +
     *          "verfahren.anh49Fachdaten = :fachdaten " +
     *          "order by verfahren.datum")
     *          .setEntity("fachdaten", fachdaten)
     *          .list();</pre>
     * @param queryString The Query String
     * @return DatabaseAccess this
     */
    public DatabaseAccess createQuery(String queryString) {
        this.query = getSession().createQuery(queryString);
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
    // TODO: Do we really need this?
    public DatabaseAccess uniqueResult() {
        this.query.uniqueResult();
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
            dbManager.handleDBException(he, "list", true);
        } finally {
            // This place is intentionally left blank.
        }
        return result;
    }

    /**
     * Execute the query and get the result as an Iterator<?>
     * @return Iterator<?> The result of the Query
     */
    /* Note: The result of the query only lives as long as the session! */
    public Iterator<?> iterate() {
        Iterator<?> result = null;
        try {
            result = this.query.iterate();
        } catch (HibernateException he) {
            dbManager.handleDBException(he, "iterate", true);
        } finally {
            // This place is intentionally left blank.
        }
        return result;
    }

    /** Get the current Session */
    private Session getSession() {
        if (this.session == null) {
            try {
                this.session = HibernateSessionFactory.currentSession();
            } catch (HibernateException he) {
                dbManager.handleDBException(he, "getSession", true);
            } finally {
                // This place is intentionally left blank.
            }
        }
        return this.session;
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
            dbManager.handleDBException(he, "beginTransaction", true);
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
            dbManager.handleDBException(he, "commitTransaction", true);
        } finally {
            if (!success) {
                log.warn("Commit did not throw an Exception, but the "
                        + "Transaction was not committed for some reason.");
                try {
                    this.transaction.rollback();
                } catch (HibernateException he) {
                    dbManager.handleDBException(he, "ROLLBACK", true);
                }
            }
            this.transaction = null;
        }
        return success;
    }
}
