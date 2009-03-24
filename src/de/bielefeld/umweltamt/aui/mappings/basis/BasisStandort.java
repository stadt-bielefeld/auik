/*
 * Created Thu Jan 13 16:52:31 CET 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.basis;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * Eine Klasse, die eine Zeile der 'BASIS_STANDORT'-Tabelle 
 * repräsentiert.
 */
public class BasisStandort
    extends AbstractBasisStandort
    implements Serializable
{
    /**
     * Simple constructor of BasisStandort instances.
     */
    public BasisStandort()
    {
    }

    /**
     * Constructor of BasisStandort instances given a simple primary key.
     * @param standortid
     */
    public BasisStandort(java.lang.Integer standortid)
    {
        super(standortid);
    }

    /* Add customized code below */
    
    /**
     * Liefert einen String der Form "Strasse Nr.NrZusatz", also
     * beispielsweise "Ravensberger Straße 77", "Apfelstraße 23b" 
     * oder "Jahnplatz 41-42".
     */
    public String toString() {
		String strasse = this.getStrasse();
		String nr = "";
		if (this.getHausnr() != null && this.getHausnrzus() != null) {
			nr = " " + this.getHausnr() + this.getHausnrzus();
		} else if (this.getHausnr() != null) {
			nr = " " + this.getHausnr();
		}
		return strasse + nr;
	}
    
	/**
	 * Liefert einen Standort mit einer bestimmten ID.
	 * @param id Die ID (der Primärschlüssel) des Standorts.
	 * @return Den gesuchten Standort oder <code>null</code>, 
	 * falls kein Standort mit dieser ID existiert.
	 */
    public static BasisStandort getStandort(Integer id) {
    	BasisStandort standort;
		try {
			Session session = HibernateSessionFactory.currentSession();
			standort = (BasisStandort) session.get(BasisStandort.class, id);
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			standort = null;
		}
    	
    	return standort;
    }
    
    /**
     * Durchsucht die Standort-Tabelle nach Straße und Hausnummer.. 
     * Bei der Straße wird Groß-/Kleinschreibung ignoriert und automatisch ein
	 * '%' angehängt.
	 * Wenn die Hausnummer -1 ist, wird nur nach der Straße gesucht.
     * @param strasse Der Straßenname (oder sein Anfang).
     * @param hausnr Die Hausnummer (oder -1, falls nicht nach einer bestimmten Hausnummer gesucht werden soll)
     * @return Eine Liste mit allen gefundenen Standorten.
     */
    public static List findStandorte(String strasse, int hausnr) {
    	String strasse2 = strasse.toLowerCase().trim() + "%";
    	Integer hausNummer = new Integer(hausnr);
    	
    	AUIKataster.debugOutput("Suche nach '" + strasse2 + "' Nr. " + hausnr, "BasisStandort.findStandorte");
    	List standorte;
		try {
			Session session = HibernateSessionFactory.currentSession();
			if (hausnr != -1) {
				standorte = session.createQuery(
						"from BasisStandort as bsta where " +
						"lower(bsta.strasse) like ? " +
						"and bsta.hausnr = ? " +
						"order by bsta.strasse, bsta.hausnr")
						.setString(0, strasse2)
						.setInteger(1, hausNummer)
						.list();

			} else {
				standorte = session.createQuery(
						"from BasisStandort as bsta where " +
						"lower(bsta.strasse) like ? " +
						"order by bsta.strasse, bsta.hausnr")
						.setString(0, strasse2)
						.list();

			}
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
    	return standorte;
    }
    
    /**
     * Speichert einen Standort in die Datenbank, bzw. updatet einen 
     * schon vorhandenen Standort mit neuen Werten.
     * @param bsta Der Standort, der gespeichert werden soll.
     * @return Der gespeicherte Standort, oder <code>null</code>, falls beim Speichern ein Fehler auftrat.
     */
    public static BasisStandort saveStandort(BasisStandort bsta) {
    	BasisStandort bstaRet;
    	
    	Transaction tx = null;
		try {
			// Neue Session beginnen
			Session session = HibernateSessionFactory.currentSession();
			
			// Alle Änderungen in einer Transaktion zusammenfassen
			tx = session.beginTransaction();
			
			// Speichern / Transaktion durchführen
			session.saveOrUpdate(bsta);
			bstaRet = bsta;

			tx.commit();
			
			//frame.changeStatus("Neuer Standort "+bsta.getStandortid()+" erfolgreich gespeichert!", HauptFrame.SUCCESS_COLOR);
			AUIKataster.debugOutput("Neuer Standort "+ bsta +" gespeichert!", "BasisStandort.saveStandort");
			//manager.getCache().invalidateCache("standorte");
			
			// Formular zurücksetzen
			//clearForm();
		} catch (HibernateException e) {
			bstaRet = null;
			e.printStackTrace();
			// Falls während der Änderungen ein Hibernate Fehler auftritt
			if (tx != null) {
				try {
					// Alle Änderungen rückgängig machen
					tx.rollback();
				} catch (HibernateException e1) {
					throw new RuntimeException("Datenbank-Fehler (BasisStandort)", e);
				}
			}
		} finally {
			// Am Ende (egal ob erfolgreich oder nicht) die Session schließen
			HibernateSessionFactory.closeSession();
		}
		
		return bstaRet;
    }
    
    /**
	 * Liefert alle in der Tabelle benutzten Entwässerungsgebiete als Strings.
	 * <br><b>ACHTUNG:</b> Diese Methode liefert <b>nicht</b> alle 
	 * Standorte, sondern alle in der Spalte ENTWGEB benutzten Werte!
	 * @param session Eine Hibernate-Session
	 * @return Alle zur Zeit benutzten Entwässerungsgebiete
	 * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
	 */
	private static String[] getEntwGebiete(Session session) throws HibernateException {
		List list = null;
		String suchString = "SELECT DISTINCT sta.entgebid FROM de.bielefeld.umweltamt.aui.mappings.basis.BasisStandort sta";
		Query query = session.createQuery(suchString);
		query.setCacheable(true);
		query.setCacheRegion("ezgbliste");
		list = query.list();
		String[] tmp = new String[list.size()];
		tmp = (String[]) list.toArray(tmp);
		return tmp;
	}
	
    /**
	 * Liefert alle in der Tabelle benutzten Entwässerungsgebiete als Strings.
	 * <br><b>ACHTUNG:</b> Diese Methode liefert <b>nicht</b> alle 
	 * Standorte, sondern alle in der Spalte ENTWGEB benutzten Werte!
	 * @return Alle zur Zeit benutzten Entwässerungsgebiete
	 */
	public static String[] getEntwGebiete() {
		String[] tmp;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tmp = getEntwGebiete(session);
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler (BasisStandort)", e);
		}
		
		return tmp;
	}
}
