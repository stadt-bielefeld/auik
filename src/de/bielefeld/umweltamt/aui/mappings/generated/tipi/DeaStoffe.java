package de.bielefeld.umweltamt.aui.mappings.generated.tipi;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class DeaStoffe.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaStoffe
 * @author Hibernate Tools
 */
public class DeaStoffe extends AbstractDeaStoffe {

    private static final Log log = LogFactory.getLog(DeaStoffe.class);

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
    
    public void persist(DeaStoffe transientInstance) {
        log.debug("persisting DeaStoffe instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(DeaStoffe instance) {
        log.debug("attaching dirty DeaStoffe instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DeaStoffe instance) {
        log.debug("attaching clean DeaStoffe instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(DeaStoffe persistentInstance) {
        log.debug("deleting DeaStoffe instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DeaStoffe merge(DeaStoffe detachedInstance) {
        log.debug("merging DeaStoffe instance");
        try {
            DeaStoffe result = (DeaStoffe) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public DeaStoffe findById( de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaStoffeId id) {
        log.debug("getting DeaStoffe instance with id: " + id);
        try {
            DeaStoffe instance = (DeaStoffe) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.tipi.DeaStoffe", id);
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

