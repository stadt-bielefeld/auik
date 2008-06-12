/*
 * Created Tue Sep 06 14:48:48 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_WGK' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsWgk
    extends AbstractVawsWgk
    implements Serializable
{
    /**
     * Simple constructor of VawsWgk instances.
     */
    public VawsWgk()
    {
    }

    /* Add customized code below */

    public String toString() {
    	return super.getWassergef().toString();
    }
    
    /**
     * Liefer alle VAWS-WGK.
     * @return Ein Array mit den Namen aller WGK.
     */
    public static Integer[] getWgk() {
		List list;
		String suchString = "select wgk.wassergef " +
				"from VawsWgk wgk " +
				"order by wgk.wassergef";
		Integer[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawswgkliste");
			list = query.list();
			tmp = new Integer[list.size()];
			tmp = (Integer[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (VawsWgk)", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
