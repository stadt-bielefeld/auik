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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_56_FACHDATEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Anh53Fachdaten
    extends AbstractAnh53Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh52Fachdaten instances.
     */
    public Anh53Fachdaten()
    {
    }

    /**
     * Constructor of Anh53Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh53Fachdaten(java.lang.Integer objektid)
    {
        super(objektid);
    }

    public String toString() {
        return "[ID:" + this.getObjektid() + "]";
    }

    public static Anh53Fachdaten getAnh53ByObjekt(BasisObjekt objekt, Session session) {
        Anh53Fachdaten fachdaten = null;
        if (objekt.getBasisObjektarten().isAnh53Gr() || objekt.getBasisObjektarten().isAnh53Kl()) {
            try {
                List anhang53 = session.createQuery(
                        "from Anh53Fachdaten as anhang53 where " +
                        "anhang53.basisObjekt = ?")
                        .setEntity(0, objekt)
                        .list();

                if (anhang53.size() > 0) {
                    fachdaten = (Anh53Fachdaten) anhang53.get(0);
                }
            } catch (HibernateException e) {
            }
        }

        return fachdaten;
    }

    public static Anh53Fachdaten getAnh53ByObjekt(BasisObjekt objekt) {
        Anh53Fachdaten fachdaten;
        try {
            Session session = HibernateSessionFactory.currentSession();
            fachdaten = getAnh53ByObjekt(objekt, session);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            fachdaten = null;
        }

        return fachdaten;
    }

    public static boolean saveFachdaten(Anh53Fachdaten fachdaten) {
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
                    AUIKataster.handleDBException(e1, "Anh53Fachdaten.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Liefert eine Liste mit allen Anhang53 Objekten.
     * @return Eine Liste aus Anh53Fachdaten.
     */
    public static List getAuswertungsListe() {
        List liste;


        String query = "from Anh53Fachdaten as anh53 "
                + "order by anh53.basisObjekt.inaktiv, anh53.basisObjekt.basisObjektarten.objektartid, "
                + "anh53.basisObjekt.basisStandort.strasse, "
                + "anh53.basisObjekt.basisStandort.hausnr";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query).list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }
}
