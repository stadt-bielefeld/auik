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
 * Created Thu Apr 21 11:48:03 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.indeinl.AbstractAnhBwkFachdaten;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_BWK' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AnhBwkFachdaten
    extends AbstractAnhBwkFachdaten
    implements Serializable
{
    /** Database manager */
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();

    /**
     * Simple constructor of AnhBwk instances.
     */
    public AnhBwkFachdaten()
    {
    }

    /**
     * Constructor of AnhBwk instances given a simple primary key.
     * @param bwkId
     */
    public AnhBwkFachdaten(java.lang.Integer bwkId)
    {
        super(bwkId);
    }

    /**
     * Liefert einen String der Form "[BWK:ID,Hersteller Typ]"
     */
    public String toString() {
        return "[BWK:" + getBwkId() + "," + getKHersteller() + " " + getKTyp() + "]";
    }

    private static AnhBwkFachdaten getAnhBwkByObjekt(BasisObjekt objekt, Session session) throws HibernateException {
        AnhBwkFachdaten bwk = null;
        if (objekt.getBasisObjektarten().isBWK()) {
            List brennwert = session.createQuery(
                    "from AnhBwkFachdaten as brennwert where " +
                    "brennwert.basisObjekt = ?")
                    .setEntity(0, objekt)
                    .list();

            if (brennwert.size() > 0) {
                bwk = (AnhBwkFachdaten) brennwert.get(0);
            }
        }

        return bwk;
    }

    public static AnhBwkFachdaten getAnhBwkByObjekt(BasisObjekt objekt) {
        AnhBwkFachdaten bwk;
        try {
            Session session = HibernateSessionFactory.currentSession();
            bwk = getAnhBwkByObjekt(objekt, session);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            bwk = null;
            throw new RuntimeException("Datenbank-Fehler", e);
        }

        return bwk;
    }

    /**
     * Speichert ein BWK Fachdaten-Objekt in
     * der Datenbank.
     *
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst <code>false</code>.
     */
    public static boolean saveBwk(AnhBwkFachdaten bwk) {
        boolean saved;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(bwk);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    dbManager.handleDBException(e1, "AnhBwk.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Erzeugt eine Liste mit allen Brennwerkesseln eines
     * bestimmten Erfassungsjahrs.
     * @param jahr Das Erfassungsjahr (oder -1 wenn alle
     * Kessel ausgegeben werden sollen).
     * @return Eine Liste aus AnhBwk-Objekten.
     */
    public static List findByErfassungsjahr(int jahr) {
        List liste;

        //Object[] objects;
        //Type[] types;

        String query = "from AnhBwkFachdaten as bwk ";

        if (jahr != -1) {
            if (jahr > 0 && jahr < 100) {
                if (jahr <= 30) {
                    jahr = jahr + 2000;
                } else {
                    jahr = jahr + 1900;
                }
            }
            query += "where bwk.erfassung = ? ";
        }

        query += "order by bwk.basisObjekt.inaktiv, bwk.erfassung, " +
                "bwk.basisObjekt.basisBetreiber.betrname, " +
                "bwk.basisObjekt.basisBetreiber.betrnamezus, " +
                "bwk.basisObjekt.basisStandort.strasse, " +
                "bwk.basisObjekt.basisStandort.hausnr";

        try {
            Session session = HibernateSessionFactory.currentSession();
            if (jahr != -1) {
                liste = session.createQuery(query)
                .setInteger(0, jahr)
                .list();

            } else {
                liste = session.createQuery(query)
                .list();
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }
}
