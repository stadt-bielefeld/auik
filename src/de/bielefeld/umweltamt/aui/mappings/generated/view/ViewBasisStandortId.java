package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewBasisStandortId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewBasisStandortId
 * @author Hibernate Tools
 */
public class ViewBasisStandortId extends AbstractViewBasisStandortId {

    private static final Log log = LogFactory.getLog(ViewBasisStandortId.class);

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
    
    public void persist(ViewBasisStandortId transientInstance) {
        log.debug("persisting ViewBasisStandortId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewBasisStandortId instance) {
        log.debug("attaching dirty ViewBasisStandortId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewBasisStandortId instance) {
        log.debug("attaching clean ViewBasisStandortId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewBasisStandortId persistentInstance) {
        log.debug("deleting ViewBasisStandortId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewBasisStandortId merge(ViewBasisStandortId detachedInstance) {
        log.debug("merging ViewBasisStandortId instance");
        try {
            ViewBasisStandortId result = (ViewBasisStandortId) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    
}

