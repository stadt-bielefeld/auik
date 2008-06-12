/*
 * Created Wed Apr 27 12:06:34 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_50_FACHDATEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Anh50Fachdaten
    extends AbstractAnh50Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh50Fachdaten instances.
     */
    public Anh50Fachdaten()
    {
    }

    /**
     * Constructor of Anh50Fachdaten instances given a simple primary key.
     * @param firmenid
     */
    public Anh50Fachdaten(java.lang.Integer firmenid)
    {
        super(firmenid);
    }
    
    public String toString() {
		return "[ID:" + this.getObjektid() + "]";
	}
    
    /**
     * Sucht alle Anhang50-Fachdatensätze, bei denen Amalgam
     * selektiert ist und die nicht erloschen sind.
     * @param nurWiedervorlageAbgelaufen Sollen nur Datensätze 
     * angezeigt werden, deren Wiedervorlage in der 
     * Vergangenheit liegt?
     * @return Eine Liste mit den entstprechenden Anh50Fachdaten.
     */
    public static List findByWiedervorlage(boolean nurWiedervorlageAbgelaufen) {
    	List anhang50;
    	
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		if (nurWiedervorlageAbgelaufen) {
    			Date today = new Date();
    			anhang50 = session.createQuery(
    					"from Anh50Fachdaten as ah50 where " +
						"ah50.wiedervorlage <= ? " +
						"and ah50.erloschen = 'f' " +
						"order by ah50.wiedervorlage, " +
						"ah50.basisObjekt.basisBetreiber.betrname")
						.setDate(0, today)
						.list();

    		} else {
    			anhang50 = session.createQuery(
    					"from Anh50Fachdaten as ah50 where " +
						"ah50.erloschen = 'f' " +
						"order by ah50.wiedervorlage, " +
						"ah50.basisObjekt.basisBetreiber.betrname")
						.list();

    		}
    	} catch (HibernateException e) {
    		throw new RuntimeException(e);
    	} finally {
    		HibernateSessionFactory.closeSession();
    	}
    	
    	return anhang50;
    }

    private static Anh50Fachdaten getAnh50ByObjekt(BasisObjekt objekt, Session session) throws HibernateException {
    	Anh50Fachdaten fachdaten = null;
    	Integer objID = objekt.getObjektid();
    	if (objekt.getBasisObjektarten().isAnh50()) {
				List anhang50 = session.createQuery(
					    "from Anh50Fachdaten as anhang50 where " +
					    "anhang50.objektid = ?")
					    .setEntity(0, objekt)
					    .list();
				
				if (anhang50.size() > 0) {
					fachdaten = (Anh50Fachdaten) anhang50.get(0);
				}
    	}
    	
    	return fachdaten;
    }

    public static Anh50Fachdaten getAnh50ByObjekt(BasisObjekt objekt) {
    	Anh50Fachdaten fachdaten;
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		fachdaten = getAnh50ByObjekt(objekt, session);
    	} catch (HibernateException e) {
    		fachdaten = null;
    	} finally {
    		HibernateSessionFactory.closeSession();
    	}
    	
    	return fachdaten;
    }

    public static boolean saveFachdaten(Anh50Fachdaten fachdaten) {
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
					AUIKataster.handleDBException(e1, "Anh50Fachdaten.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
}
