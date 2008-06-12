/*
 * Created Tue Sep 06 14:47:39 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_PRUEFER' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsPruefer
    extends AbstractVawsPruefer
    implements Serializable
{
    /**
     * Simple constructor of VawsPruefer instances.
     */
    public VawsPruefer()
    {
    }

    /* Add customized code below */

    /**
     * Liefert alle Prüfer für die Sachverständigenprüfung.
     * @return Ein Array mit den Namen aller Prüfer.
     */
    public static String[] getAllPruefer() {
		List list;
		String suchString = "select prf.pruefer " +
				"from VawsPruefer prf " +
				"order by prf.pruefer";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsprfliste");
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
