/*
 * Created Mon Feb 07 10:40:29 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_WIRTSCHAFTSZWEIGE' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsWirtschaftszweige
    extends AbstractVawsWirtschaftszweige
    implements Serializable
{
    /**
     * Simple constructor of VawsWirtschaftszweige instances.
     */
    public VawsWirtschaftszweige()
    {
    }

    /**
     * Constructor of VawsWirtschaftszweige instances given a simple primary key.
     * @param wirtschaftszweigid
     */
    public VawsWirtschaftszweige(java.lang.Integer wirtschaftszweigid)
    {
        super(wirtschaftszweigid);
    }

    /* Add customized code below */

	/**
	 * Liefert den Namen des Wirtschaftszweiges.
	 */
    public String toString() {
		return super.getWirtschaftszweig();
	}
    
    /**
	 * Liefert alle vorhandenen VAWS-Wirtschaftszweige.
	 * @param session Eine Hibernate-Session
	 * @return Alle vorhandenen Wirtschaftszweige
	 * @throws HibernateException Falls ein Datenbank-Fehler auftritt
	 */
	public static VawsWirtschaftszweige[] getWirtschaftszweige(Session session) throws HibernateException {
		List list = null;
		String suchString = "FROM VawsWirtschaftszweige wizw ORDER BY wizw.wirtschaftszweigid";
		VawsWirtschaftszweige[] tmp;
		Query query = session.createQuery(suchString);
		query.setCacheable(true);
		query.setCacheRegion("wizwliste");
		list = query.list();
		tmp = new VawsWirtschaftszweige[list.size()];
		tmp = (VawsWirtschaftszweige[]) list.toArray(tmp);
		return tmp;
	}
	
	public static VawsWirtschaftszweige[] getWirtschaftszweige() throws HibernateException {
		Session session = HibernateSessionFactory.currentSession();
		VawsWirtschaftszweige[] tmp = getWirtschaftszweige(session);
		HibernateSessionFactory.closeSession();
		return tmp;
	}
}
