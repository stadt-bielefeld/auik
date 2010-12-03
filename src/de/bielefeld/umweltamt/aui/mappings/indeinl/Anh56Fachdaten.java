/*
 * Created Wed Aug 10 13:41:29 CEST 2005 by MyEclipse Hibernate Tool.
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
 * A class that represents a row in the 'ANH_56_FACHDATEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class Anh56Fachdaten
    extends AbstractAnh56Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh56Fachdaten instances.
     */
    public Anh56Fachdaten()
    {
    }

    /**
     * Constructor of Anh56Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh56Fachdaten(java.lang.Integer id)
    {
        super(id);
    }

    public String toString() {
        return "[ID:" + this.getObjektid() + "]";
    }

    /**
     * Liefert eine Liste mit allen Anhang56 Objekten.
     * @return Eine Liste aus Anh56Fachdaten.
     */
    public static List getAuswertungsListe() {
        List liste;

        String query = "from Anh56Fachdaten as anh56 " +
                "order by anh56.basisObjekt.inaktiv, anh56.basisObjekt.basisStandort.strasse, " +
                "anh56.basisObjekt.basisStandort.hausnr";

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

    /**
     * Liefert eine Liste mit allen abwasserrelevanten Objekten.
     * @return Eine Liste aus Anh56Fachdaten.
     */
    public static List getAbwasserListe() {
        List liste;
        Boolean abwasser = true;

        String query = "from Anh56Fachdaten as anh56 " +
                "where anh56.abwasseranfall = ? " +
                "order by anh56.basisObjekt.inaktiv, anh56.basisObjekt.basisStandort.strasse, " +
                "anh56.basisObjekt.basisStandort.hausnr";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
                .setString(0, abwasser.booleanValue() ? "t" : "f")
                .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    /**
     * Liefert eine Liste mit allen genehmigungspflichtigen Objekten.
     * @return Eine Liste aus Anh56Fachdaten.
     */
    public static List getGenehmigungListe() {
        List liste;
        Boolean genpflicht = true;

        String query = "from Anh56Fachdaten as anh56 " +
                "where anh56.genpflicht = ? " +
                "order by anh56.basisObjekt.inaktiv, anh56.basisObjekt.basisStandort.strasse, " +
                "anh56.basisObjekt.basisStandort.hausnr";

        try {
            Session session = HibernateSessionFactory.currentSession();
            liste = session.createQuery(query)
                .setString(0, genpflicht.booleanValue() ? "t" : "f")
                .list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return liste;
    }

    public static Anh56Fachdaten getAnh56ByObjekt(BasisObjekt objekt, Session session) {
        Anh56Fachdaten fachdaten = null;
        Integer objID = objekt.getObjektid();
        if (objekt.getBasisObjektarten().isAnh56()) {
            try {
                List anhang56 = session.createQuery(
                        "from Anh56Fachdaten as anhang56 where " +
                        "anhang56 = ?")
                        .setEntity(0, objekt)
                        .list();

                if (anhang56.size() > 0) {
                    fachdaten = (Anh56Fachdaten) anhang56.get(0);
                }
            } catch (HibernateException e) {
            }
        }

        return fachdaten;
    }

    public static Anh56Fachdaten getAnh56ByObjekt(BasisObjekt objekt) {
        Anh56Fachdaten fachdaten;
        try {
            Session session = HibernateSessionFactory.currentSession();
            fachdaten = getAnh56ByObjekt(objekt, session);
            HibernateSessionFactory.closeSession();
        } catch (HibernateException e) {
            fachdaten = null;
        }

        return fachdaten;
    }

    public static boolean saveFachdaten(Anh56Fachdaten fachdaten) {
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
                    AUIKataster.handleDBException(e1, "Anh56Fachdaten.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

}
