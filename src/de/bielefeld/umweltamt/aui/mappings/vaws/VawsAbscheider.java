/*
 * Created Tue Sep 06 14:44:16 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.vaws;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'VAWS_Abscheider' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class VawsAbscheider
    extends AbstractVawsAbscheider
    implements Serializable
{
	// Für die Umwandlung von Bool'schen Werten zu Shorts
	private static int fW = 0;
	private static int tW = -1;
	
	/** Für die Umwandlung von Bool'schen Werten zu Shorts */
	public static Integer FALSE = new Integer(fW);
	/** Für die Umwandlung von Bool'schen Werten zu Shorts */
	public static Integer TRUE = new Integer(tW);
	
    /**
     * Simple constructor of VawsAbscheider instances.
     */
    public VawsAbscheider()
    {
    }

    /**
     * Constructor of VawsAbscheider instances given a simple primary key.
     * @param id
     */
    public VawsAbscheider(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */
    
    public String toString() {
    	return "[VawsAbscheider: " + getBehaelterid() + ", FD:"+getVawsFachdaten()+"]";
    }
    
	// Boolean <-> Short:
	
	// TRUE und FALSE sind in dieser Klasse definierte 
	// Short-Konstanten.
	
	// Der Grund, warum ich nicht einfach TRUE.equals(getXyz()) o.ä.
	// zurück liefere ist, dass eigentlich nur festgelegt ist,
	// dass 0 == false ist. Deshalb ist alles ausser 0 und NULL
	// bei mir true.
    
	public boolean bIsKomp() {
		Integer val = getKompaktanlage();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetKomp(boolean isKomp) {
		if (isKomp) {
			setKompaktanlage(TRUE);
		} else {
			setKompaktanlage(FALSE);
		}
	}
    
	public boolean bIsKompLF() {
		Integer val = getLfkl2();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetKompLF(boolean isLf) {
		if (isLf) {
			setLfkl2(TRUE);
		} else {
			setLfkl2(FALSE);
		}
	}
	
	public boolean bIsKompPS() {
		Integer val = getPs();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetKompPS(boolean isPs) {
		if (isPs) {
			setPs(TRUE);
		} else {
			setPs(FALSE);
		}
	}
    
	public boolean bIsKompSF() {
		Integer val = getSf();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetKompSF(boolean isSf) {
		if (isSf) {
			setSf(TRUE);
		} else {
			setSf(FALSE);
		}
	}
	
	public boolean bIsKompK() {
		Integer val = getKkl1();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetKompK(boolean isK) {
		if (isK) {
			setKkl1(TRUE);
		} else {
			setKkl1(FALSE);
		}
	}
	
	public boolean bIsRück() {
		Integer val = getUeberhausr();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetRück(boolean isRück) {
		if (isRück) {
			setRueckhalteausr(TRUE);
		} else {
			setRueckhalteausr(FALSE);
		}
	}
	
	public boolean bIsHoch() {
		Integer val = getHlzapfanl();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetHoch(boolean isHoch) {
		if (isHoch) {
			setHlzapfanl(TRUE);
		} else {
			setHlzapfanl(FALSE);
		}
	}
	
	public boolean bIsAbgabe() {
		Integer val = getAbgabe();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetAbgabe(boolean isAbgabe) {
		if (isAbgabe) {
			setAbgabe(TRUE);
		} else {
			setAbgabe(FALSE);
		}
	}
	
	public boolean bIsBelüft() {
		Integer val = getBelvonlagerbh();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetBelüft(boolean isBelüft) {
		if (isBelüft) {
			setBelvonlagerbh(TRUE);
		} else {
			setBelvonlagerbh(FALSE);
		}
	}
	
	public boolean bIsÜber() {
		Integer val = getUeberhausr();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetÜber(boolean isUeber) {
		if (isUeber) {
			setUeberhausr(TRUE);
		} else {
			setUeberhausr(FALSE);
		}
	}
	
	public boolean bIsWasch() {
		Integer val = getWaschanlvorh();
		if (val == null) {
			return false;
		} else {
			return !(FALSE.equals(val));
		}
	}
	public void bSetWasch(boolean isWasch) {
		if (isWasch) {
			setWaschanlvorh(TRUE);
		} else {
			setWaschanlvorh(FALSE);
		}
	}
    
    // Statischer Teil:

    public static VawsAbscheider getAbscheider(VawsFachdaten fachdaten) {
    	VawsAbscheider abscheider;
    	List tmp;
    	
    	if (fachdaten == null || !fachdaten.getAnlagenart().equals("VAwS-Abscheider")) {
    		throw new IllegalArgumentException("Fachdaten-Objekt ist kein VAwS-Abscheider!");
    	}
    	
    	if (fachdaten.getBehaelterId() == null) {
    		tmp = new ArrayList();
    	} else {
    		try {
    			Session session = HibernateSessionFactory.currentSession();
    			
    			tmp = session.createQuery(
    					"from VawsAbscheider abff " +
						"where abff.vawsFachdaten = ? ")
						.setEntity(0, fachdaten)
						.list();
    			
    		} catch (HibernateException e) {
    			throw new RuntimeException("Datenbank-Fehler", e);
    		} finally {
    			//HibernateSessionFactory.closeSession();
    		}
    	}
		
		if (tmp.size() > 0) {
			abscheider = (VawsAbscheider) tmp.get(0);
			AUIKataster.debugOutput("Fläche '" + abscheider + "' geladen!", "VawsAbscheider.getAbscheider()");
		} else {
			// Bei so ziemlich 95% aller Tankstellen gibts ein VawsFachdaten-
			// Objekt, aber kein VawsAbscheidern-Objekt.
			// Seems like it's not a bug, it's a feature...
			
			// Also legen wir in diesen Fällen einfach ein neues
			// VawsAbscheidern-Objekt an.
			
			// Das selbe tun wir bei einem noch ungespeicherten
			// neuen VawsFachdaten-Objekt.
			
			abscheider = new VawsAbscheider();
			abscheider.setVawsFachdaten(fachdaten);
			AUIKataster.debugOutput("Neuer Abscheider für '" + fachdaten + "' erzeugt!", "VawsAbscheider.getAbscheider()");
		}
    	
    	return abscheider;
    }
    
	/**
	 * Speichert einen VAWS-Abfüllflächen-Datensatz in der Datenbank.
	 * @param flaeche Der zu speichernde Datensatz.
	 * @return <code>true</code>, falls beim Speichern kein Fehler auftritt, sonst <code>false</code>.
	 */
    public static boolean saveAbscheider(VawsAbscheider abscheider) {
    	boolean saved;
    	
    	if (abscheider.getVawsFachdaten() == null) {
    		throw new IllegalArgumentException("Die VawsAbscheider muss einem VawsFachdaten-Objekt zugeordnet sein!");
    	}
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(abscheider);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "VawsAbscheider.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
    
//    /**
//     * Liefert alle Bodenflächen-Ausführungen. 
//     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsAbscheidern, 
//     * sondern alle in der Spalte "BODENFLAECHENAUSF" benutzten Werte!
//     * @return Ein Array mit den Namen aller Ausführungen.
//     */
//    public static String[] getBodenflaechenausfArray() {
//    	//FIXME: select distinct nicht die beste Lösung
//		List list;
//		String suchString = "select distinct vabf.bodenflaechenausf " +
//				"from VawsAbscheider vabf " +
//				"order by vabf.bodenflaechenausf";
//		String[] tmp;
//		
//		try {
//			Session session = HibernateSessionFactory.currentSession();
//			Query query = session.createQuery(suchString);
//			query.setCacheable(true);
//			query.setCacheRegion("vawsabausfliste");
//			list = query.list();
//			tmp = new String[list.size()];
//			tmp = (String[]) list.toArray(tmp);
//		} catch (HibernateException e) {
//			throw new RuntimeException("Datenbank-Fehler", e);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//		
//		return tmp;
//	}
    
//    /**
//     * Liefert alle Bodenflächen-Ausführungen. 
//     * <br><b>ACHTUNG:</b> Liefert nicht alle VawsAbscheidern, 
//     * sondern alle in der Spalte "BODENFLAECHENAUSF" benutzten Werte!
//     * @return Ein Array mit den Namen aller Ausführungen.
//     */
//    public static String[] getNiederschlagschutzArray() {
//    	//FIXME: select distinct nicht die beste Lösung
//		List list;
//		String suchString = "select distinct vabf.niederschlagschutz " +
//				"from VawsAbscheider vabf " +
//				"order by vabf.niederschlagschutz";
//		String[] tmp;
//		
//		try {
//			Session session = HibernateSessionFactory.currentSession();
//			Query query = session.createQuery(suchString);
//			query.setCacheable(true);
//			query.setCacheRegion("vawsabnieliste");
//			list = query.list();
//			tmp = new String[list.size()];
//			tmp = (String[]) list.toArray(tmp);
//		} catch (HibernateException e) {
//			throw new RuntimeException("Datenbank-Fehler", e);
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//		
//		return tmp;
//	}
}
