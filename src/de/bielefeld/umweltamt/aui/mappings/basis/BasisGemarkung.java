/*
 * Created Thu Jan 20 17:02:28 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A class that represents a row in the 'BASIS_GEMARKUNG' table.
 * This class may be customized as it is never re-generated
 * after being created.
 */
public class BasisGemarkung
    extends AbstractBasisGemarkung
    implements Serializable
{
    /**
     * Simple constructor of BasisGemarkung instances.
     */
    public BasisGemarkung()
    {
    }

    /**
     * Constructor of BasisGemarkung instances given a simple primary key.
     * @param gemarkungid
     */
    public BasisGemarkung(java.lang.Integer gemarkungid)
    {
        super(gemarkungid);
    }

    /* Add customized code below */

    /**
     * Liefert den Gemarkungsnamen.
     */
    public String toString() {
        return super.getGemarkung();
    }

    /**
     * Liefert alle vorhandenen Gemarkungen.
     * @param session Eine Hibernate-Session
     * @return Alle vorhandenen Gemarkungen
     * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
     */
    public static BasisGemarkung[] getGemarkungen(Session session) throws HibernateException {
        List list = null;
        String suchString = "FROM BasisGemarkung bg ORDER BY bg.gemarkungid";
        BasisGemarkung[] tmp = null;
        Query query = session.createQuery(suchString);
        query.setCacheable(true);
        query.setCacheRegion("gemarkungsliste");
        list = query.list();
        tmp = new BasisGemarkung[list.size()];
        tmp = (BasisGemarkung[]) list.toArray(tmp);
        return tmp;
    }

    /**
     * Liefert alle vorhandenen Gemarkungen.
     * @return Alle vorhandenen Gemarkungen
     * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
     */
    public static BasisGemarkung[] getGemarkungen() throws HibernateException {
        Session session = HibernateSessionFactory.currentSession();
        BasisGemarkung[] tmp = getGemarkungen(session);
        HibernateSessionFactory.closeSession();
        return tmp;
    }
}
