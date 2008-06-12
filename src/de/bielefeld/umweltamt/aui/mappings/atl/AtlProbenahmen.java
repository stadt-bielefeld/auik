/*
 * Created Wed Feb 16 15:12:02 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.atl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.utils.AuikUtils;

/**
 * A class that represents a row in the 'ATL_PROBENAHMEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class AtlProbenahmen
    extends AbstractAtlProbenahmen
    implements Serializable
{
    /**
     * Simple constructor of AtlProbenahmen instances.
     */
    public AtlProbenahmen()
    {
    }

    /**
     * Constructor of AtlProbenahmen instances given a simple primary key.
     * @param kennummer
     */
    public AtlProbenahmen(java.lang.Integer id)
    {
        super(id);
    }

    /* Add customized code below */
    
    /**
     * Liefert alle Probenahmen einer bestimmten Art von einer 
     * bestimmten Kläranlage. 
     */
    public static List getKSProbenahmen(AtlProbeart art, AtlKlaeranlagen ka) {
    	// Evtl. Version mit gegebener Session bauen?
    	List proben;
		try {
			Session session = HibernateSessionFactory.currentSession();
			proben = session.createQuery(
				    "from AtlProbenahmen as probenahme where " +
				    "probenahme.atlProbepkt.atlProbeart = ? " +
				    "and probenahme.atlProbepkt.atlKlaeranlagen = ? " +
				    "order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc")
				    .setEntity(0, art)
				    .setEntity(1, ka)
				    .list();

			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		}
    	return proben;
    }
    
    /**
     * Liefert alle Probenahmen eines bestimmten Probepunktes.
     * @param pkt Der Probepunkt.
     * @param loadPos Sollen die Analysepositionen auch geholt werden?
     */
    public static List getProbenahmen(AtlProbepkt pkt, boolean loadPos, int limit) {
    	List proben;
    	if (pkt.getPktId() != null) {
    		try {
    			Session session = HibernateSessionFactory.currentSession();
    			Query query = session.createQuery("from AtlProbenahmen as probenahme where " +
						"probenahme.atlProbepkt = ? " +
						"order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc");
    			query.setParameter(0, pkt, Hibernate.entity(AtlProbepkt.class));
    			if (limit != -1) {
    				query.setMaxResults(5);
    			}
    			proben = query.list();
//    			proben = session.find(
//    					,
//						pkt,
//						Hibernate.entity(AtlProbepkt.class)
//    			);
    			
    			if (loadPos) {
    				for (int i = 0; i < proben.size(); i++) {
    					AtlProbenahmen probe = (AtlProbenahmen)proben.get(i);
    					Hibernate.initialize(probe.getAtlAnalysepositionen());
    				}
    			}
    		} catch (HibernateException e) {
    			throw new RuntimeException("Datenbank-Fehler", e);
    		} finally {
    			HibernateSessionFactory.closeSession();
    		}
    	} else {
    		proben = new ArrayList();
    	}
    	
    	return proben;
    }
    
//    /**
//     * Liefert alle Probenahmen eines bestimmten Probepunktes.
//     * @param pkt Der Probepunkt.
//     * @param loadPos Sollen die Analysepositionen auch geholt werden?
//     */
//    public static List getProbenahmenAndPos(AtlProbepkt pkt, AtlParameter[] params) {
//    	List proben;
//    	if (pkt.getPktId() != null) {
//    		try {
//    			Session session = HibernateSessionFactory.currentSession();
//    			proben = session.find(
//    					"from AtlProbenahmen as probenahme where " +
//						"probenahme.atlProbepkt = ? " +
//						"order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc",
//						pkt,
//						Hibernate.entity(AtlProbepkt.class)
//    			);
//    		} catch (HibernateException e) {
//    			throw new RuntimeException("Datenbank-Fehler", e);
//    		} finally {
//    			HibernateSessionFactory.closeSession();
//    		}
//    	} else {
//    		proben = new ArrayList();
//    	}
//    	
//    	return proben;
//    }
    
    public static List findProbenahmen(String suche, String property) {
    	String suche2 = suche.toLowerCase().trim() + "%";
    	AUIKataster.debugOutput("Suche nach '" + suche2 + "' (" + property + ").", "AtlProbenahmen.findProbenahmen");
    	List proben;
		try {
			Session session = HibernateSessionFactory.currentSession();
			proben = session.createQuery(
				    "from AtlProbenahmen as probenahme where " +
				    "lower(probenahme."+property+") like ? " +
				    "order by probenahme.datumDerEntnahme desc, probenahme.kennummer desc")
				    .setString(0, suche2)
				    .list();
			HibernateSessionFactory.closeSession();

		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		}
    	return proben;
    }
    
    /**
     * Überprüft ob eine Probenahme mit einer bestimmten Kennnummer existiert.
     * @param kennnummer Die Kennnummer.
     * @return <code>true</code>, falls bereits eine Probenahme mit dieser Kennung existiert, sonst <code>false</code>.
     */
    public static boolean probenahmeExists(String kennnummer) {
    	String Kennummer = kennnummer;
    	int count;
    	try {
			Session session = HibernateSessionFactory.currentSession();

			List liste;
			liste = session.createQuery("from AtlProbenahmen pn where pn.kennummer = ?")
			.setString(0, kennnummer)
			.list();
			
			count = ((Integer) session.createQuery("from AtlProbenahmen pn where pn.kennummer = ?")
					.setString(0, kennnummer)
					.list().size());
			HibernateSessionFactory.closeSession();


		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		}

    	return (count >= 1);



    }
    
    /**
	 * Liefert eine bestimmte Probenahme.
	 * @param id Die ID der Probenahme
	 * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
	 */
	public static AtlProbenahmen getProbenahme(Integer id) {
		return getProbenahme(id, false);
	}
	
	/**
	 * Liefert eine bestimmte Probenahme.
	 * @param id Die ID der Probenahme
	 * @param loadPos Sollen die Analysepositionen auch geholt werden?
	 * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
	 */
	public static AtlProbenahmen getProbenahme(Integer id, boolean loadPos) {
		AtlProbenahmen probe;
		try {
			Session session = HibernateSessionFactory.currentSession();
			probe = (AtlProbenahmen) session.get(AtlProbenahmen.class, id);
			if (loadPos && probe != null) {
				Hibernate.initialize(probe.getAtlAnalysepositionen());
				//Collection sorted = session.filter(probe.getAtlAnalysepositionen(), "order by this.atlParameter.ordnungsbegriff desc");
				//AUIKataster.debugOutput("Sorted:\n " + sorted);
				//probe.setAtlAnalysepositionen(new HashSet(sorted));
				AUIKataster.debugOutput("APos geladen:\n " + probe.getAtlAnalysepositionen());
			}
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (AtlProbenahmen)", e);
		}
    	
    	return probe;
	}
	
	/**
	 * Liefert eine bestimmte Probenahme.
	 * @param kennummer Die Kennummer der Probenahme
	 * @param loadPos Sollen die Analysepositionen auch geholt werden?
	 * @return Die Probe mit der gegebenen ID oder <code>null</code> falls diese nicht existiert
	 */
	public static AtlProbenahmen getProbenahme(String kennummer, boolean loadPos) {
		AtlProbenahmen probe;
		try {
			Session session = HibernateSessionFactory.currentSession();
			probe = (AtlProbenahmen) session.createQuery("from AtlProbenahmen where kennummer = ?")
				.setString(0, kennummer)
				.uniqueResult();
			if (loadPos && probe != null) {
				Hibernate.initialize(probe.getAtlAnalysepositionen());
				//Collection sorted = session.filter(probe.getAtlAnalysepositionen(), "order by this.atlParameter.ordnungsbegriff desc");
				//AUIKataster.debugOutput("Sorted:\n " + sorted);
				//probe.setAtlAnalysepositionen(new HashSet(sorted));
				AUIKataster.debugOutput("APos geladen:\n " + probe.getAtlAnalysepositionen());
			}
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (AtlProbenahmen)", e);
		}
    	
    	return probe;
	}
	
	public static boolean saveProbenahme(AtlProbenahmen probe) {
		boolean success;
		
		Transaction tx = null;
    	
    	try {
			Session session = HibernateSessionFactory.currentSession();
			
			tx = session.beginTransaction();
			
			session.save(probe);
			
			tx.commit();
			
			success = true;
			
		} catch (HibernateException e) {
			success = false;
			e.printStackTrace();
			// Falls während der Änderungen ein Hibernate Fehler auftritt
			if (tx != null) {
				try {
					// Alle Änderungen rückgängig machen
					tx.rollback();
				} catch (HibernateException e1) {
					throw new RuntimeException("Datenbank-Fehler (Anh49Ortstermine)", e);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return success;
	}
	
	public static boolean updateProbenahme(AtlProbenahmen probe) {
		boolean success;
		
		Transaction tx = null;
    	
    	try {
			Session session = HibernateSessionFactory.currentSession();
			
			tx = session.beginTransaction();
			
			session.saveOrUpdate(probe);
			
			tx.commit();
			
			success = true;
			
		} catch (HibernateException e) {
			success = false;
			e.printStackTrace();
			// Falls während der Änderungen ein Hibernate Fehler auftritt
			if (tx != null) {
				try {
					// Alle Änderungen rückgängig machen
					tx.rollback();
				} catch (HibernateException e1) {
					throw new RuntimeException("Datenbank-Fehler (Anh49Ortstermine)", e);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return success;
	}
	
	public static boolean removeProbenahme(AtlProbenahmen probe) {
		boolean success;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(probe);
			tx.commit();
			success = true;
		} catch (HibernateException e) {
			success = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "ProbenahmenModel.objectRemoved", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return success;
	}
	
	public static List sortAnalysepositionen(AtlProbenahmen probe) {
		List sortedPositionen;
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			AtlProbenahmen newProbe = (AtlProbenahmen) session.get(AtlProbenahmen.class, probe.getId());
			//Hibernate.initialize(probe.getAtlAnalysepositionen());
			sortedPositionen = session.createFilter(newProbe.getAtlAnalysepositionen(), "order by lower(this.atlParameter.bezeichnung) asc, this.atlEinheiten.id desc, this.wert desc")
				.list();

			//AUIKataster.debugOutput("Sorted (Klasse: "+sortedPositionen.getClass()+"):\n " + sortedPositionen);
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (AtlAnalysepos.)", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return sortedPositionen;
	}
	
    /**
     * @return Einen String der Form "[Probe: Kennummer, Probeart, Datum, Anz.Positionen]"
     * bzw. "[Probe: Kennummer, Probeart, Datum, N/A]" falls die Positionen noch nicht aus
     * der Datenbank geholt wurden.
     */
	public String toString() {
		String tmp = "[Probe: " + getKennummer() + ", " + getProbeArt() + ", " + AuikUtils.getStringFromDate(getDatumDerEntnahme());
		
		if (getZeitDerEntnahmen() != null) {
			tmp += " " + getZeitDerEntnahmen();
		}
		tmp += ", ";
		
		if (Hibernate.isInitialized(getAtlAnalysepositionen())) {
			tmp += getAtlAnalysepositionen().size();
		} else {
			tmp += "N/A";
		}
		
		tmp +=  "]";
		return tmp;
	}
	
	/**
	 * @return Die ID der Probeart des Probepunktes dieser Probe
	 */
	public AtlProbeart getProbeArt() {
		return this.getAtlProbepkt().getAtlProbeart();
	}
	
	/**
	 * @return <code>true</code>, wenn die Probeart des Probepunktes dieser Probe Rohschlamm oder Faulschlamm ist, sonst <code>false</code>
	 */
	public boolean isKlaerschlammProbe() {
		if (this.getProbeArt().isFaulschlamm()) {
			return true;
		} else if (this.getProbeArt().isRohschlamm()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isAnalysepositionenInitialized() {
		return Hibernate.isInitialized(getAtlAnalysepositionen());
	}
    
    /**
     * Fügt dieser Probenahme eine Analyseposition hinzu (und sorgt 
     * für die richtigen Fremdschlüssel in den Tabellen).
     * @param pos Die neue Analyseposition
     */
    public void addAnalyseposition(AtlAnalyseposition pos) {
    	pos.setAtlProbenahmen(this);
    	this.getAtlAnalysepositionen().add(pos);
    }
}
