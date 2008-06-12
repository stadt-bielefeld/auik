/*
 * Created Thu Jan 20 17:02:28 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.List;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * A class that represents a row in the 'VAWS_STANDORTGGHWSG' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsStandortgghwsg
    extends AbstractVawsStandortgghwsg
    implements Serializable
{
    /**
     * Simple constructor of VawsStandortgghwsg instances.
     */
    public VawsStandortgghwsg()
    {
    }

    /**
     * Constructor of VawsStandortgghwsg instances given a simple primary key.
     * @param standortggid
     */
    public VawsStandortgghwsg(java.lang.Integer standortggid)
    {
        super(standortggid);
    }

    /* Add customized code below */

	public String toString() {
		return super.getStandortgg();
	}
	
	/**
	 * Liefert alle vorhandenen VAWS-Standortgeg.
	 * @return Alle vorhandenen VawsStandortgghwsg
	 */
	public static VawsStandortgghwsg[] getStandortGg() {
		List list;
		String suchString = "FROM VawsStandortgghwsg vsgg ORDER BY vsgg.standortggid";
		VawsStandortgghwsg[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("standortggliste");
			list = query.list();
			tmp = new VawsStandortgghwsg[list.size()];
			tmp = (VawsStandortgghwsg[]) list.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (VawsStandortgghwsg)", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}
}
