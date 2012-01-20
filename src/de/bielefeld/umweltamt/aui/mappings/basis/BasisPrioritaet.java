package de.bielefeld.umweltamt.aui.mappings.basis;

// Generated 21.11.2011 10:56:36 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import de.bielefeld.umweltamt.aui.AUIKataster;
import de.bielefeld.umweltamt.aui.DatabaseManager;
import de.bielefeld.umweltamt.aui.HibernateSessionFactory;
import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class BasisPrioritaet.
 * @see AbstractBasisPrioritaet.BasisPrioritaet
 * @author Hibernate Tools
 */
public class BasisPrioritaet 
	extends AbstractBasisPrioritaet
	implements Serializable
{
    /** Database manager */
    private static final DatabaseManager dbManager = DatabaseManager.getInstance();

	Session session = HibernateSessionFactory.currentSession();

	public void persist(AbstractBasisPrioritaet transientInstance) {

		try {
			session.persist(transientInstance);

		} catch (RuntimeException re) {

			throw re;
		}
	}

	public void attachDirty(AbstractBasisPrioritaet instance) {

		try {
			session.saveOrUpdate(instance);

		} catch (RuntimeException re) {

			throw re;
		}
	}

	public void attachClean(AbstractBasisPrioritaet instance) {

		try {
			session.lock(instance, LockMode.NONE);

		} catch (RuntimeException re) {

			throw re;
		}
	}

	public void delete(AbstractBasisPrioritaet persistentInstance) {

		try {
			session.delete(persistentInstance);

		} catch (RuntimeException re) {

			throw re;
		}
	}
	
    public String toString() {
        return super.getPrioritaet().toString();
    }

	public static BasisPrioritaet getPrioritaet(BasisObjekt basisObjekt) {
	
		BasisPrioritaet prioritaet = null; 
		
    	String query =
        	"from BasisPrioritaet as bp " +
        	"where bp.basisStandort.standortid = ? " +
        	"and bp.basisBetreiber.betreiberid = ?";

        try {
        Session session = HibernateSessionFactory.currentSession();
			if ((Integer) session
					.createQuery(query)
					.setInteger(0,
							basisObjekt.getBasisStandort().getStandortid())
					.setInteger(1,
							basisObjekt.getBasisBetreiber().getBetreiberid())
					.list().size() > 0) {

				prioritaet = (BasisPrioritaet) session
						.createQuery(query)
						.setInteger(0,
								basisObjekt.getBasisStandort().getStandortid())
						.setInteger(
								1,
								basisObjekt.getBasisBetreiber()
										.getBetreiberid()).list().get(0);

			}
        } catch (HibernateException e) {
            throw new RuntimeException("Datenbank-Fehler", e);
        } finally {
            HibernateSessionFactory.closeSession();
        }

        return prioritaet;
		
	}

    /**
     * Speichert eine Priorität in der Datenbank.
     * @param obj Das Objekt zu dem die Priorität gespeichert werden soll.
     * @param prio Die zu speichernde Priorität
     * @return Das gespeicherte Prioritätsobjekt.
     */
	public static BasisPrioritaet saveBasisPrioritaet(BasisObjekt obj,
			Integer prio) {
		BasisPrioritaet prioritaet;
		BasisPrioritaet saved;
		BasisPrioritaetId prioId;

		if (getPrioritaet(obj) != null) {
			prioritaet = getPrioritaet(obj);
			prioritaet.setPrioritaet(prio);

			Transaction tx = null;
			try {
				Session session = HibernateSessionFactory.currentSession();
				tx = session.beginTransaction();
				session.saveOrUpdate(prioritaet);
				saved = prioritaet;

				tx.commit();
			} catch (HibernateException e) {
				saved = null;
				e.printStackTrace();
				if (tx != null) {
					try {
						tx.rollback();
					} catch (HibernateException e1) {
						dbManager.handleDBException(e1,
								"BasisPrioritaet.save", false);
					}
				}
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}

		else {
			
			prioritaet = new BasisPrioritaet();
			prioId = new BasisPrioritaetId();
			
			prioId.setStandortId(obj.getBasisStandort().getStandortid());
			prioId.setBetreiberId(obj.getBasisBetreiber().getBetreiberid());
			prioritaet.setId(prioId);
			
			prioritaet.setBasisBetreiber(obj.getBasisBetreiber());
			prioritaet.setBasisStandort(obj.getBasisStandort());
			prioritaet.setPrioritaet(prio);

			Transaction tx = null;
			try {
				Session session = HibernateSessionFactory.currentSession();
				tx = session.beginTransaction();
				session.saveOrUpdate(prioritaet);
				saved = prioritaet;

				tx.commit();
			} catch (HibernateException e) {
				saved = null;
				e.printStackTrace();
				if (tx != null) {
					try {
						tx.rollback();
					} catch (HibernateException e1) {
						dbManager.handleDBException(e1,
								"BasisPrioritaet.save", false);
					}
				}
			} finally {
				HibernateSessionFactory.closeSession();
			}
		}

		return saved;
	}
}
