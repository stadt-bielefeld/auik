package de.bielefeld.umweltamt.aui.mappings.generated.basis;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class BasisStrassen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisStrassen
 * @author Hibernate Tools
 */
public class BasisStrassen extends AbstractBasisStrassen {

    private static final Log log = LogFactory.getLog(BasisStrassen.class);

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
    
    public void persist(BasisStrassen transientInstance) {
        log.debug("persisting BasisStrassen instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(BasisStrassen instance) {
        log.debug("attaching dirty BasisStrassen instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(BasisStrassen instance) {
        log.debug("attaching clean BasisStrassen instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(BasisStrassen persistentInstance) {
        log.debug("deleting BasisStrassen instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public BasisStrassen merge(BasisStrassen detachedInstance) {
        log.debug("merging BasisStrassen instance");
        try {
            BasisStrassen result = (BasisStrassen) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public BasisStrassen findById( int id) {
        log.debug("getting BasisStrassen instance with id: " + id);
        try {
            BasisStrassen instance = (BasisStrassen) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.basis.BasisStrassen", id);
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

