/*
 * Created Tue Sep 06 14:45:45 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_ANLAGENCHRONO' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class VawsAnlagenchrono
    extends AbstractVawsAnlagenchrono
    implements Serializable
{
    /**
     * Simple constructor of VawsAnlagenchrono instances.
     */
    public VawsAnlagenchrono()
    {
    }

    /**
     * Constructor of VawsAnlagenchrono instances given a simple primary key.
     * @param id
     */
    public VawsAnlagenchrono(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    /**
     * Liefert alle Anlagen-Chronologie-Einträge zu einem bestimmten
     * VawsFachdaten-satz.
     * @param fachdaten Der Fachdatensatz.
     * @return Eine Liste mit VawsAnlagenchrono-Objekten.
     */
    public static List getAnlagenChrono(VawsFachdaten fachdaten) {
        List chrono;

        if (fachdaten.getBehaelterId() == null)
        {
            chrono = new ArrayList();
        } else {
            try {
                Session session = HibernateSessionFactory.currentSession();

                chrono = session.createQuery(
                        "from VawsAnlagenchrono as vac where " +
                        "vac.vawsFachdaten = ? " +
                        "order by vac.datum, vac.wv")
                        .setEntity(0, fachdaten)
                        .list();

                AUIKataster.debugOutput(chrono.size() + " AChrono-Einträge für FD " + fachdaten + " gefunden!", "VawsAnlagenchrono");
            } catch (HibernateException e) {
                throw new RuntimeException("Datenbank-Fehler", e);
            } finally {
                HibernateSessionFactory.closeSession();
            }
        }

        return chrono;
    }

    /**
     * Speichert einen VAWS-Anlagenchronologie-Eintrag in der Datenbank.
     * @param chrono Der zu speichernde Datensatz.
     * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
     */
    public static boolean saveAnlagenChrono(VawsAnlagenchrono chrono) {
        boolean saved;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.saveOrUpdate(chrono);
            tx.commit();
            saved = true;
        } catch (HibernateException e) {
            saved = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "VawsAnlagenchrono.save", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return saved;
    }

    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param chrono Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder
     * <code>false</code> falls dabei ein Fehler auftrat (z.B. der Datensatz
     * nicht in der Datenbank existiert).
     */
    public static boolean removeAnlagenChrono(VawsAnlagenchrono chrono) {
        boolean removed;

        Transaction tx = null;
        try {
            Session session = HibernateSessionFactory.currentSession();
            tx = session.beginTransaction();
            session.delete(chrono);
            tx.commit();
            removed = true;
        } catch (HibernateException e) {
            removed = false;
            e.printStackTrace();
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (HibernateException e1) {
                    AUIKataster.handleDBException(e1, "VawsAnlagenchrono.remove", false);
                }
            }
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return removed;
    }
}
