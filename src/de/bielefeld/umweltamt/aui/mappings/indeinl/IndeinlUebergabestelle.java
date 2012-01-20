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
 * Created Tue Jun 28 08:35:52 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'SUEV_FACHDATEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class IndeinlUebergabestelle
    extends AbstractIndeinlUebergabestelle
    implements Serializable
{
    /** Database manager */
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();

    /**
     * Simple constructor of SuevFachdaten instances.
     */
    public IndeinlUebergabestelle()
    {
    }

    /**
     * Constructor of SuevFachdaten instances given a simple primary key.
     * @param objektid
     */
    public IndeinlUebergabestelle(java.lang.Integer objektid)
    {
        super(objektid);
    }
    /**
     * Liefert einen String der Form "[Uebergabestelle:ID]"
     */
    public String toString() {
        return "[Uebergabestelle:" + getObjektid() + "]";
    }

    private static IndeinlUebergabestelle getUebergabeByObjekt(BasisObjekt objekt,
            Session session) throws HibernateException {
        IndeinlUebergabestelle fachdaten = null;
        if (objekt.getBasisObjektarten().isUebergabestelle()) {
            List stelle = session.createQuery(
                    "from IndeinlUebergabestelle as stelle where "
                            + "stelle.basisObjekt = ?").setEntity(0, objekt)
                    .list();

            if (stelle.size() > 0) {
                fachdaten = (IndeinlUebergabestelle) stelle.get(0);
            }
        }

        return fachdaten;
    }

    public static IndeinlUebergabestelle getUebergabeByObjekt(BasisObjekt objekt) {
        IndeinlUebergabestelle fachdaten;
        try {
            Session session = HibernateSessionFactory.currentSession();
            fachdaten = getUebergabeByObjekt(objekt, session);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            fachdaten = null;
        }

        return fachdaten;
    }

    /**
     * Speichert ein SUEV-KAN Fachdaten-Objekt
     * in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst <code>false</code>.
     */
    public static boolean saveFachdaten(IndeinlUebergabestelle fachdaten) {
        boolean saved;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(fachdaten);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    dbManager.handleDBException(e1, "SuevFachdaten.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Liefert eine Liste mit allen SUEV-KAN Objekten.
     * @return Eine Liste aus SuevFachdaten.
     */
    public static List getAuswertungsListe() {
        List liste;

        String query = "from IndeinlUebergabestelle as stelle " +
                "order by stelle.objektid";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }
}
