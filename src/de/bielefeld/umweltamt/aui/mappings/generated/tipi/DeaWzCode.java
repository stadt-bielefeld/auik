package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaWzCode.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWzCode
 * @author Hibernate Tools
 */
public class DeaWzCode extends AbstractDeaWzCode {

    private static final Log log = LogFactory.getLog(DeaWzCode.class);

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
    
    public void persist(DeaWzCode transientInstance) {
        log.debug("persisting DeaWzCode instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaWzCode instance) {
        log.debug("attaching dirty DeaWzCode instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaWzCode instance) {
        log.debug("attaching clean DeaWzCode instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaWzCode persistentInstance) {
        log.debug("deleting DeaWzCode instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaWzCode merge(DeaWzCode detachedInstance) {
        log.debug("merging DeaWzCode instance");
        try {
            DeaWzCode result = (DeaWzCode) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaWzCode findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWzCodeId id) {
        log.debug("getting DeaWzCode instance with id: " + id);
        try {
            DeaWzCode instance = (DeaWzCode) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWzCode", id);
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

