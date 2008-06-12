/*
 * Created Tue Sep 06 14:46:49 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_GEFAEHRDUNGSSTUFEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsGefaehrdungsstufen
    extends AbstractVawsGefaehrdungsstufen
    implements Serializable
{
    /**
     * Simple constructor of VawsGefaehrdungsstufen instances.
     */
    public VawsGefaehrdungsstufen()
    {
    }

    /* Add customized code below */

    public String toString() {
    	return super.getGefaehrdungsstufen();
    }
    
    /**
     * Liefer alle VAWS-Gefährdungsstufen.
     * @return Ein Array mit den Namen aller Gefährdungsstufen.
     */
    public static String[] getVbfeinstufungen() {
		List list;
		String suchString = "select gef.gefaehrdungsstufen " +
				"from VawsGefaehrdungsstufen gef " +
				"order by gef.gefaehrdungsstufen";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsgefliste");
			list = query.list();
			tmp = new String[list.size()];
			tmp = (String[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (VawsGefaehrdungsstufen)", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
