/*
 * Created Wed Feb 16 15:12:02 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'ATL_PROBEART' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class AtlProbeart
    extends AbstractAtlProbeart
    implements Serializable
{
	/** Die ID von Abwasser-Proben */
	final public static Integer ABWASSER = new Integer(1);
	/** Die ID von UWB Abwasser-Proben */
	final public static Integer ABWASSER_UWB = new Integer(2);
	/** Die ID von E-Satzungs Abwasser-Proben */
	final public static Integer ABWASSER_ES = new Integer(3);
	/** Die ID von Anlieferungs-Proben */
	final public static Integer ANLIEFERUNG = new Integer(4);
	/** Die ID von Faulschlamm-Proben */
	final public static Integer FAULSCHLAMM = new Integer(5);
	/** Die ID von Rohschlamm-Proben */
	final public static Integer ROHSCHLAMM = new Integer(6);
	/** Die ID von Sielhaut-Proben */
	final public static Integer SIELHAUT = new Integer(7);
	/** Die ID von sonstigen Proben */
	final public static Integer SONSTIGE = new Integer(8);
	/** Die ID von Zulauf-Proben */
	final public static Integer ZULAUF = new Integer(9);
	
    /**
     * Simple constructor of AtlProbeart instances.
     */
    public AtlProbeart()
    {
    }

    /**
     * Constructor of AtlProbeart instances given a simple primary key.
     * @param artId
     */
    public AtlProbeart(java.lang.Integer artId)
    {
        super(artId);
    }

    /* Add customized code below */

	public String toString() {
		return getArt();
	}
	
	public boolean isAbwasser() {
		if (ABWASSER.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isAbwasserUWB() {
		if (ABWASSER_UWB.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isAbwasserES() {
		if (ABWASSER_ES.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isAnlieferung() {
		if (ANLIEFERUNG.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isFaulschlamm() {
		if (FAULSCHLAMM.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isRohschlamm() {
		if (ROHSCHLAMM.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isSielhaut() {
		if (SIELHAUT.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isSonstige() {
		if (SONSTIGE.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isZulauf() {
		if (ZULAUF.equals(this.getArtId())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Liefert alle vorhandenen Probearten.
	 * @param session Eine Hibernate-Session
	 * @return Alle vorhandenen Probearten
	 * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
	 */
	public static AtlProbeart[] getProbearten(Session session) throws HibernateException {
		List list = null;
		
		String suchString = "from AtlProbeart art order by art.artId";

		Query query = session.createQuery(suchString);
		query.setCacheable(true);
		query.setCacheRegion("probeartliste");
		list = query.list();
		
		AtlProbeart[] tmp = new AtlProbeart[list.size()];
		tmp = (AtlProbeart[]) list.toArray(tmp);
		
		return tmp;
	}
	
	/**
	 * Liefert alle vorhandenen Probearten. 
	 * öffnet eine neue Hibernate-Session und schließt sie wieder. 
	 * @return Alle vorhandenen Probearten
	 */
	public static AtlProbeart[] getProbearten() {
		AtlProbeart[] tmp = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tmp = getProbearten(session);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return tmp;
	}
	
	/**
	 * Liefert eine bestimmte Probeart.
	 * @param id Die ID der Probeart
	 * @return Die Probeart mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
	 */
	public static AtlProbeart getProbeart(Integer id) {
		AtlProbeart art;
		try {
			Session session = HibernateSessionFactory.currentSession();
			art = (AtlProbeart) session.get(AtlProbeart.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			art = null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
    	
    	return art;
	}
}
