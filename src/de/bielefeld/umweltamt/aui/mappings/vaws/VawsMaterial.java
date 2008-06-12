/*
 * Created Tue Sep 06 14:47:14 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_MATERIAL' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsMaterial
    extends AbstractVawsMaterial
    implements Serializable
{
    /**
     * Simple constructor of VawsMaterial instances.
     */
    public VawsMaterial()
    {
    }

    /* Add customized code below */

    public String toString() {
    	return super.getMaterial();
    }
    
    /**
     * Liefert alle VAWS-Materialien.
     * @return Ein Array mit den Namen aller Materialien.
     */
    public static String[] getMaterialien() {
		List list;
		String suchString = "select mat.material " +
				"from VawsMaterial mat " +
				"order by mat.material";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsmatliste");
			list = query.list();
			tmp = new String[list.size()];
			tmp = (String[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (VawsMaterial)", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
