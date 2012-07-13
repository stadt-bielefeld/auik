package de.bielefeld.umweltamt.aui.mappings.generated.atl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AtlEinheiten.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlEinheiten
 * @author Hibernate Tools
 */
public class AtlEinheiten extends AbstractAtlEinheiten {

    private static final Log log = LogFactory.getLog(AtlEinheiten.class);

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
    
    public void persist(AtlEinheiten transientInstance) {
        log.debug("persisting AtlEinheiten instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(AtlEinheiten instance) {
        log.debug("attaching dirty AtlEinheiten instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AtlEinheiten instance) {
        log.debug("attaching clean AtlEinheiten instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(AtlEinheiten persistentInstance) {
        log.debug("deleting AtlEinheiten instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AtlEinheiten merge(AtlEinheiten detachedInstance) {
        log.debug("merging AtlEinheiten instance");
        try {
            AtlEinheiten result = (AtlEinheiten) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public AtlEinheiten findById( int id) {
        log.debug("getting AtlEinheiten instance with id: " + id);
        try {
            AtlEinheiten instance = (AtlEinheiten) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlEinheiten", id);
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

