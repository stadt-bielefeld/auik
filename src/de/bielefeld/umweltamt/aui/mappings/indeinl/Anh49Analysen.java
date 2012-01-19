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

/*
 * Created Thu May 19 07:47:27 CEST 2005 by MyEclipse Hibernate Tool.
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

/**
 * A class that represents a row in the 'ANH_49_ANALYSEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Anh49Analysen
    extends AbstractAnh49Analysen
    implements Serializable
{
	/** Logging */
    private static final Logger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Analysen instances.
     */
    public Anh49Analysen()
    {
    }

    /**
     * Constructor of Anh49Analysen instances given a simple primary key.
     * @param analysenid
     */
    public Anh49Analysen(java.lang.Integer analysenid)
    {
        super(analysenid);
    }

    /* Add customized code below */
    /**
     * Liefert einen String der Form "[Datum: DATUM und ANALYSENID]".
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[Datum:"+ getDatum() +", "+ getAnalysenid() +"]";
    }

    /**
     * Liefert alle Analysen eines bestimmten Fachdatenobjekts.
     */
    public static List getAnalyse(Anh49Fachdaten fd) {
        List analyse;

        try {
            Session session = HibernateSessionFactory.currentSession();
            analyse = session.createQuery(
                    "from Anh49Analysen as analyse where " +
                    "analyse.anh49Fachdaten = ? " +
                    "order by analyse.datum")
                    .setEntity(0, fd)
                    .list();

            log.debug("Analysen für " + fd + ", Anzahl: " + analyse.size());
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return analyse;
    }

    public static void saveOrUpdateAnalyse(Anh49Analysen analyse) {
        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.saveOrUpdate(analyse);

            tx.commit();

        } catch (HibernateException e) {
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

    public static boolean removeAnalyse(Anh49Analysen analyse) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(analyse);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "Anh49AnalysenModel.objectRemoved", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }
}
