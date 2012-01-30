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

package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;

/**
 * A class that represents a row in the 'ANH_49_VERWALTUNGSVERF' table.
 * This class may be customized as it is never re-generated
 * after being created.
 * <br><br>
 * Well, not really. I just copied this from a generated class and modified it!
 * 
 * @author <a href="mailto:Conny.Pearce@bielefeld.de">Conny Pearce (u633z)</a>
 */
public class Anh49Verwaltungsverfahren
    extends AbstractAnh49Verwaltungsverfahren
    implements Serializable
{
	private static final long serialVersionUID = 7696154300748058009L;

	/** Database manager */
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /** Simple constructor of Anh49Verwaltungsverfahren instances. */
    public Anh49Verwaltungsverfahren() {
    	super();
    }

    /**
     * Constructor of Anh49Verwaltungsverfahren instances given a simple
     * primary key.
     * @param verwaltungsverfahrenID
     */
    public Anh49Verwaltungsverfahren(java.lang.Integer verwaltungsverfahrenID) {
        super(verwaltungsverfahrenID);
    }

    /**
     * Get all "Verwaltungsverfahren" for a given "Anhang 49" object
     * @param fachdaten The "Anhang 49" object
     * @return List<Anh49Verwaltungsverfahren>
     */
    public static List<Anh49Verwaltungsverfahren>
    		getVerwaltungsverfahren(Anh49Fachdaten fachdaten) {
        List<Anh49Verwaltungsverfahren> verwaltungsverfahrensListe = null;

        try {
            verwaltungsverfahrensListe = (List<Anh49Verwaltungsverfahren>)
            	HibernateSessionFactory.currentSession().createQuery(
                    "from Anh49Verwaltungsverfahren as verfahren where " +
                    "verfahren.anh49Fachdaten = ? " +
                    "order by verfahren.datum")
                    .setEntity(0, fachdaten)
                    .list();

            log.debug("Verwaltungsverfahren für " + fachdaten
            		+ ", Anzahl: " + verwaltungsverfahrensListe.size());
        } catch (HibernateException hibernateException) {
        	Anh49Verwaltungsverfahren.dbManager.handleDBException(
        			hibernateException,
        			"Could not get the list of Verwaltungsverfahren for "
        			+ "Anhang 49 " + fachdaten,
        			false);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return verwaltungsverfahrensListe;
    }

    public static void saveOrUpdateVerwaltungsverfahren(
            Anh49Verwaltungsverfahren verwaltungsverfahren) {
        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.saveOrUpdate(verwaltungsverfahren);

            tx.commit();

        } catch (HibernateException e) {
            // TODO: This exception handling should be moved into the dbmanager
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Änderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (Anh49Analysen)", e);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public static boolean removeVerwaltungsverfahren(
            Anh49Verwaltungsverfahren verwaltungsverfahren) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(verwaltungsverfahren);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    dbManager.handleDBException(e1, "Anh49AnalysenModel.objectRemoved", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }
}
