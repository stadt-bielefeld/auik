package de.bielefeld.umweltamt.aui.mappings.generated.indeinl;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewBwk.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.indeinl.ViewBwk
 * @author Hibernate Tools
 */
public class ViewBwk extends AbstractViewBwk {

    private static final Log log = LogFactory.getLog(ViewBwk.class);

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
    
    public void persist(ViewBwk transientInstance) {
        log.debug("persisting ViewBwk instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewBwk instance) {
        log.debug("attaching dirty ViewBwk instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewBwk instance) {
        log.debug("attaching clean ViewBwk instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewBwk persistentInstance) {
        log.debug("deleting ViewBwk instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewBwk merge(ViewBwk detachedInstance) {
        log.debug("merging ViewBwk instance");
        try {
            ViewBwk result = (ViewBwk) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ViewBwk findById( de.bielefeld.umweltamt.aui.mappings.generated.indeinl.ViewBwkId id) {
        log.debug("getting ViewBwk instance with id: " + id);
        try {
            ViewBwk instance = (ViewBwk) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.indeinl.ViewBwk", id);
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

