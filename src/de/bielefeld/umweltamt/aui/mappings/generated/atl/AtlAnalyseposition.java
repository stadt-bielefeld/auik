package de.bielefeld.umweltamt.aui.mappings.generated.atl;
// Generated 16.07.2012 17:29:07 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AtlAnalyseposition.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlAnalyseposition
 * @author Hibernate Tools
 */
public class AtlAnalyseposition extends AbstractAtlAnalyseposition {

    private static final Log log = LogFactory.getLog(AtlAnalyseposition.class);

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
    
    public void persist(AtlAnalyseposition transientInstance) {
        log.debug("persisting AtlAnalyseposition instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(AtlAnalyseposition instance) {
        log.debug("attaching dirty AtlAnalyseposition instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AtlAnalyseposition instance) {
        log.debug("attaching clean AtlAnalyseposition instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(AtlAnalyseposition persistentInstance) {
        log.debug("deleting AtlAnalyseposition instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AtlAnalyseposition merge(AtlAnalyseposition detachedInstance) {
        log.debug("merging AtlAnalyseposition instance");
        try {
            AtlAnalyseposition result = (AtlAnalyseposition) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public AtlAnalyseposition findById( java.lang.Integer id) {
        log.debug("getting AtlAnalyseposition instance with id: " + id);
        try {
            AtlAnalyseposition instance = (AtlAnalyseposition) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlAnalyseposition", id);
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

