package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewAtlAnalysepositionAllId.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewAtlAnalysepositionAllId
 * @author Hibernate Tools
 */
public class ViewAtlAnalysepositionAllId extends AbstractViewAtlAnalysepositionAllId {

    private static final Log log = LogFactory.getLog(ViewAtlAnalysepositionAllId.class);

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
    
    public void persist(ViewAtlAnalysepositionAllId transientInstance) {
        log.debug("persisting ViewAtlAnalysepositionAllId instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewAtlAnalysepositionAllId instance) {
        log.debug("attaching dirty ViewAtlAnalysepositionAllId instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewAtlAnalysepositionAllId instance) {
        log.debug("attaching clean ViewAtlAnalysepositionAllId instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewAtlAnalysepositionAllId persistentInstance) {
        log.debug("deleting ViewAtlAnalysepositionAllId instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewAtlAnalysepositionAllId merge(ViewAtlAnalysepositionAllId detachedInstance) {
        log.debug("merging ViewAtlAnalysepositionAllId instance");
        try {
            ViewAtlAnalysepositionAllId result = (ViewAtlAnalysepositionAllId) sessionFactory.getCurrentSession()
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

