/*
 * Created Thu May 19 07:47:27 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;


/**
 * A class that represents a row in the 'ANH_49_ABSCHEIDERDETAILS' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Anh49Abscheiderdetails
    extends AbstractAnh49Abscheiderdetails
    implements Serializable
{
    /**
     * Simple constructor of Anh49Abscheiderdetails instances.
     */
    public Anh49Abscheiderdetails()
    {
    	// Die Bool-Werte sind standardmäßig false
    	short fW = 0;
    	Short f = new Short(fW);
    	
    	setTankstelle(false);
    	setSchlammfang(false);
    	setBenzinOelabscheider(false);
    	setKoaleszenzfilter(false);
    	setIntegriert(false);
    	setEmulsionsspaltanlage(false);
    	setSchwimmer(false);
    	setWohnhaus(false);
    }

    /**
     * Constructor of Anh49Abscheiderdetails instances given a simple primary key.
     * @param abscheiderid
     */
    public Anh49Abscheiderdetails(java.lang.Integer abscheiderid)
    {
        super(abscheiderid);
    }

    /* Add customized code below */
    
    /**
     * Liefert einen String der Form "[ID:ID, NR von VON, LAGE]".
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	return "[ID:"+ getAbscheiderid() +", "+ getAbscheidernr() +" von "+ getVon() +", "+ getLage() +"]";
    }
    
    /**
     * Liefert alle Abscheiderdetails eines bestimmten Fachdatenobjekts.
     */
    public static List getAbscheiderDetails(Anh49Fachdaten fd) {
    	List details;
		try {
			Session session = HibernateSessionFactory.currentSession();
			details = session.createQuery(
				    "from Anh49Abscheiderdetails as details where " +
				    "details.Anh49Fachdaten = ? " +
				    "order by details.abscheidernr asc")
				    .setEntity(0, fd)
				    .list();

			AUIKataster.debugOutput("Details für " + fd + ", Anzahl: " + details.size(), "Anh49Abscheiderdetails");
		} catch (HibernateException e) {
			throw new RuntimeException("Datenbank-Fehler", e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
    	return details;
    }
    
    public static List getFettabschListe() {

		List fettabsch;   // Liste für Fettabscheider aus Anh49Abscheiderdetails
        List fettabsch2; //  Liste für Fettabscheider aus Anh49Fachdaten
		Anh49Fachdaten item; 
		
		String query = "from Anh49Abscheiderdetails details where details.Anh49Fachdaten.basisObjekt.basisObjektarten.objektart like 'Fettabscheider' "
			//+"order by details.nenngroesse desc";
			  +"order by details.Anh49Fachdaten.basisObjekt.inaktiv, details.Anh49Fachdaten.basisObjekt.basisStandort.plz, "
			  +"details.Anh49Fachdaten.basisObjekt.basisBetreiber"; 
		Session session = HibernateSessionFactory.currentSession();
		fettabsch = session.createQuery(query).list();
		HibernateSessionFactory.closeSession();
		
		String query2 = "from Anh49Fachdaten anh49"+
						" where anh49.basisObjekt.basisObjektarten.objektart like 'Fettabscheider' "
		 				+"order by anh49.basisObjekt.inaktiv, anh49.basisObjekt.basisStandort.plz, "
		 				+"anh49.basisObjekt.basisBetreiber"; 
		session = HibernateSessionFactory.currentSession();
		fettabsch2 = session.createQuery(query2).list();
		HibernateSessionFactory.closeSession();
		
    //Es können auch Abscheider eingepflegt sein zu denen keien Details vorhanden sind
	// Diese Abscheider werden hier zur Liste fettabsch hinzugefügt		
		for (int j = 0; j < fettabsch2.size(); j++) {
			item = (Anh49Fachdaten) fettabsch2.get(j);
					
			if (getAbscheiderDetails(item).size() == 0 )
			{
				fettabsch.add(item);

			}
			
		}
	
		return fettabsch;

	}
   
    
    public static boolean saveAbscheider(Anh49Abscheiderdetails absch) {
    	boolean saved;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(absch);
			tx.commit();
			saved = true;
		} catch (HibernateException e) {
			saved = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "Anh49Abscheider.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }
    
    public static boolean removeAbscheider(Anh49Abscheiderdetails abscheider) {
    	boolean removed;
    	
    	Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			session.delete(abscheider);
			tx.commit();
			removed = true;
		} catch (HibernateException e) {
			removed = false;
			e.printStackTrace();
			if (tx != null) {
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					AUIKataster.handleDBException(e1, "Anh49AbscheiderModel.objectRemoved", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return removed;
    }


}
