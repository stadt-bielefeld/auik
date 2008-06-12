/*
 * Created Wed Feb 16 15:12:00 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * A class that represents a row in the 'ATL_EINHEITEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class AtlEinheiten
    extends AbstractAtlEinheiten
    implements Serializable
{
	/** Die ID der Einheit "mg/l" */
	final public static Integer MG_L_ID = new Integer(42);
	/** Die ID der Einheit "mg/kg" */
	final public static Integer MG_KG_ID = new Integer(43);
	/** Die ID der Einheit "%" */
	final public static Integer PROZENT_ID = new Integer(63);
	
	final public static Integer DEFAULT_ID = MG_L_ID;
	
	/**
	 * Die Map für die beim Import unterstützten Einheiten.
	 * Sie enthält eine Zuordnung von Einheitennamen zu den 
	 * jeweiligen Schlüsseln der Einheiten-Tabelle.
	 * Sie wird in initMap() gefüllt.
	 */
	private static Map sEinheiten = null;
	
    /**
     * Simple constructor of AtlEinheiten instances.
     */
    public AtlEinheiten()
    {
    }

    /**
     * Constructor of AtlEinheiten instances given a simple primary key.
     * @param id
     */
    public AtlEinheiten(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */

    /**
     * @return Der Name der Einheit.
     */
	public String toString() {
		String tmp = getBezeichnung();
		if (tmp != null) {
			tmp = tmp.trim();
		}
		return tmp;
	}
	
	/**
	 * Initialisiert die Map für die beim Import unterstützten 
	 * Einheiten. Sie enthält eine Zuordnung von Einheitennamen 
	 * zu den jeweiligen Schlüsseln der Einheiten-Tabelle.
	 */
	private static void initMap() {
		if (sEinheiten == null) {
			sEinheiten = new HashMap();
			
			// Sielhaut:
			sEinheiten.put("mg/kg TS", AtlEinheiten.MG_KG_ID);
			sEinheiten.put("%", AtlEinheiten.PROZENT_ID);
			
			// ICP-Daten liegen immer als mg/L vor
		}
	}
	
	/**
	 * Überprüft ob ein entsprechender Parameter importierbar ist.
	 * @param name Der Einheiten-Name (bspw. "%").
	 * @return <code>true</code>, wenn eine entsprechende Einheit bekannt ist, sonst <code>false</code>.
	 */
	public static boolean isEinheitSupported(String name) {
		initMap();
		return sEinheiten.containsKey(name);
	}
	
	/**
	 * Liefert den Schlüssel für eine Einheit.
	 * @param name Der Einheiten-Name (bspw. "%").
	 * @return Den Schlüssel der Einheit oder <code>null</code>, falls die Einheit nicht unterstützt wird.
	 */
	public static Integer getID(String name) {
		initMap();
		return (Integer) sEinheiten.get(name);
	}
	
	/**
	 * Liefert alle in der Einheiten-Tabelle gespeicherten Einheiten.
	 * @return Ein Array mit allen Einheiten
	 */
	public static AtlEinheiten[] getEinheiten() {
		AtlEinheiten[] tmp;
		try {
			Session session = HibernateSessionFactory.currentSession();
			List einheiten = session.createQuery("from AtlEinheiten as einheit").list();
			
			tmp = new AtlEinheiten[einheiten.size()];
			tmp = (AtlEinheiten[]) einheiten.toArray(tmp);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
    	
    	return tmp;
	}
	
	/**
	 * Liefert eine bestimmte Einheit.
	 * @param id Die ID der Einheit
	 * @return Die Einheit mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
	 */
	public static AtlEinheiten getEinheit(Integer id) {
		AtlEinheiten einheit;
		
		if (id != null) {
			try {
				Session session = HibernateSessionFactory.currentSession();
				einheit = (AtlEinheiten) session.get(AtlEinheiten.class, id);
			} catch (HibernateException e) {
				e.printStackTrace();
				einheit = null;
			} finally {
				HibernateSessionFactory.closeSession();
			}
		} else {
			einheit = null;
		}
    	
    	return einheit;
	}
}
