/*
 * Created Tue Sep 06 14:47:25 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

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
 * A class that represents a row in the 'VAWS_OBJEKTCHRONO' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class BasisObjektchrono
    extends AbstractBasisObjektchrono
    implements Serializable
{
    /**
     * Simple constructor of VawsObjektchrono instances.
     */
    public BasisObjektchrono()
    {
    }

    /**
     * Constructor of VawsObjektchrono instances given a simple primary key.
     * @param id
     */
    public BasisObjektchrono(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    
    // Statischer Teil:
    
    /**
     * Liefert alle VAWS-Fachdatensätze zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit VawsFachdaten.
     */
	public static List getChronoByObjekt(BasisObjekt objekt) {
    	List chrono;
    	
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		chrono = session.createQuery(
    				"from BasisObjektchrono oc where " +
					"oc.basisObjekt = ? " +
					"order by oc.datum, oc.wv")
					.setEntity(0, objekt)
					.list();
    		
    	} catch (HibernateException e) {
    		throw new RuntimeException("Datenbank-Fehler", e);
    	} finally {
    		HibernateSessionFactory.closeSession();
    	}
    	
    	return chrono;
    }
	
	/**
	 * Speichert einen Objektchronologie-Eintrag in der Datenbank.
	 * @param chrono Der zu speichernde Datensatz.
	 * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
	 */
    public static boolean saveObjektChrono(BasisObjektchrono chrono) {
    	boolean saved;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(chrono);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "BasisObjektchrono.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
    
    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param chrono Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder 
     * <code>false</code> falls dabei ein Fehler auftrat (z.B. der Datensatz 
     * nicht in der Datenbank existiert).
     */
    public static boolean removeObjektChrono(BasisObjektchrono chrono) {
    	boolean removed;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(chrono);
			tx.commit();
			removed = true;
		} catch (HibernateException e) {
			removed = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "BasisObjektchrono.remove", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return removed;
    }

	/**
	 * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr". 
	 */
	public String toString() {
		String tmp = getId() + ": ";
		//= "[Vaws:" + getAnlagenart() + "," + getBasisObjekt() + "]";
		if (getDatum() != null) {
			tmp += getDatum() + " ";
		}
		if (getSachverhalt() != null) {
			tmp += getSachverhalt();
		}
		return tmp;
	}
}
