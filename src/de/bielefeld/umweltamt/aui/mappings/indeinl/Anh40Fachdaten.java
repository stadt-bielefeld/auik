/*
 * Created Wed Jun 29 10:02:39 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_40_FACHDATEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Anh40Fachdaten
    extends AbstractAnh40Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh40Fachdaten instances.
     */
    public Anh40Fachdaten()
    {
    }

    /**
     * Liefert einen String der Form "[Anhang 40:ID]"
     */
	public String toString() {
		return "[Anhang 40:" + getBasisObjekt() + "]";
	}
	
    /**
	 * Liefert eine Liste mit allen Anhang40 Objekten.
	 * @return Eine Liste aus Anh40Fachdaten.
	 */
	public static List getAuswertungsListe() {
		List liste;
		
		String query = "from Anh40Fachdaten as anh40 " +
				"order by anh40.id";
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			liste = session.createQuery(query).list();
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return liste;
	}

	private static Anh40Fachdaten getAnh40ByObjekt(BasisObjekt objekt, Session session) throws HibernateException {
    	Anh40Fachdaten fachdaten = null;
    	if (objekt.getBasisObjektarten().isAnh40()) {
    		List anhang40 = session.createQuery(
    				"from Anh40Fachdaten as ah40 where " +
					"ah40.basisObjekt = ?")
					.setEntity(0, objekt)
					.list();
    		
    		if (anhang40.size() > 0) {
    			fachdaten = (Anh40Fachdaten) anhang40.get(0);
    		}
    	}
    	
    	return fachdaten;
    }

    public static Anh40Fachdaten getAnh40ByObjekt(BasisObjekt objekt) {
    	Anh40Fachdaten fachdaten;
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		fachdaten = getAnh40ByObjekt(objekt, session);
    		HibernateSessionFactory.closeSession();
    	} catch (HibernateException e) {
    		fachdaten = null;
    		throw new RuntimeException("Datenbank-Fehler", e);
    	}
    	
    	return fachdaten;
    }

    public static boolean saveAnh40(Anh40Fachdaten anh40) {
    	boolean saved;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(anh40);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "Anh40.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }

}
