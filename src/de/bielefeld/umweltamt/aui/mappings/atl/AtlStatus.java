/*
 * Created Wed Feb 16 15:12:00 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * A class that represents a row in the 'ATL_STATUS' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class AtlStatus
    extends AbstractAtlEinheiten
    implements Serializable
{


    /**
     * Simple constructor of AtlStatus instances.
     */
    public AtlStatus()
    {
    }

    /**
     * Constructor of AtlStatus instances given a simple primary key.
     * @param id
     */
    public AtlStatus(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    /**
     * @return Der Name der Einheit.
     */
    public String toString() {
        String tmp = getBezeichnung();
        if (tmp != null) {
            tmp = tmp.trim();
        }
        return tmp;
    }


    /**
     * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
     * @return Ein Array mit allen Einheiten
     */
    public static AtlStatus[] getStatus() {
        AtlStatus[] tmp;
        try {
            Session session = HibernateSessionFactory.currentSession();
            List einheiten = session.createQuery("from AtlStatus as status").list();

            tmp = new AtlStatus[einheiten.size()];
            tmp = (AtlStatus[]) einheiten.toArray(tmp);
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return tmp;
    }

    /**
     * Liefert das AtlStatus Objekt mit der passenden Bezeichnung.
     *
     * @return das Objekt passend zu <i>bezeichnung</i>, oder <i>null</i> falls
     * kein Objekt mit dieser Bezeichnung existiert.
     */
    public static AtlStatus getStatus(String bezeichnung) {
        AtlStatus[] status = getStatus();

        for (AtlStatus s: status) {
            if (bezeichnung.equals(s.getBezeichnung())) {
                return s;
            }
        }

        return null;
    }


    public static AtlStatus getStatus(int id) {
        AtlStatus[] status = getStatus();

        for (AtlStatus s: status) {
            if (id == s.getId()) {
                return s;
            }
        }

        return null;
    }


    public static String[] getStatusAsString() {
        AtlStatus[] status = getStatus();
        String[]    str    = new String[status.length];

        int idx = 0;
        for (AtlStatus s: status) {
            str[idx++] = s.getBezeichnung();
        }

        return str;
    }

    /**
     * Liefert einen bestimmten Status.
     * @param id Die ID des Statuses
     * @return Der Status mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
     */
    public static AtlStatus getEinheit(Integer id) {
        AtlStatus status;

        if (id != null) {
            try {
                Session session = HibernateSessionFactory.currentSession();
                status = (AtlStatus) session.get(AtlStatus.class, id);
            } catch (HibernateException e) {
                e.printStackTrace();
                status = null;
            } finally {
                HibernateSessionFactory.closeSession();
            }
        } else {
            status = null;
        }

        return status;
    }
}
