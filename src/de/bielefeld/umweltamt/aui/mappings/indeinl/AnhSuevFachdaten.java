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
import org.hibernate.type.Type;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'SUEV_FACHDATEN' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AnhSuevFachdaten
    extends AbstractAnhSuevFachdaten
    implements Serializable
{
    /**
     * Simple constructor of SuevFachdaten instances.
     */
    public AnhSuevFachdaten()
    {
    }

    /**
     * Constructor of SuevFachdaten instances given a simple primary key.
     * @param suevid
     */
    public AnhSuevFachdaten(java.lang.Integer suevid)
    {
        super(suevid);
    }
    /**
     * Liefert einen String der Form "[SuevKan Verfahren:ID]"
     */
    public String toString() {
        return "[SuevKan Verfahren:" + getObjektid() + "]";
    }

    private static AnhSuevFachdaten getSuevByObjekt(BasisObjekt objekt,
            Session session) throws HibernateException {
        AnhSuevFachdaten fachdaten = null;
        if (objekt.getBasisObjektarten().isSuev()) {
            List suev = session.createQuery(
                    "from AnhSuevFachdaten as suev where "
                            + "suev.basisObjekt = ?").setEntity(0, objekt)
                    .list();

            if (suev.size() > 0) {
                fachdaten = (AnhSuevFachdaten) suev.get(0);
            }
        }

        return fachdaten;
    }

    public static AnhSuevFachdaten getSuevByObjekt(BasisObjekt objekt) {
        AnhSuevFachdaten fachdaten;
        try {
            Session session = HibernateSessionFactory.currentSession();
            fachdaten = getSuevByObjekt(objekt, session);
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
    public static boolean saveFachdaten(AnhSuevFachdaten fachdaten) {
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
                    AUIKataster.handleDBException(e1, "SuevFachdaten.save", false);
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

        String query = "from AnhSuevFachdaten as sv " +
                "order by sv.basisObjekt.inaktiv, sv.objektid";

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
