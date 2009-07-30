/*
 * Created Wed Jan 19 10:44:46 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'BASIS_OBJEKT' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class BasisObjekt
    extends AbstractBasisObjekt
    implements Serializable
{
    /**
     * Simple constructor of BasisObjekt instances.
     */
    public BasisObjekt()
    {
    }

    /**
     * Constructor of BasisObjekt instances given a simple primary key.
     * @param objektid
     */
    public BasisObjekt(java.lang.Integer objektid)
    {
        super(objektid);
    }

    /* Add customized code below */
    
    /**
     * Liefert einen String, der dieses BasisObjekt beschreibt.
     * @return Einen String in der Form "[ID:Objekt-ID, Betr.:BasisBetreiber, Stdort:BasisStandort, Art:BasisObjektart]"
     */
    public String toString() {
		return "[ID:" + this.getObjektid() + ", Betr.:" + this.getBasisBetreiber() + ", Stdort:" + this.getBasisStandort() + ", Art:" + this.getBasisObjektarten() + "]";
	}

    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Betreiber zugeordnet sind.
     * @param betr Der Betreiber.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll, sonst <code>null</code>.
     * @return Eine Liste von BasisObjekten dieses Betreibers.
     */
    public static List getObjekteByBetreiber(BasisBetreiber betr, String abteilung) {
    	List objekte;

    	String query = 
    		"from BasisObjekt as bo " +
			"where bo.basisBetreiber = ?";
    	
    	if (abteilung != null) {
    		query += "and bo.basisObjektarten.abteilung = '" + abteilung + "' ";
    	}
    	
    	query += "order by bo.inaktiv, bo.basisStandort.strasse, bo.basisStandort.hausnr, bo.basisObjektarten.objektart";
    	
		try {
			Session session = HibernateSessionFactory.currentSession();
			objekte = session.createQuery(
					query)
					.setEntity(0, betr)
					.list();

		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return objekte;
    }
    
    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet sind.
     * @param betr Der Standort.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll, sonst <code>null</code>.
     * @param nichtartid Die Objektart, die nicht dargestellt werden soll.
     * @return Eine Liste von BasisObjekten an diesem Standort.
     */
    public static List getObjekteByStandort(BasisStandort standort, String abteilung, Integer nichtartid) {
    	List objekte;
    	
    	String query = 
    		"from BasisObjekt as bo " +
			"where bo.basisStandort = ? "; // +
//			"and bo.inaktiv = ? ";
    	
    	if (abteilung != null) {
    		query += "and bo.basisObjektarten.abteilung = '" + abteilung + "' ";
    	}
    	
    	if (nichtartid != null) {
    		query += "and bo.basisObjektarten.objektartid != " + nichtartid;
    	}
    	
    	query += "order by bo.inaktiv, bo.basisBetreiber.betrname, bo.basisObjektarten.objektart";
    	
    	try {
    	Session session = HibernateSessionFactory.currentSession();
		objekte = session.createQuery(
				query)
				.setEntity(0, standort)
//				.setString(1, "f")
				.list();

    	} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return objekte;
    }
    
    /**
     * Liefert eine Liste von Objekten, die einem bestimmten Standort zugeordnet sind.
     * @param betr Der Standort.
     * @param abteilung Die Abteilung, wenn nach ihr gefiltert werden soll, sonst <code>null</code>.
     * @param nichtartid Die Objektart, die nicht dargestellt werden soll.
     * @return Eine Liste von BasisObjekten an diesem Standort.
     */
    public static List getObjekteByStandort(BasisStandort standort, Integer istartid) {
    	List objekte;
    	
    	String query = 
    		"from BasisObjekt as bo " +
			"where bo.basisStandort = ? ";
    	
    	if (istartid != null) {
    		query += "and bo.basisObjektarten.objektartid = ?";
    	}
    	
    	query += "order by bo.inaktiv, bo.basisBetreiber.betrname, bo.basisObjektarten.objektart";
    	
    	try {
    	Session session = HibernateSessionFactory.currentSession();
		objekte = session.createQuery(
				query)
				.setEntity(0, standort)
				.setInteger(1, 32)
				.list();

    	} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return objekte;
    }
    
    /**
     * Lädt ein Objekt aus der Datenbank.
     * @param id Der Primärschlüssel des zu ladenden Objekts.
     * @return  Das BasisObjekt mit dem Primärschlüssel oder <code>null</code>, 
     * 			falls ein solches nicht gefunden wurde.
     */
    public static BasisObjekt getObjekt(Integer id) {
    	BasisObjekt objekt;
		try {
			Session session = HibernateSessionFactory.currentSession();
			objekt = (BasisObjekt) session.get(BasisObjekt.class, id);
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			objekt = null;
		}
    	
    	return objekt;
    }
    
    /**
     * Speichert ein Objekt in der Datenbank.
     * @param obj Das zu speichernde Objekt.
     * @return Das gespeicherte Objekt.
     */
    public static BasisObjekt saveBasisObjekt(BasisObjekt obj) {
    	BasisObjekt saved;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			saved = obj;

			tx.commit();
		} catch (HibernateException e) {
			saved = null;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "BasisObjekt.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
    
    /**
     * Löscht ein Objekt aus der Datenbank.
     * @param obj Das zu löschende Objekt.
     * @return <code>true</code>, wenn das Objekt gelöscht wurde, sonst <code>false</code>.
     */
    public static boolean removeBasisObjekt(BasisObjekt obj) {
    	boolean removed;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(obj);
			tx.commit();
			removed = true;
		} catch (HibernateException e) {
			removed = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "BasisObjektModel.objectRemoved", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return removed;
    }
}
