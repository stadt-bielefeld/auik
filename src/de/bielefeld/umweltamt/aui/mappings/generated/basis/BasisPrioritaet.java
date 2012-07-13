package de.bielefeld.umweltamt.aui.mappings.generated.basis;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class BasisPrioritaet.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisPrioritaet
 * @author Hibernate Tools
 */
public class BasisPrioritaet extends AbstractBasisPrioritaet {

    private static final Log log = LogFactory.getLog(BasisPrioritaet.class);

    private final SessionFactory sessionFactory = getSessionFactory();
    
    protected SessionFactory getSessionFactory() {
        try {
            return (SessionFactory) new InitialContext().lookup("SessionFactory");
        }
        catch (Exception e) {
            log.error("Could not locate SessionFactory in JNDI", e);
            throw new IllegalStateException("Could not locate SessionFactory in JNDI");
        }
    }
    
    public void persist(BasisPrioritaet transientInstance) {
        log.debug("persisting BasisPrioritaet instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(BasisPrioritaet instance) {
        log.debug("attaching dirty BasisPrioritaet instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BasisPrioritaet instance) {
        log.debug("attaching clean BasisPrioritaet instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(BasisPrioritaet persistentInstance) {
        log.debug("deleting BasisPrioritaet instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BasisPrioritaet merge(BasisPrioritaet detachedInstance) {
        log.debug("merging BasisPrioritaet instance");
        try {
            BasisPrioritaet result = (BasisPrioritaet) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public BasisPrioritaet findById( de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisPrioritaetId id) {
        log.debug("getting BasisPrioritaet instance with id: " + id);
        try {
            BasisPrioritaet instance = (BasisPrioritaet) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisPrioritaet", id);
            if (instance==null) {
                log.debug("get successful, no instance found");
            }
            else {
                log.debug("get successful, instance found");
            }
            return instance;
        }
        catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
}

