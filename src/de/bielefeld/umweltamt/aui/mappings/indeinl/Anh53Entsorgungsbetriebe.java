/*
 * Created Wed Apr 27 12:06:50 CEST 2005 by MyEclipse Hibernate Tool.
 */
package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;

/**
 * A class that represents a row in the 'ANH_50_ENTSORGER' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Anh53Entsorgungsbetriebe
    extends AbstractAnh53Entsorgungsbetriebe
    implements Serializable
{
    /**
     * Simple constructor of Anh53Entsorgungsbetriebe instances.
     */
    public Anh53Entsorgungsbetriebe()
    {
    }

    /**
     * Constructor of Anh53Entsorgungsbetriebe instances given a simple primary key.
     * @param entsorgerid
     */
    public Anh53Entsorgungsbetriebe(java.lang.Integer id)
    {
        super(id);
    }
    
    public String toString() {
    	return getAnh53Entsorgungsbetriebe().toString();
    }

	/**
	 * Liefert alle vorhandenen Entsorger.
	 * @param session Eine Hibernate-Session
	 * @return Alle vorhandenen Entsorger
	 * @throws HibernateException Wenn ein Datenbank-Fehler auftritt
	 */
	private static Anh53Entsorgungsbetriebe[] getEntsorg(Session session) throws HibernateException {
		List list = null;
		
		String suchString = "from Anh53Entsorgungsbetriebe ah53e order by ah53e.id";

		Query query = session.createQuery(suchString);
		query.setCacheable(true);
		query.setCacheRegion("entsorgerliste");
		list = query.list();
		
		Anh53Entsorgungsbetriebe[] tmp = new Anh53Entsorgungsbetriebe[list.size()];
		tmp = (Anh53Entsorgungsbetriebe[]) list.toArray(tmp);
		
		return tmp;
	}
	
	/**
	 * Liefert alle vorhandenen Entsorger. 
	 * öffnet eine neue Hibernate-Session und schließt sie wieder. 
	 * @return Alle vorhandenen Entsorger
	 */

	public static Anh53Entsorgungsbetriebe[] getAnh53Entsorgungsbetriebe() {
		Anh53Entsorgungsbetriebe[] tmp;
		
		try {
		Session session = HibernateSessionFactory.currentSession();
		tmp = getEntsorg(session);
		} catch (HibernateException e) {
			tmp = null;
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return tmp;
	}

	public static Anh53Entsorgungsbetriebe saveEntsorger(Anh53Entsorgungsbetriebe ents) {
		Anh53Entsorgungsbetriebe entsRet;
		
		Transaction tx = null;
		try {
			Session session = HibernateSessionFactory.currentSession();
			tx = session.beginTransaction();
			entsRet = (Anh53Entsorgungsbetriebe) session.merge(ents);
			tx.commit();
		} catch (HibernateException e) {
			entsRet = null;
			// Falls während der Änderungen ein Hibernate Fehler auftritt
			if (tx != null) {
				// Alle Änderungen rückgängig machen
				try {
					tx.rollback();
				} catch (HibernateException e1) {
					e1.printStackTrace();
					AUIKataster.handleDBException(e1, "EntsorgerEditor.doSpeichern", false);
				}
			}
		} finally {
			// Am Ende (egal ob erfolgreich oder nicht) die Session schließen
			HibernateSessionFactory.closeSession();
		}
		
		return entsRet;
	}
}
