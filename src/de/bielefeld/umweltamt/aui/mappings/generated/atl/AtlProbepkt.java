package de.bielefeld.umweltamt.aui.mappings.generated.atl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AtlProbepkt.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlProbepkt
 * @author Hibernate Tools
 */
public class AtlProbepkt extends AbstractAtlProbepkt {

    private static final Log log = LogFactory.getLog(AtlProbepkt.class);

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
    
    public void persist(AtlProbepkt transientInstance) {
        log.debug("persisting AtlProbepkt instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(AtlProbepkt instance) {
        log.debug("attaching dirty AtlProbepkt instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AtlProbepkt instance) {
        log.debug("attaching clean AtlProbepkt instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(AtlProbepkt persistentInstance) {
        log.debug("deleting AtlProbepkt instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AtlProbepkt merge(AtlProbepkt detachedInstance) {
        log.debug("merging AtlProbepkt instance");
        try {
            AtlProbepkt result = (AtlProbepkt) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public AtlProbepkt findById( int id) {
        log.debug("getting AtlProbepkt instance with id: " + id);
        try {
            AtlProbepkt instance = (AtlProbepkt) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlProbepkt", id);
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

