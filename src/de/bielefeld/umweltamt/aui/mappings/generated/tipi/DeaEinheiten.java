package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaEinheiten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaEinheiten
 * @author Hibernate Tools
 */
public class DeaEinheiten extends AbstractDeaEinheiten {

    private static final Log log = LogFactory.getLog(DeaEinheiten.class);

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
    
    public void persist(DeaEinheiten transientInstance) {
        log.debug("persisting DeaEinheiten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaEinheiten instance) {
        log.debug("attaching dirty DeaEinheiten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaEinheiten instance) {
        log.debug("attaching clean DeaEinheiten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaEinheiten persistentInstance) {
        log.debug("deleting DeaEinheiten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaEinheiten merge(DeaEinheiten detachedInstance) {
        log.debug("merging DeaEinheiten instance");
        try {
            DeaEinheiten result = (DeaEinheiten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaEinheiten findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaEinheitenId id) {
        log.debug("getting DeaEinheiten instance with id: " + id);
        try {
            DeaEinheiten instance = (DeaEinheiten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaEinheiten", id);
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

