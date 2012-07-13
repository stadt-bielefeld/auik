package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaArbeitsstaette.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaArbeitsstaette
 * @author Hibernate Tools
 */
public class DeaArbeitsstaette extends AbstractDeaArbeitsstaette {

    private static final Log log = LogFactory.getLog(DeaArbeitsstaette.class);

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
    
    public void persist(DeaArbeitsstaette transientInstance) {
        log.debug("persisting DeaArbeitsstaette instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaArbeitsstaette instance) {
        log.debug("attaching dirty DeaArbeitsstaette instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaArbeitsstaette instance) {
        log.debug("attaching clean DeaArbeitsstaette instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaArbeitsstaette persistentInstance) {
        log.debug("deleting DeaArbeitsstaette instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaArbeitsstaette merge(DeaArbeitsstaette detachedInstance) {
        log.debug("merging DeaArbeitsstaette instance");
        try {
            DeaArbeitsstaette result = (DeaArbeitsstaette) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaArbeitsstaette findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaArbeitsstaetteId id) {
        log.debug("getting DeaArbeitsstaette instance with id: " + id);
        try {
            DeaArbeitsstaette instance = (DeaArbeitsstaette) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaArbeitsstaette", id);
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

