/*
 * Created Tue Sep 06 14:47:25 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;
import de.bielefeld.umweltamt.aui.mappings.indeinl.IndeinlUebergabestelle;

/**
 * A class that represents a row in the 'VAWS_BasisObjektverknuepfung' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class BasisObjektverknuepfung
    extends AbstractBasisObjektverknuepfung
    implements Serializable
{
    /**
     * Simple constructor of VawsObjektchrono instances.
     */
    public BasisObjektverknuepfung()
    {
    }

    /**
     * Constructor of VawsObjektchrono instances given a simple primary key.
     * @param id
     */
    public BasisObjektverknuepfung(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */


    // Statischer Teil:


    /**
     * Liefert alle verknuepften Objekte zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit Objekten.
     */
    public static List getVerknuepfungByObjekt(BasisObjekt objekt) {
        List verknuepf;

        try {
            Session session = HibernateSessionFactory.currentSession();
            verknuepf = session.createQuery(
                    "from BasisObjektverknuepfung ov where " +
                    "ov.basisObjektByObjekt = ? " +
                    "or ov.basisObjektByIstVerknuepftMit = ? ")
                    .setEntity(0, objekt)
                    .setEntity(1, objekt)
                    .list();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return verknuepf;
    }

    /**
     * Liefert alle verknuepften Sielhautmessstellen zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit Objekten.
     */
    public static List getVerknuepfungSielhaut(BasisObjekt objekt) {
        List verknuepf;

        try {
            Session session = HibernateSessionFactory.currentSession();
            verknuepf = session.createQuery(
                    "from BasisObjektverknuepfung ov where " +
                    "ov.basisObjektByObjekt = ? " +
                    "and ov.basisObjektByIstVerknuepftMit.basisObjektarten.objektart like 'Sielhautmessstelle' ")
                    .setEntity(0, objekt)
                    .list();

        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return verknuepf;
    }

    /**
     * Speichert einen Objektverknuepfungs-Eintrag in der Datenbank.
     * @param verknuepf Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
     */
    public static boolean saveObjektVerknuepfung(BasisObjektverknuepfung verknuepf) {
        boolean saved;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(verknuepf);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "BasisObjektverknuepfung.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param verknuepf Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     * <code>false</code> falls dabei ein Fehler auftrat (z.B. der Datensatz
     * nicht in der Datenbank existiert).
     */
    public static boolean removeObjektVerknuepfung(BasisObjektverknuepfung verknuepf) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(verknuepf);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "BasisObjektverknuepfung.remove", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }

    /**
     * Liefert einen String mit der ID.
     */
    public String toString() {
        String tmp = getId() + ": ";
        return tmp;
    }

    public static List getObjekte() {



        return null;
    }
}
