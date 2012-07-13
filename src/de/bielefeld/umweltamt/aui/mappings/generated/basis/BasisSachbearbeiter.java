package de.bielefeld.umweltamt.aui.mappings.generated.basis;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class BasisSachbearbeiter.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisSachbearbeiter
 * @author Hibernate Tools
 */
public class BasisSachbearbeiter extends AbstractBasisSachbearbeiter {

    private static final Log log = LogFactory.getLog(BasisSachbearbeiter.class);

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
    
    public void persist(BasisSachbearbeiter transientInstance) {
        log.debug("persisting BasisSachbearbeiter instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(BasisSachbearbeiter instance) {
        log.debug("attaching dirty BasisSachbearbeiter instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BasisSachbearbeiter instance) {
        log.debug("attaching clean BasisSachbearbeiter instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(BasisSachbearbeiter persistentInstance) {
        log.debug("deleting BasisSachbearbeiter instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BasisSachbearbeiter merge(BasisSachbearbeiter detachedInstance) {
        log.debug("merging BasisSachbearbeiter instance");
        try {
            BasisSachbearbeiter result = (BasisSachbearbeiter) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public BasisSachbearbeiter findById( java.lang.String id) {
        log.debug("getting BasisSachbearbeiter instance with id: " + id);
        try {
            BasisSachbearbeiter instance = (BasisSachbearbeiter) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisSachbearbeiter", id);
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

