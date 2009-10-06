package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'VAWS_FACHDATEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsFachdaten
    extends AbstractVawsFachdaten
    implements Serializable
{

	
    /**
     * Simple constructor of VawsFachdaten instances.
     */
    public VawsFachdaten()
    {
    }

    /**
     * Constructor of VawsFachdaten instances given a simple primary key.
     * @param behaelterId
     */
    public VawsFachdaten(Integer behaelterId)
    {
        super(behaelterId);
    }
    
    /**
     * Liefert einen String der Form "BehaelterID: Anlagenart Herstellnr". 
     */
	public String toString() {
		String tmp = getBehaelterId() + ": ";
		//= "[Vaws:" + getAnlagenart() + "," + getBasisObjekt() + "]";
		if (getAnlagenart() != null) {
			tmp += getAnlagenart() + " ";
		}
		if (getHerstellnr() != null) {
			tmp += getHerstellnr();
		}
		return tmp;
	}
	
	public String getStillegungsDatumString() {
		if (getStillegungsdatum() != null) {
			DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
			return f.format(getStillegungsdatum());
		} else {
			return null;
		}
//		return AuikUtils.getStringFromDate(getStillegungsdatum());
	}
	
	// Anlagenart:
	
	public boolean isAbfuellflaeche() {
		if ("Abfüllfläche".equals(getAnlagenart())) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isVAWSAbscheider()
	{
	    if ("VAwS-Abscheider".equals(getAnlagenart()))
	    {
	        return true;
	    }
	    else
	    {
	        return false;
	    }
	}	

	
	public boolean isLageranlage() {
		if (isAbfuellflaeche() || isRohrleitung()) {
			return false;
		} else {
			return true;
		}
	}
	

	
	public boolean isRohrleitung() {
		if ("Rohrleitung".equals(getAnlagenart())) {
			return true;
		} else {
			return false;
		}
	}
	
	// Boolean <-> Short:
	
	// TRUE und FALSE sind in dieser Klasse definierte 
	// Short-Konstanten.
	
	// Der Grund, warum ich nicht einfach TRUE.equals(getXyz()) o.Ä.
	// zurück liefere ist, dass eigentlich nur festgelegt ist,
	// dass 0 == false ist. Deshalb ist alles ausser 0 und NULL
	// bei mir true.
	
	/**
     * Liefert alle VAWS-Fachdatensätze zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit VawsFachdaten.
     */
	public static List getVawsByObjekt(BasisObjekt objekt) {
    	List vaws;
    	if (objekt.getBasisObjektarten().abteilungIs34()) {
    		try {
    			Session session = HibernateSessionFactory.currentSession();
				vaws = session.createQuery(
					    "from VawsFachdaten as v where " +
					    "v.basisObjekt = ? " +
					    "order by v.stillegungsdatum desc, v.anlagenart asc, v.herstellnr asc")
					    .setEntity(0, objekt)
					    .list();
					    
				AUIKataster.debugOutput(vaws.size() + " VawsFachdatensätze für BO " + objekt + " gefunden!", "VawsFachdaten");
			} catch (HibernateException e) {
				throw new RuntimeException("Datenbank-Fehler", e);
			} finally {
				HibernateSessionFactory.closeSession();
			}
    	} else {
    		throw new IllegalArgumentException("Zu diesem BasisObjekt existieren keine VAWS-Fachdaten-Objekte!");
    	}
    	
    	return vaws;
    }
	
	
	/**
     * Liefert alle VAWS-Fachdatensätze zu einem bestimmten BasisObjekt.
     * @param objekt Das BasisObjekt.
     * @return Eine Liste mit VawsFachdaten.
     */
	public static VawsFachdaten getVawsByBehaelterId(Integer id) {
		VawsFachdaten fachdaten;
		try {
			Session session = HibernateSessionFactory.currentSession();
			fachdaten = (VawsFachdaten) session.get(VawsFachdaten.class, id);
			HibernateSessionFactory.closeSession();
		}catch (HibernateException e) {
			fachdaten = null;
		}
		return fachdaten;
	}
	
	/**
	 * Speichert einen VAWS-Fachdatensatz in der Datenbank.
	 * 
	 * @param fachdaten
	 *            Der zu speichernde Datensatz.
	 * @return <code>true</code>, falls beim Speichern kein Fehler auftritt,
	 *         sonst <code>false</code>.
	 */
    public static boolean saveFachdaten(VawsFachdaten fachdaten) {
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
					AUIKataster.handleDBException(e1, "VawsFachdaten.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
    
    /**
     * Löscht einen vorhandenen Datensatz aus der Datenbank.
     * @param fachdaten Der Datensatz, der gelöscht werden soll.
     * @return <code>true</code>, wenn der Datensatz gelöscht wurde oder 
     * <code>false</code> falls dabei ein Fehler auftrat (z.B. der Datensatz 
     * nicht in der Datenbank existiert).
     */
    public static boolean removeFachdaten(VawsFachdaten fachdaten) {
    	boolean removed;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(fachdaten);
			tx.commit();
			removed = true;
		} catch (HibernateException e) {
			removed = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "VawsFachdaten.remove", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return removed;
    }
    
    /**
     * Liefert alle VAWS-Ausführungen. 
     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsFachdaten, sondern 
     * alle in der Spalte "AUSFUEHRUNG" benutzten Werte!
     * @return Ein Array mit den Namen aller Ausführungen.
     */
    public static String[] getAusfuehrungen() {
    	// FIXME: select distinct nicht die beste Lösung
		List list;
		String suchString = "select distinct fd.ausfuehrung " +
				"from VawsFachdaten fd " +
				"order by fd.ausfuehrung";
		String[] tmp;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			Query query = session.createQuery(suchString);
			query.setCacheable(true);
			query.setCacheRegion("vawsausfliste");
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
    
    /* Durchsucht VawsFachdaten nach einer bestimmten Herstellnummer und gibt das Ergebnis als List zurück */
    public static List findherstellnr(String herstellnr) {

		List vaws;
        
		String query = 	"from VawsFachdaten vaws "+
						"where vaws.herstellnr = ?";

                        
		Session session = HibernateSessionFactory.currentSession();
		vaws = session.createQuery(query)
			.setString(0, herstellnr)
			.list();
		HibernateSessionFactory.closeSession();

		return vaws;

	}
    
    
    
    
}
