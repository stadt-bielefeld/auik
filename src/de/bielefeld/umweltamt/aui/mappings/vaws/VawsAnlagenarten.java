/*
 * Created Tue Sep 06 14:45:23 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_ANLAGENARTEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsAnlagenarten
    extends AbstractVawsAnlagenarten
    implements Serializable
{
    /**
     * Simple constructor of VawsAnlagenarten instances.
     */
    public VawsAnlagenarten()
    {
    }

    /* Add customized code below */
    
    public String toString() {
    	return getAnlagenart();
    }

    /**
     * Liefer alle VAWS-Anlagenarten.
     * @return Ein Array mit den Namen aller Anlagenarten.
     */
    public static String[] getAnlagenarten() {
		List list;
		String suchString = "select ala.anlagenart " +
				"from VawsAnlagenarten ala " +
				"order by ala.id";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsalaliste");
			list = query.list();
			tmp = new String[list.size()];
			tmp = (String[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			//HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
