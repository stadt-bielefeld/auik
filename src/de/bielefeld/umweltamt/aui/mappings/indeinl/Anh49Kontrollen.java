/**
 * Copyright 2005-2011, Stadt Bielefeld
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

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;

/**
 * A class that represents a row in the 'ANH_49_KONTROLLEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Anh49Kontrollen
    extends AbstractAnh49Kontrollen
    implements Serializable
{
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Ortstermine instances.
     */
    public Anh49Kontrollen()
    {
    }

    /**
     * Constructor of Anh49Ortstermine instances given a simple primary key.
     * @param ortsterminid
     */
    public Anh49Kontrollen(java.lang.Integer Id)
    {
        super(Id);
    }

    /* Add customized code below */

    /**
     * Liefert einen String der Form "[Datum: Prüfdatum und Pruefergebnis]".
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[Prüfdatum: "+ AuikUtils.getStringFromDate(getPruefdatum()) +", Ergebnis: "+ getPruefergebnis() +"]";
    }

    /**
     * Liefert alle Kontrollen eines bestimmten Fachdatenobjekts.
     */
    public static List getKontrollen(Anh49Fachdaten fd) {
        List ot;
        try {
            Session session = HibernateSessionFactory.currentSession();
            ot = session.createQuery(
                    "from Anh49Kontrollen as kt where " +
                    "kt.anh49Fachdaten = ? " +
                    "order by kt.pruefdatum")
                    .setEntity(0, fd)
                    .list();

            log.debug("Kontrollen für " + fd + ", Anzahl: " + ot.size());
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return ot;
    }

    public static void saveOrUpdateOrtstermin(Anh49Kontrollen kt) {
        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.saveOrUpdate(kt);

            tx.commit();

        } catch (HibernateException e) {
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Änderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (Anh49Kontrollen)", e);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public static boolean removeOrtstermin(Anh49Kontrollen kt) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(kt);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "Anh49Kontrollen.objectRemoved", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }

	public static void saveOrUpdateAnalyse(Anh49Kontrollen kontrolle) {
        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.saveOrUpdate(kontrolle);

            tx.commit();

        } catch (HibernateException e) {
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Änderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (Anh49Kontrollen)", e);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }
		
	}
}
