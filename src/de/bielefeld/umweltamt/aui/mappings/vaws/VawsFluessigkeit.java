/*
 * Created Tue Sep 06 14:46:23 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_FLUESSIGKEIT' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsFluessigkeit
    extends AbstractVawsFluessigkeit
    implements Serializable
{
    /**
     * Simple constructor of VawsFluessigkeit instances.
     */
    public VawsFluessigkeit()
    {
    }

    /* Add customized code below */
    
    public String toString() {
		return super.getFluessigkeit();
	}

    /**
     * Liefer alle VAWS-Flüssigkeiten.
     * @return Ein Array mit allen Flüssigkeiten.
     */
    public static String[] getFluessigkeiten() {
		List list;
		String suchString = "select fl.fluessigkeit " +
				"from VawsFluessigkeit fl " +
				"order by fl.fluessigkeit";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsflliste");
			list = query.list();
			tmp = new String[list.size()];
			tmp = (String[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (VawsStandortgghwsg)", e);
		} finally {
			//HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
