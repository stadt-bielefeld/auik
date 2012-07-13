package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaWasserbehoerden.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWasserbehoerden
 * @author Hibernate Tools
 */
public class DeaWasserbehoerden extends AbstractDeaWasserbehoerden {

    private static final Log log = LogFactory.getLog(DeaWasserbehoerden.class);

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
    
    public void persist(DeaWasserbehoerden transientInstance) {
        log.debug("persisting DeaWasserbehoerden instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaWasserbehoerden instance) {
        log.debug("attaching dirty DeaWasserbehoerden instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaWasserbehoerden instance) {
        log.debug("attaching clean DeaWasserbehoerden instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaWasserbehoerden persistentInstance) {
        log.debug("deleting DeaWasserbehoerden instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaWasserbehoerden merge(DeaWasserbehoerden detachedInstance) {
        log.debug("merging DeaWasserbehoerden instance");
        try {
            DeaWasserbehoerden result = (DeaWasserbehoerden) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaWasserbehoerden findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWasserbehoerdenId id) {
        log.debug("getting DeaWasserbehoerden instance with id: " + id);
        try {
            DeaWasserbehoerden instance = (DeaWasserbehoerden) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaWasserbehoerden", id);
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

