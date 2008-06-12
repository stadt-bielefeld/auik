/*
 * Created Thu May 19 12:48:18 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_49_FACHDATEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Anh49Fachdaten
    extends AbstractAnh49Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh49Fachdaten instances.
     */
    public Anh49Fachdaten()
    {
    }
//
//    /**
//     * Constructor of Anh49Fachdaten instances given a simple primary key.
//     * @param basisObjekt
//     */
//    public Anh49Fachdaten(BasisObjekt basisObjekt)
//    {
//        super(basisObjekt);
//    }

    

	public String toString() {
		String tmp = "[Anh49:" + getBasisObjekt() + "]";
		return tmp;
	}
	
    /**
     * Sucht Anhang49-Fachdatensätze nach bestimmten Kriterien.
     * @param sachbearbeiter Welche(r) SachbearbeiterIn (oder "")?
     * @param abgemeldet
     * param abgerissen
     * @param abwasserfrei
     * @param dekratuev
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze 
     * angezeigt werden, deren Wiedervorlage in der 
     * Vergangenheit liegt?
     * @return Eine Liste mit den entstprechenden Anh49Fachdaten.
     */
    public static List findAuswertung(
    		String sachbearbeiter, 
			Boolean abgemeldet, 
			String abgerissen, 
			Boolean abwasserfrei, 
			Integer dekratuev, 
			boolean nurWiedervorlageAbgelaufen) {
    	List anhang49;
    	
    	Date today = new Date();
    	
    	String sb2 = sachbearbeiter.toLowerCase().trim() + "%";
    	String abgr = abgerissen.toLowerCase().trim() + "%";
    	
    	String query = "from Anh49Fachdaten ah49 where " +
		"ah49.abgemeldet = ? and " +
		"lower(ah49.sonstigestechnik) like ? and " +
		"ah49.abwasserfrei = ? ";

		if (nurWiedervorlageAbgelaufen) {
			if (dekratuev != null) {
				query += "and ah49.dekraTuevTermin = ? and "
					+ "ah49.wiedervorlage <= ? " +
					"order by ah49.wiedervorlage, " +
					"ah49.basisObjekt.basisBetreiber.betrname";
					//Date today = new Date();
					
		    		Session session = HibernateSessionFactory.currentSession();
		    		anhang49 = session.createQuery(query)
		    		.setBoolean(0, abgemeldet)
		    		.setString(1, abgr)
		    		.setBoolean(2, abwasserfrei)
		    		.setInteger(3, dekratuev)
		    		.setDate(4, today)
		    		.list();
		    		HibernateSessionFactory.closeSession();
				}
			else {
					query += "and ah49.wiedervorlage <= ? " +
					"order by ah49.wiedervorlage, " +
					"ah49.basisObjekt.basisBetreiber.betrname";
					//Date today = new Date();

		    		Session session = HibernateSessionFactory.currentSession();
		    		anhang49 = session.createQuery(query)
		    		.setBoolean(0, abgemeldet)
		    		.setString(1, abgr)
		    		.setBoolean(2, abwasserfrei)
		    		.setDate(3, today)
		    		.list();
		    		HibernateSessionFactory.closeSession();
			} 
    	} else {
			if (dekratuev != null) {
					query += "and ah49.dekraTuevTermin = ? " +
					"order by ah49.wiedervorlage, " +
					"ah49.basisObjekt.basisBetreiber.betrname";

		    		Session session = HibernateSessionFactory.currentSession();
		    		anhang49 = session.createQuery(query)
		    		.setBoolean(0, abgemeldet)
		    		.setString(1, abgr)
		    		.setBoolean(2, abwasserfrei)
		    		.setInteger(3, dekratuev)
		    		.list();
		    		HibernateSessionFactory.closeSession();
		
			} else {
				query +=
				"order by ah49.wiedervorlage, " +
				"ah49.basisObjekt.basisBetreiber.betrname";
				
	    		Session session = HibernateSessionFactory.currentSession();
	    		anhang49 = session.createQuery(query)
	    		.setBoolean(0, abgemeldet)
	    		.setString(1, abgr)
	    		.setBoolean(2, abwasserfrei)
	    		.list();
	    		HibernateSessionFactory.closeSession();
			}
    	}
    	


//    	try {
//    		Session session = HibernateSessionFactory.currentSession();
//    		anhang49 = session.createQuery(query)
//    		.setBoolean(0, abgemeldet)
//    		.setString(1, abgr)
//    		.setBoolean(2, abwasserfrei)
//    		.list();
//    		
//    	} catch (HibernateException e) {
//    		throw new RuntimeException(e);
//    	} finally {
//    		HibernateSessionFactory.closeSession();
//    	}
    	
    	return anhang49;
    }
	
    public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt, Session session) {
    	Anh49Fachdaten fachdaten = null;
    	Integer objID = objekt.getObjektid();
    	if (objekt.getBasisObjektarten().isAnh49()) {
    		try {
				List anhang49 = session.createQuery(
					    "from Anh49Fachdaten as ah49 where " +
					    "ah49.basisObjekt = ?")
					    .setEntity(0, objekt)
					    .list();
				
				if (anhang49.size() > 0) {
					fachdaten = (Anh49Fachdaten) anhang49.get(0);
				}
			} catch (HibernateException e) {
				throw new RuntimeException("Datenbank-Fehler", e);
			} finally {
				HibernateSessionFactory.closeSession();
			}
    	}
    	
    	return fachdaten;
    }

    public static Anh49Fachdaten getAnh49ByObjekt(BasisObjekt objekt) {
    	Anh49Fachdaten fachdaten;
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		fachdaten = getAnh49ByObjekt(objekt, session);
    		HibernateSessionFactory.closeSession();
    	} catch (HibernateException e) {
    		fachdaten = null;
    	} finally {
			HibernateSessionFactory.closeSession();
		}
    	
    	return fachdaten;
    }

    public static boolean saveFachdaten(Anh49Fachdaten fachdaten) {
    	boolean saved;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(fachdaten);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "Anh49Fachdaten.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
}
