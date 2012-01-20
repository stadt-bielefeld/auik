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
 * Created Thu May 19 07:47:28 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.utils.AuikLogger;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;

/**
 * A class that represents a row in the 'ANH_49_ORTSTERMINE' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Anh49Ortstermine
    extends AbstractAnh49Ortstermine
    implements Serializable
{
	/** Logging */
    private static final AuikLogger log = AuikLogger.getLogger();

    /**
     * Simple constructor of Anh49Ortstermine instances.
     */
    public Anh49Ortstermine()
    {
    }

    /**
     * Constructor of Anh49Ortstermine instances given a simple primary key.
     * @param ortsterminid
     */
    public Anh49Ortstermine(java.lang.Integer ortsterminid)
    {
        super(ortsterminid);
    }

    /* Add customized code below */

    /**
     * Liefert einen String der Form "[Datum: DATUM und SACHBEARBEITER]".
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[Datum: "+ AuikUtils.getStringFromDate(getDatum()) +", SachbearbeiterIn: "+ getSachbearbeiterIn() +"]";
    }

    /**
     * Liefert alle Ortstermine eines bestimmten Fachdatenobjekts.
     */
    public static List getOrtstermine(Anh49Fachdaten fd) {
        List ot;
        try {
            Session session = HibernateSessionFactory.currentSession();
            ot = session.createQuery(
                    "from Anh49Ortstermine as ot where " +
                    "ot.anh49Fachdaten = ? " +
                    "order by ot.datum")
                    .setEntity(0, fd)
                    .list();

            log.debug("Ortstermine für " + fd + ", Anzahl: " + ot.size());
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return ot;
    }

    public static void saveOrUpdateOrtstermin(Anh49Ortstermine ot) {
        Transaction tx = null;

        try {
            Session session = HibernateSessionFactory.currentSession();

            tx = session.beginTransaction();

            session.saveOrUpdate(ot);

            tx.commit();

        } catch (HibernateException e) {
            // Falls während der Änderungen ein Hibernate Fehler auftritt
            if (tx != null) {
                try {
                    // Alle Änderungen rückgängig machen
                    tx.rollback();
                } catch (HibernateException e1) {
                    throw new RuntimeException("Datenbank-Fehler (Anh49Ortstermine)", e);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public static boolean removeOrtstermin(Anh49Ortstermine ot) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(ot);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "Anh49Ortstermin.objectRemoved", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }
}
