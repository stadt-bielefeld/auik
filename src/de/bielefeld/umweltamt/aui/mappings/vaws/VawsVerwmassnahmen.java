/*
 * Created Tue Sep 06 14:48:38 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_VERWMASSNAHMEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsVerwmassnahmen
    extends AbstractVawsVerwmassnahmen
    implements Serializable
{
    /**
     * Simple constructor of VawsVerwmassnahmen instances.
     */
    public VawsVerwmassnahmen()
    {
    }

    /* Add customized code below */

    /**
     * Liefert alle Massnahmen für die Verwaltungsverfahren.
     * @return Ein Array mit den Namen aller möglichen Massnahmen.
     */
    public static String[] getAllMassnahmen() {
		List list;
		String suchString = "select vwm.massnahmen " +
				"from VawsVerwmassnahmen vwm " +
				"order by vwm.massnahmen";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsvwmliste");
			list = query.list();
			tmp = new String[list.size()];
			tmp = (String[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
