package de.bielefeld.umweltamt.aui.mappings.indeinl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import de.bielefeld.umweltamt.aui.mappings.basis.BasisObjekt;

/**
 * A class that represents a row in the 'ANH_56_FACHDATEN' table. 
 * This class may be customized as it is never re-generated 
 * after being created.
 */
public class Anh55Fachdaten
    extends AbstractAnh55Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh55Fachdaten instances.
     */
    public Anh55Fachdaten()
    {
    }

    /**
     * Constructor of Anh55Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh55Fachdaten(java.lang.Integer id)
    {
        super(id);
    }

    public String toString() {
		return "[ID:" + this.getId() + "]";
	}

    public static Anh55Fachdaten getAnh55ByObjekt(BasisObjekt objekt, Session session) {
    	Anh55Fachdaten fachdaten = null;
    	if (objekt.getBasisObjektarten().isAnh55()) {
    		try {
				List anhang55 = session.createQuery(
					    "from Anh55Fachdaten as anhang55 where " +
					    "anhang55.basisObjekt = ?")
					    .setEntity(0, objekt)
					    .list();
				
				if (anhang55.size() > 0) {
					fachdaten = (Anh55Fachdaten) anhang55.get(0);
				}
			} catch (HibernateException e) {
			}
    	}
    	
    	return fachdaten;
    }

    public static Anh55Fachdaten getAnh55ByObjekt(BasisObjekt objekt) {
    	Anh55Fachdaten fachdaten;
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		fachdaten = getAnh55ByObjekt(objekt, session);
    		HibernateSessionFactory.closeSession();
    	} catch (HibernateException e) {
    		fachdaten = null;
    	}
    	
    	return fachdaten;
    }

    public static boolean saveFachdaten(Anh55Fachdaten fachdaten) {
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
					AUIKataster.handleDBException(e1, "Anh55Fachdaten.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }

	/**
	 * Liefert eine Liste mit allen Anhang55 Objekten.
	 * @return Eine Liste aus Anh55Fachdaten.
	 */
	public static List getAuswertungsListe() {
		List liste;
		
		String query = "from Anh55Fachdaten as anh55 " +
				"order by anh55.basisObjekt.inaktiv, anh55.id";
		
		try {
			Session session = HibernateSessionFactory.currentSession();
			liste = session.createQuery(query).list();
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return liste;
	}
}
