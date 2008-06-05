/*
 * Created Tue Sep 06 14:47:50 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_PRUEFERGEBNISSE' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsPruefergebnisse
    extends AbstractVawsPruefergebnisse
    implements Serializable
{
    /**
     * Simple constructor of VawsPruefergebnisse instances.
     */
    public VawsPruefergebnisse()
    {
    }

    /* Add customized code below */

    /**
     * Liefert alle Prüfergebnisse für die Sachverständigenprüfung.
     * @return Ein Array mit den Namen aller möglichen Prüfergebnisse.
     */
    public static String[] getAllPruefergebnisse() {
		List list;
		String suchString = "select prferg.pruefergebnis " +
				"from VawsPruefergebnisse prferg " +
				"order by prferg.pruefergebnis";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsprfergliste");
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
