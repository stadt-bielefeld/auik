package de.bielefeld.umweltamt.aui.mappings.generated.view;
// Generated 13.07.2012 16:41:06 by Hibernate Tools 3.3.0.GA


import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * Home object for domain model class ViewAtlAnalysepositionAll.
 * @see de.bielefeld.umweltamt.aui.mappings.generated.view.ViewAtlAnalysepositionAll
 * @author Hibernate Tools
 */
public class ViewAtlAnalysepositionAll extends AbstractViewAtlAnalysepositionAll {

    private static final Log log = LogFactory.getLog(ViewAtlAnalysepositionAll.class);

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
    
    public void persist(ViewAtlAnalysepositionAll transientInstance) {
        log.debug("persisting ViewAtlAnalysepositionAll instance");
        try {
            sessionFactory.getCurrentSession().persist(transientInstance);
            log.debug("persist successful");
        }
        catch (RuntimeException re) {
            log.error("persist failed", re);
            throw re;
        }
    }
    
    public void attachDirty(ViewAtlAnalysepositionAll instance) {
        log.debug("attaching dirty ViewAtlAnalysepositionAll instance");
        try {
            sessionFactory.getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ViewAtlAnalysepositionAll instance) {
        log.debug("attaching clean ViewAtlAnalysepositionAll instance");
        try {
            sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void delete(ViewAtlAnalysepositionAll persistentInstance) {
        log.debug("deleting ViewAtlAnalysepositionAll instance");
        try {
            sessionFactory.getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ViewAtlAnalysepositionAll merge(ViewAtlAnalysepositionAll detachedInstance) {
        log.debug("merging ViewAtlAnalysepositionAll instance");
        try {
            ViewAtlAnalysepositionAll result = (ViewAtlAnalysepositionAll) sessionFactory.getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public ViewAtlAnalysepositionAll findById( de.bielefeld.umweltamt.aui.mappings.generated.view.ViewAtlAnalysepositionAllId id) {
        log.debug("getting ViewAtlAnalysepositionAll instance with id: " + id);
        try {
            ViewAtlAnalysepositionAll instance = (ViewAtlAnalysepositionAll) sessionFactory.getCurrentSession()
                    .get("de.bielefeld.umweltamt.aui.mappings.generated.view.ViewAtlAnalysepositionAll", id);
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

