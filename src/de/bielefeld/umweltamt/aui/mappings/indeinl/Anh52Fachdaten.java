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
public class Anh52Fachdaten
    extends AbstractAnh52Fachdaten
    implements Serializable
{
    /**
     * Simple constructor of Anh52Fachdaten instances.
     */
    public Anh52Fachdaten()
    {
    }

    /**
     * Constructor of Anh52Fachdaten instances given a simple primary key.
     * @param id
     */
    public Anh52Fachdaten(java.lang.Integer id)
    {
        super(id);
    }

    public String toString() {
		return "[ID:" + this.getId() + "]";
	}

    public static Anh52Fachdaten getAnh52ByObjekt(BasisObjekt objekt, Session session) {
    	Anh52Fachdaten fachdaten = null;
    	if (objekt.getBasisObjektarten().isAnh52()) {
    		try {
				List anhang52 = session.createQuery(
					    "from Anh52Fachdaten as anhang52 where " +
					    "anhang52.basisObjekt = ?")
					    .setEntity(0, objekt)
					    .list();
				
				if (anhang52.size() > 0) {
					fachdaten = (Anh52Fachdaten) anhang52.get(0);
				}
			} catch (HibernateException e) {
			}
    	}
    	
    	return fachdaten;
    }

    public static Anh52Fachdaten getAnh52ByObjekt(BasisObjekt objekt) {
    	Anh52Fachdaten fachdaten;
    	try {
    		Session session = HibernateSessionFactory.currentSession();
    		fachdaten = getAnh52ByObjekt(objekt, session);
    		HibernateSessionFactory.closeSession();
    	} catch (HibernateException e) {
    		fachdaten = null;
    	}
    	
    	return fachdaten;
    }

    public static boolean saveFachdaten(Anh52Fachdaten fachdaten) {
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
					AUIKataster.handleDBException(e1, "Anh52Fachdaten.save", false);
				}
			}
		} finally {
			HibernateSessionFactory.closeSession();
		}
		
		return saved;
    }

	/**
	 * Liefert eine Liste mit allen Anhang52 Objekten.
	 * @return Eine Liste aus Anh52Fachdaten.
	 */
	public static List getAuswertungsListe() {
		List liste;
		
		String query = "from Anh52Fachdaten as anh52 " +
				"order by anh52.basisObjekt.inaktiv, anh52.id";
		
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
