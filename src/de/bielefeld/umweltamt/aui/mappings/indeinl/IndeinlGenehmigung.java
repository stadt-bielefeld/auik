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
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'IndeinlGenehmigung' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class IndeinlGenehmigung
    extends AbstractIndeinlGenehmigung
    implements Serializable
{
    /**
     * Simple constructor of IndeinlGenehmigung instances.
     */
    public IndeinlGenehmigung()
    {
    }

    /**
     * Constructor of IndeinlGenehmigung instances given a simple primary key.
     * @param objektid
     */
    public IndeinlGenehmigung(java.lang.Integer objektid)
    {
        super(objektid);
    }
    /**
     * Liefert einen String der Form "[Genehmigung Verfahren:ID]"
     */
    public String toString() {
        return "[Genehmigung Verfahren:" + getObjektid() + "]";
    }

    private static IndeinlGenehmigung getGenByObjekt(BasisObjekt objekt,
            Session session) throws HibernateException {
        IndeinlGenehmigung fachdaten = null;
        if (objekt.getBasisObjektarten().isGenehmigung()) {
            List gen = session.createQuery(
                    "from IndeinlGenehmigung as gen where "
                            + "gen.basisObjekt = ?").setEntity(0, objekt)
                    .list();

            if (gen.size() > 0) {
                fachdaten = (IndeinlGenehmigung) gen.get(0);
            }
        }

        return fachdaten;
    }

    public static IndeinlGenehmigung getGenByObjekt(BasisObjekt objekt) {
        IndeinlGenehmigung fachdaten;
        try {
            Session session = HibernateSessionFactory.currentSession();
            fachdaten = getGenByObjekt(objekt, session);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            fachdaten = null;
        }

        return fachdaten;
    }

    /**
     * Speichert ein IndeinlGenehmigung Fachdaten-Objekt
     * in der Datenbank.
     * @param fachdaten Das zu speichernde Fachdaten-Objekt.
     * @return <code>true</code>, wenn das Objekt gespeichert wurde, sonst <code>false</code>.
     */
    public static boolean saveFachdaten(IndeinlGenehmigung fachdaten) {
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
                    AUIKataster.handleDBException(e1, "GenFachdaten.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Liefert eine Liste mit allen IndeinlGenehmigung Objekten.
     * @return Eine Liste aus IndeinlGenehmigungen.
     */
    public static List getAuswertungsListe(Boolean gen58, Boolean gen59) {
        List liste;

        String query = "from IndeinlGenehmigung as gen " +
                   "where gen.gen58 = ? or gen.gen59 = ? " +
                "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisBetreiber.betrname";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .setBoolean(0, gen58)
            .setBoolean(1, gen59)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 40 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List getAnh40Liste(Boolean gen58, Boolean gen59) {
        List liste;

        String query = "from IndeinlGenehmigung as gen " +
                       "where gen.anhang = 40 " +
                       "and gen.gen58 = ? or gen.anhang = 40 and gen.gen59 = ? " +
                       "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisBetreiber.betrname";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .setBoolean(0, gen58)
            .setBoolean(1, gen59)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 49 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List getAnh49Liste(Boolean gen58, Boolean gen59) {
        List liste;

        String query = "from IndeinlGenehmigung as gen " +
                           "where gen.anhang = 49 " +
                           "and gen.gen58 = ? or gen.anhang = 49 and gen.gen59 = ? " +
                           "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisBetreiber.betrname";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .setBoolean(0, gen58)
            .setBoolean(1, gen59)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 50 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List getAnh50Liste(Boolean gen58, Boolean gen59) {
        List liste;

        String query = "from IndeinlGenehmigung as gen " +
                           "where gen.anhang = 50 " +
                           "and gen.gen58 = ? or gen.anhang = 50 and gen.gen59 = ? " +
                           "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisBetreiber.betrname";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .setBoolean(0, gen58)
            .setBoolean(1, gen59)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 53 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List getAnh53Liste(Boolean gen58, Boolean gen59) {
        List liste;

        String query = "from IndeinlGenehmigung as gen " +
                           "where gen.anhang = 53 " +
                           "and gen.gen58 = ? or gen.anhang = 53 and gen.gen59 = ? " +
                           "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisBetreiber.betrname";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .setBoolean(0, gen58)
            .setBoolean(1, gen59)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    /**
     * Liefert eine Liste mit allen Anhang 40 Genehmigungsobjekten.
     * @return Eine Liste aus Genehmigungsfachdaten.
     */
    public static List getBwkListe(Boolean gen58, Boolean gen59) {
        List liste;

        String query = "from IndeinlGenehmigung as gen " +
                       "where gen.anhang Is Null " +
                       "and gen.gen58 = ? or gen.anhang Is Null and gen.gen59 = ? " +
                       "order by gen.basisObjekt.inaktiv, gen.basisObjekt.basisStandort.strasse, gen.basisObjekt.basisStandort.hausnr";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
            .setBoolean(0, gen58)
            .setBoolean(1, gen59)
            .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }
}
