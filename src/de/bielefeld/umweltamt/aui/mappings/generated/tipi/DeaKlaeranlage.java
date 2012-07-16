package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaKlaeranlage.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaKlaeranlage
 * @author Hibernate Tools
 */
public class DeaKlaeranlage extends AbstractDeaKlaeranlage {

    private static final Log log = LogFactory.getLog(DeaKlaeranlage.class);

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
    
    public void persist(DeaKlaeranlage transientInstance) {
        log.debug("persisting DeaKlaeranlage instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaKlaeranlage instance) {
        log.debug("attaching dirty DeaKlaeranlage instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaKlaeranlage instance) {
        log.debug("attaching clean DeaKlaeranlage instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaKlaeranlage persistentInstance) {
        log.debug("deleting DeaKlaeranlage instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaKlaeranlage merge(DeaKlaeranlage detachedInstance) {
        log.debug("merging DeaKlaeranlage instance");
        try {
            DeaKlaeranlage result = (DeaKlaeranlage) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaKlaeranlage findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaKlaeranlageId id) {
        log.debug("getting DeaKlaeranlage instance with id: " + id);
        try {
            DeaKlaeranlage instance = (DeaKlaeranlage) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaKlaeranlage", id);
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

