package de.bielefeld.umweltamt.aui.mappings.generated.atl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class AtlKlaeranlagen.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlKlaeranlagen
 * @author Hibernate Tools
 */
public class AtlKlaeranlagen extends AbstractAtlKlaeranlagen {

    private static final Log log = LogFactory.getLog(AtlKlaeranlagen.class);

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
    
    public void persist(AtlKlaeranlagen transientInstance) {
        log.debug("persisting AtlKlaeranlagen instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(AtlKlaeranlagen instance) {
        log.debug("attaching dirty AtlKlaeranlagen instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AtlKlaeranlagen instance) {
        log.debug("attaching clean AtlKlaeranlagen instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(AtlKlaeranlagen persistentInstance) {
        log.debug("deleting AtlKlaeranlagen instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AtlKlaeranlagen merge(AtlKlaeranlagen detachedInstance) {
        log.debug("merging AtlKlaeranlagen instance");
        try {
            AtlKlaeranlagen result = (AtlKlaeranlagen) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public AtlKlaeranlagen findById( int id) {
        log.debug("getting AtlKlaeranlagen instance with id: " + id);
        try {
            AtlKlaeranlagen instance = (AtlKlaeranlagen) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.atl.AtlKlaeranlagen", id);
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

