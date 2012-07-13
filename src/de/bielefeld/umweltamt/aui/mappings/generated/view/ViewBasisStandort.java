package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewBasisStandort.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewBasisStandort
 * @author Hibernate Tools
 */
public class ViewBasisStandort extends AbstractViewBasisStandort {

    private static final Log log = LogFactory.getLog(ViewBasisStandort.class);

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
    
    public void persist(ViewBasisStandort transientInstance) {
        log.debug("persisting ViewBasisStandort instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewBasisStandort instance) {
        log.debug("attaching dirty ViewBasisStandort instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewBasisStandort instance) {
        log.debug("attaching clean ViewBasisStandort instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewBasisStandort persistentInstance) {
        log.debug("deleting ViewBasisStandort instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewBasisStandort merge(ViewBasisStandort detachedInstance) {
        log.debug("merging ViewBasisStandort instance");
        try {
            ViewBasisStandort result = (ViewBasisStandort) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ViewBasisStandort findById( de.bielefeld.umweltamt.aui.mappings.generated.view.ViewBasisStandortId id) {
        log.debug("getting ViewBasisStandort instance with id: " + id);
        try {
            ViewBasisStandort instance = (ViewBasisStandort) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.view.ViewBasisStandort", id);
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

